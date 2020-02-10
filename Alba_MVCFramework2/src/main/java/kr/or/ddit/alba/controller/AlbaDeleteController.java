package kr.or.ddit.alba.controller;

import java.io.IOException;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.alba.service.AlbaServiceImpl;
import kr.or.ddit.alba.service.IAlbaService;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.mvc.annotation.CommandHandler;
import kr.or.ddit.mvc.annotation.HttpMethod;
import kr.or.ddit.mvc.annotation.URIMapping;
import kr.or.ddit.vo.AlbaVO;

@CommandHandler
public class AlbaDeleteController {
	IAlbaService service = AlbaServiceImpl.getInstance();
	
	@URIMapping(value="/alba/albaDelete.do", method = HttpMethod.POST)
	public String delete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String alId = req.getParameter("al_id");
		if(StringUtils.isBlank(alId)) {
			resp.sendError(400, "아이디 누락");
			return null;
		}
		
		AlbaVO alba = new AlbaVO();
		alba.setAl_id(alId);    //(Integer.parseInt(alId));
		
		ServiceResult result = service.removeAlba(alba);
		String viewName = null;
		String message = null;
		switch (result) {
			case OK:
				viewName = "redirect:/alba/albaList.do";
				break;

			default:
				message = "서버 오류 쫌따 다시.";
				viewName = "redirect:/alba/albaView.do?what="+alba.getAl_id();
				break;
		}
		req.getSession().setAttribute("message", message);
		return viewName;
	}
}
