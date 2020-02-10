package kr.or.ddit.alba.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.alba.service.AlbaServiceImpl;
import kr.or.ddit.alba.service.IAlbaService;
import kr.or.ddit.mvc.annotation.CommandHandler;
import kr.or.ddit.mvc.annotation.URIMapping;
import kr.or.ddit.vo.AlbaVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.SearchVO;

@CommandHandler
public class AlbaRetrieveController {
	IAlbaService service = AlbaServiceImpl.getInstance();
	
	@URIMapping(value = "/alba/albaList.do")
	public String list(HttpServletRequest req, HttpServletResponse resp) throws IOException{
		//Accpet헤더꺼내서 안에 json있는지 =>있으면 비동기요청
				String accept = req.getHeader("Accept");
						
				String pageParam = req.getParameter("page");
				
				String searchType = req.getParameter("searchType");
				String searchWord = req.getParameter("searchWord");
				SearchVO searchVO = new SearchVO(searchType, searchWord);
				
				int currentPage = 1;
				if(StringUtils.isNumeric(pageParam)) {
					currentPage = Integer.parseInt(pageParam);
				}
				
				PagingVO<AlbaVO> pagingVO = new PagingVO<>();
				
				pagingVO.setSearchVO(searchVO);
				pagingVO.setTotalRecord(service.readAlbaCount(pagingVO));
				pagingVO.setCurrentPage(currentPage);
				
				List<AlbaVO> albaList = service.readAlbaList(pagingVO);
				pagingVO.setDataList(albaList);
				
				String viewName = null;
				if(StringUtils.containsIgnoreCase(accept, "json")) {
					resp.setContentType("application/json;charset=UTF-8");
					ObjectMapper mapper = new ObjectMapper();
					mapper.writeValue(resp.getWriter(), pagingVO);
					return null;
				}else {
					req.setAttribute("pagingVO", pagingVO);
					viewName = "alba/albaList";
				}
				return viewName;
	}
	
	@URIMapping(value = "/alba/albaView.do")
	public String view(HttpServletRequest req, HttpServletResponse resp) throws IOException{
		String al_id = req.getParameter("what");
		
		//검증
		if(!StringUtils.isNotBlank(al_id)) { 
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		}
		
		AlbaVO alba = service.readAlba(al_id);
		req.setAttribute("alba", alba);
		return "alba/albaView";
	}
	
}
