package kr.or.ddit.mvc.annotation;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//7-1
public class HandlerInvoker implements IHandlerInvoker { //front가 사용

	@Override
	public String InvokeHandler(URIMappingInfo mappingInfo, HttpServletRequest req, HttpServletResponse resp) {
		Object handler = mappingInfo.getCommandHandler();
		Method handlerMethod = mappingInfo.getHandlerMethod();
		//handler가 갖고있는 handlerMethod호출해야함
		String viewName;
		try {
			viewName = (String) handlerMethod.invoke(handler, req, resp); //파라미터 넘기기위해 req, resp받아옴
			return viewName;
		} catch (Exception e) { //핸들러에서 발생한 모든 예외 RuntimeExcption으로 바꿈
			throw new RuntimeException(e);
		} 
		
	}

}
