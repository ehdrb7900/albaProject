package kr.or.ddit.mvc;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.mvc.annotation.HandlerInvoker;
import kr.or.ddit.mvc.annotation.HandlerMapper;
import kr.or.ddit.mvc.annotation.IHandlerInvoker;
import kr.or.ddit.mvc.annotation.IHandlerMapper;
import kr.or.ddit.mvc.annotation.URIMappingInfo;

/**
 * 현재 어플리케이션의 유일한 서블릿.
 * *모든 요청*은 FrontControllerServlet을 통해 접수. -> service 메서드 오버라이딩
 * 
 */
public class FrontControllerServlet extends HttpServlet{
	/* loging framework사용 (sysout 대신)*/
	//slf4j //Logger가 인터페이스이기 때문에 객체생성불가->팩토리사용 //log4j2에 name="kr.or.ddit"있기때문에
	private static Logger logger = LoggerFactory.getLogger(FrontControllerServlet.class);
	
	private IHandlerMapper handlerMapper;
	private IHandlerInvoker handlerInvoker;
	private IViewProcessor viewProcessor;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		String[] basePackages = null;
		String pkgs = config.getInitParameter("basePakages");
		if(pkgs!=null) { //파라미터 넘어옴
			basePackages = pkgs.split("\\s+"); //공백이 한칸이상 들어가있으면
		}
		
		String prefix = config.getInitParameter("prefix");
		String suffix = config.getInitParameter("suffix");
		
		handlerMapper = new HandlerMapper(basePackages); //"kr.or.ddit.member"
		handlerInvoker = new HandlerInvoker();
		viewProcessor = new ViewProcessor();
		if(prefix!=null)
			viewProcessor.setPrefix(prefix); // "/WEB-INF/views/"
		if(suffix!=null)
			viewProcessor.setSuffix(suffix); // ".jsp"
	}
	
	//템플릿 메서드 (구체적인 작업X, 작업의 절차만 정의)
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//req.setCharacterEncoding("UTF-8"); //CharacterEncodingFilter.java 로 옮김

		URIMappingInfo mappingInfo = handlerMapper.findCommandHandler(req); //어떤 핸들러에대한 정보들어있음 //ex)kr.or.ddit.prod.controller.ProdInsertController.doPost
		if(mappingInfo==null) { //처리할 수 없는 요청(사용하려고하는 주소 존재하지않음)
			resp.sendError(HttpServletResponse.SC_NOT_FOUND);
			return; //위에 나가면 여기서 끝나야함
		}
		//commandHandler 있을때
		String viewName = handlerInvoker.InvokeHandler(mappingInfo, req, resp);
		
		if(viewName==null) {
			//이미 핸들러안에서 응답데이터 커밋됐는지
			//if(resp.isCommitted()) //응답데이터가 직렬화돼서 나감 (정상)
			if(!resp.isCommitted()) { //응답데이터가 나가지 않았는데 view의 데이터가 null로 돌아온경우 : 개발자가 코드 잘못짬
				resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,"View Layer가 결정되지 않았음"); //500에러(view결정안됨)
			}
			return; //viewName null이면 제어 여기서 끝나야함
		}
		//viewName null아닐때
		viewProcessor.viewProcess(viewName, req, resp);
	}
}
