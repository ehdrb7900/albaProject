package kr.or.ddit.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;

/**
 * Factory Object[Method] Pattern
 *  
 *
 */
public class ConnectionFactory {
	static String url;
	static String user;
	static String password;
	static DataSource dataSource;
	
	static { //처음 한번만 실행됨
		
		//connection 생성정보 변경되면 dbInfo.properties만 고치면됨
		ResourceBundle bundle = ResourceBundle.getBundle("kr.or.ddit.db.dbInfo"); //baseName : 경로를 클래스패스 리소스형식으로?
		String driverClassName = bundle.getString("driverClassName");
		url = bundle.getString("url");
		user = bundle.getString("user");
		password = bundle.getString("password");
		/*
		try {
			//2. driver class loading -로딩하려고하는 드라이버 클래스name 넘겨줌
			Class.forName(driverClassName);
		} catch (ClassNotFoundException e1) {
			throw new RuntimeException(e1);
		} */
		
		BasicDataSource bds = new BasicDataSource(); //connection생성
		dataSource = bds;
		bds.setDriverClassName(driverClassName);
		bds.setUrl(url);
		bds.setUsername(user);
		bds.setPassword(password);
		
		//pooling 정책결정
		int initialSize = Integer.parseInt(bundle.getString("initialSize"));
		int maxTotal = Integer.parseInt(bundle.getString("maxTotal"));
		long maxWait = Long.parseLong(bundle.getString("maxWait"));
		bds.setInitialSize(initialSize); //초기사이즈 제한
		bds.setMaxTotal(maxTotal);	//최대몇개까지 만들것인지
		bds.setMaxWaitMillis(maxWait);//얼마동안 기다릴건지
	}
	
	public static Connection getConnection() throws SQLException{ //예외 팩토리 소비자(컨트롤러)가 가져감
		return dataSource.getConnection();
	}
}
