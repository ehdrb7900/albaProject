package kr.or.ddit.mvc.annotation;

import javax.servlet.http.HttpServletRequest;

public interface IHandlerMapper {
	/** 6.
	 * 특정 요청을 처리할 수 있는 핸들러에 대한 정보 검색
	 * @param req
	 * @return
	 */
	public URIMappingInfo findCommandHandler(HttpServletRequest req);
}
