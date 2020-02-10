package kr.or.ddit.alba.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.alba.dao.IOtherDAO;
import kr.or.ddit.alba.dao.OtherDAOImpl;
import kr.or.ddit.alba.service.AlbaServiceImpl;
import kr.or.ddit.alba.service.IAlbaService;
import kr.or.ddit.mvc.annotation.CommandHandler;
import kr.or.ddit.mvc.annotation.HttpMethod;
import kr.or.ddit.mvc.annotation.URIMapping;
import kr.or.ddit.vo.AlbaVO;

@CommandHandler
public class AlbaUpdateController {

	IOtherDAO otherDAO = new OtherDAOImpl();
	IAlbaService service = AlbaServiceImpl.getInstance();
	
	private void addAttribute(HttpServletRequest req){
		req.setAttribute("gradeList", otherDAO.selectGradeList());
		req.setAttribute("licenseList", otherDAO.selectLicenseList());
	}
	
	@URIMapping(value = "/alba/albaUpdate.do")
	public String form(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String al_id = req.getParameter("what");
		if(!StringUtils.isBlank(al_id)) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "글번호 이상함.");
			return null;
		}
		AlbaVO alba = service.readAlba(al_id);
		req.setAttribute("alba", alba);
		addAttribute(req);
		return "alba/albaForm";
	}
	
	@URIMapping(value = "/alba/albaUpdate.do", method = HttpMethod.POST)
	public String update(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		String al_id = req.getParameter("al_no");
		if(!StringUtils.isBlank(al_id)) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "글번호 오류");
			return null;
		}
		
		AlbaVO alba = service.readAlba(al_id);
		req.setAttribute("alba", alba);
		return "alba/albaForm";
	}
}
