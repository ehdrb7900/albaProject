package kr.or.ddit.alba.dao;

import java.util.List;

import kr.or.ddit.vo.GradeVO;
import kr.or.ddit.vo.LicenseVO;

public interface IOtherDAO {

	public List<GradeVO> selectGradeList();
	
	public List<LicenseVO> selectLicenseList();
}
