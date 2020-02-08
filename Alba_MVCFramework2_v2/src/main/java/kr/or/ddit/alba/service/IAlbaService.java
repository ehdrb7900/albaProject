package kr.or.ddit.alba.service;

import java.util.List;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.AlbaVO;
import kr.or.ddit.vo.PagingVO;

public interface IAlbaService {
	
	public ServiceResult createAlba(AlbaVO alba); //1.성공 2.실패
	
	public List<AlbaVO> readAlbaList(PagingVO<AlbaVO> pagingVO);
	
	public int readAlbaCount(PagingVO<AlbaVO> pagingVO);
	
	public AlbaVO readAlba(String al_id); //조회하려는알바가 삭제되고없으면 CommonException
	
	public ServiceResult modifyAlba(AlbaVO alba);
	
	public ServiceResult removeAlba(AlbaVO alba);
}
