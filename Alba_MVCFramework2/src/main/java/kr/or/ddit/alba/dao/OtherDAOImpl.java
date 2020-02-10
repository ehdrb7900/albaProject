package kr.or.ddit.alba.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.mybatis.CustomSqlSessionFactoryBuilder;
import kr.or.ddit.vo.GradeVO;
import kr.or.ddit.vo.LicenseVO;

public class OtherDAOImpl implements IOtherDAO {

	SqlSessionFactory sqlSessionFactory = CustomSqlSessionFactoryBuilder.getSqlSessionFactory();
	
	@Override
	public List<GradeVO> selectGradeList() {
		try(
			SqlSession sqlSession = sqlSessionFactory.openSession();
		){
			IOtherDAO mapper = sqlSession.getMapper(IOtherDAO.class);
			return mapper.selectGradeList();
		}
	}

	@Override
	public List<LicenseVO> selectLicenseList() {
		try(
			SqlSession sqlSession = sqlSessionFactory.openSession();
		){
			IOtherDAO mapper = sqlSession.getMapper(IOtherDAO.class);
			return mapper.selectLicenseList();
		}
	}

}
