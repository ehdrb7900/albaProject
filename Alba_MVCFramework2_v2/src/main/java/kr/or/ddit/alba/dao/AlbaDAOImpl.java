package kr.or.ddit.alba.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.mybatis.CustomSqlSessionFactoryBuilder;
import kr.or.ddit.vo.AlbaVO;
import kr.or.ddit.vo.PagingVO;

public class AlbaDAOImpl implements IAlbaDAO{
	SqlSessionFactory sqlSessionFactory =
			CustomSqlSessionFactoryBuilder.getSqlSessionFactory();

	private static AlbaDAOImpl instance;
	private AlbaDAOImpl() {}
	public static AlbaDAOImpl getInstance() {
		if(instance==null) instance = new AlbaDAOImpl();
		return instance;
	}
	
	@Override
	public int insertAlba(AlbaVO alba) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public AlbaVO selectAlba(String al_id) {
		try(
				SqlSession sqlSession = sqlSessionFactory.openSession();
			){
				IAlbaDAO mapper = sqlSession.getMapper(IAlbaDAO.class);
				return mapper.selectAlba(al_id);
			}
	}

	@Override
	public int selectAlbaCount(PagingVO pagingVO) {
		try(
				SqlSession sqlSession = sqlSessionFactory.openSession();
			){
				IAlbaDAO mapper = sqlSession.getMapper(IAlbaDAO.class);
				return mapper.selectAlbaCount(pagingVO);
			}
	}

	@Override
	public List<AlbaVO> selectAlbaList(PagingVO pagingVO) {
		try(
				SqlSession sqlSession = sqlSessionFactory.openSession();
			){
				IAlbaDAO mapper = sqlSession.getMapper(IAlbaDAO.class);
				return mapper.selectAlbaList(pagingVO);	
			}
	}

	@Override
	public int updateAlba(AlbaVO alba) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateAlba(AlbaVO alba, SqlSession sqlSession) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteAlba(AlbaVO alba) {
		// TODO Auto-generated method stub
		return 0;
	}
/*
	@Override
	public int deleteAlba(AlbaVO alba, SqlSession sqlSession) {
		// TODO Auto-generated method stub
		return 0;
	}
*/
	@Override
	public int insertAlba(AlbaVO alba, SqlSession sqlSession) {
		// TODO Auto-generated method stub
		return 0;
	}

}
