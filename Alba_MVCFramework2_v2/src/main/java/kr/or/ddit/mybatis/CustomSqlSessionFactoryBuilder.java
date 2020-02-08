package kr.or.ddit.mybatis;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class CustomSqlSessionFactoryBuilder {
	private static SqlSessionFactory sqlSessionFactory; //application을 대상으로한 singleton
	
	static { //한번만 실행
		String resource = "kr/or/ddit/mybatis/Configuration.xml";
		try(
			Reader reader = Resources.getResourceAsReader(resource);
		) {
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static SqlSessionFactory getSqlSessionFactory() { //이 안에서 만들어지는 Factory 외부에서 가져갈 수 있음
		return sqlSessionFactory;
	}
}
