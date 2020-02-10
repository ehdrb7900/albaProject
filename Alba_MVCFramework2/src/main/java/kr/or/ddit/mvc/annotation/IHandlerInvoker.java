package kr.or.ddit.mvc.annotation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface IHandlerInvoker {
	/** 7.
	 * 핸들러 객체와 핸들러 메서드를 직접 사용(호출)
	 * @param mappingInfo
	 * @param req
	 * @param resp
	 * @return
	 */
	public String InvokeHandler(URIMappingInfo mappingInfo, HttpServletRequest req, HttpServletResponse resp); 
	//list메서드와 view메서드 호출됨(어떤view를 쓰겠다는 정보). 리턴타입 String
	//URIMappingInfo :핸들러에 대한 정보
}
