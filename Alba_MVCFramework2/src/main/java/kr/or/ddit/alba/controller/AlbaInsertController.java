package kr.or.ddit.alba.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.alba.dao.IOtherDAO;
import kr.or.ddit.alba.dao.OtherDAOImpl;
import kr.or.ddit.alba.service.AlbaServiceImpl;
import kr.or.ddit.alba.service.IAlbaService;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.hint.InsertHint;
import kr.or.ddit.mvc.annotation.CommandHandler;
import kr.or.ddit.mvc.annotation.HttpMethod;
import kr.or.ddit.mvc.annotation.URIMapping;
import kr.or.ddit.validator.GeneralValidator;
import kr.or.ddit.vo.AlbaVO;

@CommandHandler
public class AlbaInsertController {

	IOtherDAO otherDAO = new OtherDAOImpl();
	IAlbaService service = AlbaServiceImpl.getInstance();
	
	private void addAttribute(HttpServletRequest req){
		req.setAttribute("gradeList", otherDAO.selectGradeList());
		req.setAttribute("licenseList", otherDAO.selectLicenseList());
	}
	
	@URIMapping(value="/alba/albaInsert.do")
	public String goInsertAlba(HttpServletRequest req, HttpServletResponse resp) {
	
		addAttribute(req);
		return "alba/albaForm";
	}
	
	@URIMapping(value="/alba/albaInsert.do", method = HttpMethod.POST)
	public String insertAlba(HttpServletRequest req, HttpServletResponse resp) {
		
		String alNoParam = req.getParameter("al_no");
		if(!StringUtils.isBlank(alNoParam)) {
			return "/alba/albaUpdate.do";
		}
		
		AlbaVO alba = new AlbaVO();
		req.setAttribute("alba", alba);
		
		try {	//리플렉션
			BeanUtils.populate(alba, req.getParameterMap());
		} catch (IllegalAccessException | InvocationTargetException e) {
			throw new RuntimeException(e);
		}
		
		
//		if(req instanceof FileUploadRequestWrapper) {
//			List<PartWrapper> lic_img = ((FileUploadRequestWrapper) req).getPartWrappers("lic_img");
//			
//			if(lic_img !=null && lic_img.size()>0) {
//				File saveFolder = new File("D:\\saveFiles");
//				
//				List<Lic_AlbaVO> licList = new ArrayList<>();
//				for(PartWrapper tmp : lic_img) {
////					1) 2진
//					tmp.saveFile(saveFolder);
//					//2) 메타
//					Lic_AlbaVO lic_AlbaVO = new Lic_AlbaVO(tmp);
//					licList.add(attatchVO);
//				}
//				alba.setAttatchList(attatchList);
//			}
//		}
		
		
		Map<String, List<CharSequence>> errors = new HashMap<>();
		req.setAttribute("errors", errors);
		String goPage = null;
		String message = null;
		
		GeneralValidator validator = new GeneralValidator();
		boolean valid = validator.validate(alba, errors, InsertHint.class);	
		if(valid) {
			
			ServiceResult result = service.createAlba(alba);
			switch (result) {
				case OK:
					goPage = "redirect:/alba/albaView.do?who="+alba.getAl_id();
					break;
	
				default: 
					message = "서버에 오류가 있습니다. 잠시 후에 다시 시도해주세요.";
					goPage = "alba/albaForm";
					break;
			}
		}else {
			goPage = "alba/albaForm";
		}
		req.setAttribute("message", message);
		
		return goPage;
	}
}
