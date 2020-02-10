package kr.or.ddit.mvc;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface IViewProcessor {
	/**
	 * view layer의 위치 공통성을 활용 (String view = "/WEB-INF/views/member/memberView.jsp";에서 /WEB-INF/views 항상 같음)
	 * @param prefix
	 */
	public void setPrefix(String prefix);
	
	/**
	 * view layer 파일의 확장자 공통성을 활용 (~.jsp 항상 같음)
	 * @param suffix
	 */
	public void setSuffix(String suffix);
	
	 /**
	 * viewName을 이용해서 view layer로 이동하는 코드를 가짐
	 * viewName이 "redirect:"으로 시작하면, redirect로 이동.
	 * @param viewName
	 * @param req
	 * @param resp
	 */
	public void viewProcess(String viewName, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException; //viewName으로 forward나 redirect으로 이동
	 //이동하는거기때문에 리턴값 돌려줄필요X
}
