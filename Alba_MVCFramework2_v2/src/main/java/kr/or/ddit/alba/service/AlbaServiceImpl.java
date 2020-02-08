package kr.or.ddit.alba.service;

import java.util.List;

import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.alba.dao.AlbaDAOImpl;
import kr.or.ddit.alba.dao.IAlbaDAO;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.exception.CommonException;
import kr.or.ddit.mybatis.CustomSqlSessionFactoryBuilder;
import kr.or.ddit.vo.AlbaVO;
import kr.or.ddit.vo.PagingVO;

public class AlbaServiceImpl implements IAlbaService {
	IAlbaDAO albaDAO = AlbaDAOImpl.getInstance();
	
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
		// TODO Auto-generated method stub
		return null;
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
		// TODO Auto-generated method stub
		return null;
	}

}
