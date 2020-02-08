package kr.or.ddit.mvc;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//8. redirect , Dispath 운영
public class ViewProcessor implements IViewProcessor {
	private String prefix = "";
	private String suffix = "";
	
	@Override
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	@Override
	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	@Override
	public void viewProcess(String viewName, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{

		if(viewName.startsWith("redirect:")) { //"redirect:" 로 시작하면 redirect방식
			viewName = viewName.substring("redirect:".length()); //"redirect:" 자름
			resp.sendRedirect(req.getContextPath()+ viewName);
		}else {
			req.getRequestDispatcher(prefix+viewName+suffix).forward(req, resp);
		}
	}
}
