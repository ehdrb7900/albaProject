package kr.or.ddit.mvc.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.utils.ReflectionUtils;

/** 6-1
 * 1. 특정 패키지 내의 모든 핸들러에 대한 정보를 수집하고 Map으로 관리
 * 		: parseBasepackage
 * 2. handlerMap을 바탕으로 특정 요청을 처리할 수 있는 핸들러 검색
 * 		: findCommandHandler
 *
 */
public class HandlerMapper implements IHandlerMapper {
	private static Logger logger = LoggerFactory.getLogger(HandlerMapper.class); //slf4j 사용
	
	
	private Map<URIMappingCondition, URIMappingInfo> handlerMap;
	
	public HandlerMapper(String...basePackages){ // =String[]의 basePackages
		handlerMap = new LinkedHashMap<>(); // 객체생성
		if(basePackages==null || basePackages.length==0) return; //스캔대상 없으면 리턴
		parseBasePackage(basePackages); //파싱
	}
	
	private void parseBasePackage(String...basePackages) {
		Map<Class<?>, Annotation> resultMap = ReflectionUtils.getClassesWithAnnotationAtBasePackages(CommandHandler.class, basePackages);
		for(Entry<Class<?>, Annotation> entry : resultMap.entrySet()) {
			Class<?> handlerType = entry.getKey();
			Map<Method, Annotation> methodMap = ReflectionUtils.getMethodsWithAnnotationAtClass(handlerType, URIMapping.class, String.class, HttpServletRequest.class, HttpServletResponse.class); //조건 : 파라미터타입 통일, 리턴타입동일, URI어노테이션 가짐 
			if(methodMap.size()==0) continue; //핸들러 하나도 없을때 다음으로 넘김
			
			Object commandHandler;
			try {
				commandHandler = handlerType.newInstance(); //MemberReadController에 기본생성자 있어야
			} catch (InstantiationException | IllegalAccessException e) {
				throw new RuntimeException(e); //기본생성자없으면 할수있는것 없음
			} 
			//메서드 하나이상 있을때
			for(Entry<Method, Annotation> mtdEntry : methodMap.entrySet()){
				 Method handlerMethod = mtdEntry.getKey();
				 URIMapping mtdAnnotation = (URIMapping) mtdEntry.getValue();
				 String uri = mtdAnnotation.value();
				 HttpMethod httpMethod = mtdAnnotation.method();
				 URIMappingCondition mappingCondition = new URIMappingCondition(uri, httpMethod);
				 
				 URIMappingInfo mappingInfo = new URIMappingInfo(mappingCondition, commandHandler, handlerMethod);
				 handlerMap.put(mappingCondition, mappingInfo);
				 logger.info("{} : {}", mappingInfo, mappingCondition);
			 }
		}// outer for end
	}

	@Override
	public URIMappingInfo findCommandHandler(HttpServletRequest req) {
//		String uri = req.getRequestURI(); //contextPath, session파라미터 포함되어있음
		String uri = getRealativeContextPath(req);
		HttpMethod method = HttpMethod.valueOf(req.getMethod().toUpperCase()); //req.getMethod() 문자열에 해당하는 상수 받아옴, 대문자로
		URIMappingCondition key = new URIMappingCondition(uri, method);
		return handlerMap.get(key);
	}

	private String getRealativeContextPath(HttpServletRequest req) {
		//현재요청 : /webStudy03/member/memberList.do 에서 /webStudy03잘라야함
		int cPlength = req.getContextPath().length();
		//http://localhost/webStudy03/memberList.do;JSESSION= 일때 ;JSESSION=지우기
		String uri = req.getRequestURI().split(";")[0]; //현재요청의 주소판단 
		uri = uri.substring(cPlength); //contextPath이후의 경로 돌아옴
		
		return uri;
	}

}
