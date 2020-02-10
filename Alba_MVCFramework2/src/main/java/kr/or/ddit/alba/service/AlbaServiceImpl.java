package kr.or.ddit.alba.service;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.alba.dao.AlbaDAOImpl;
import kr.or.ddit.alba.dao.IAlbaDAO;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.exception.CommonException;
import kr.or.ddit.mybatis.CustomSqlSessionFactoryBuilder;
import kr.or.ddit.vo.AlbaVO;
import kr.or.ddit.vo.Lic_AlbaVO;
import kr.or.ddit.vo.PagingVO;

public class AlbaServiceImpl implements IAlbaService {
	IAlbaDAO albaDAO = AlbaDAOImpl.getInstance();
	
	File saveFolder = new File("d:/saveFiles");
	
	private AlbaServiceImpl() {}
	private static AlbaServiceImpl instance;
	public static AlbaServiceImpl getInstance() {
		if(instance==null) instance = new AlbaServiceImpl();
		return instance;
	}
	
	//트랜젝션 관리위해
	SqlSessionFactory sqlSessionFactory = CustomSqlSessionFactoryBuilder.getSqlSessionFactory();
	
	@Override
	public ServiceResult createAlba(AlbaVO alba) {
		ServiceResult result = ServiceResult.FAIL;
		try(
			SqlSession sqlSession = sqlSessionFactory.openSession(false);
		){
			int cnt = albaDAO.insertAlba(alba, sqlSession);
			if(cnt > 0) {
				List<Lic_AlbaVO> lic_AlbaList = alba.getLic_AlbaList();
				if(lic_AlbaList !=null && lic_AlbaList.size() > 0) {
					// lic_licence 테이블에 데이터랑 이미지 넣는 insert
				}
				sqlSession.commit();
				result = ServiceResult.OK;
			}
		}
		return result;
	}

	@Override
	public List<AlbaVO> readAlbaList(PagingVO<AlbaVO> pagingVO) {
		return albaDAO.selectAlbaList(pagingVO);
	}

	@Override
	public int readAlbaCount(PagingVO<AlbaVO> pagingVO) {
		return albaDAO.selectAlbaCount(pagingVO);
	}

	@Override
	public AlbaVO readAlba(String al_id) {
		AlbaVO alba = albaDAO.selectAlba(al_id);
		
		if(alba==null) {
			throw new CommonException(al_id+"아이디를 가진 회원이없음"); 
		}
		return alba;
	}

	@Override
	public ServiceResult modifyAlba(AlbaVO alba) {
		// TODO Auto-generated method stub
		return null;
	}
	


	@Override
	public ServiceResult removeAlba(AlbaVO alba) {
		
		ServiceResult result = null;
		
		try(
			SqlSession sqlSession = sqlSessionFactory.openSession();
		){
			result = ServiceResult.FAIL;
			albaDAO.deleteAlba(alba, sqlSession);
			int rowcnt = alba.getRowcnt();
			
			
			if(rowcnt>0) { 
				result = ServiceResult.OK;
				sqlSession.commit();
			}
			
		}
		return result;
		
	}

}
