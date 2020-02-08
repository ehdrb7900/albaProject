package kr.or.ddit.alba.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.vo.AlbaVO;
import kr.or.ddit.vo.PagingVO;


public interface IAlbaDAO {
	
	public int insertAlba(AlbaVO alba);
	public int insertAlba(AlbaVO alba, SqlSession sqlSession);
	
	public AlbaVO selectAlba(String al_id);
	
	public int selectAlbaCount(PagingVO pagingVO);
	
	public List<AlbaVO> selectAlbaList(PagingVO pagingVO);
	
	public int updateAlba(AlbaVO alba);
	public int updateAlba(AlbaVO alba, SqlSession sqlSession);
	
	public int deleteAlba(AlbaVO alba);
//	public int deleteAlba(AlbaVO alba, SqlSession sqlSession);
}
