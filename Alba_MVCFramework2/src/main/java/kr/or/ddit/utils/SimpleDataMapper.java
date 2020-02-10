package kr.or.ddit.utils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SimpleDataMapper {
	public static List queryForList(ResultSet rs, Class resultClass) throws SQLException{		
		List list = new ArrayList();
		while(rs.next()) {
			
			// 리스트 안에 들어갈 수 있는 엘리먼트가 만들어짐
			list.add(queryForObject(rs, resultClass));
		}
		return list;
	}
		
	public static Object queryForObject(ResultSet rs, Class resultClass) throws SQLException { //class제한없앰
	//rs데이터를 resultClass에 넣으려고함	
		try {
			Object result = resultClass.newInstance();  //member.setMem_id(rs.getString("MEM_ID"));에서 member
			
			Field[] fields =  resultClass.getDeclaredFields();
			for(Field tmp : fields) {
				String propName = tmp.getName(); //필드명
				PropertyDescriptor pd = new PropertyDescriptor(propName, resultClass);
				Method setter = pd.getWriteMethod();
				
				Object value = null;
				try {
					if(tmp.getType().equals(String.class)) { //type이 문자면 getString으로 꺼냄
						value = rs.getString(propName); //컬럼명넘김, 컬럼명은 필드명과 동일(대소문자구분x)
					}else if(tmp.getType().equals(Integer.class)) {
						value = rs.getInt(propName);
					}
				}catch (Exception e) { //해당 컬럼이 없는경우 exception
					continue;
				}
				setter.invoke(result, value);
			}
			return result;
		}catch (Exception e) {
			throw new SQLException(e); //모든 예외를 sqlexception으로 바꿈
		}
		
		/*
		member = new MemberVO();
		member.setMem_id(rs.getString("MEM_ID"));
		member.setMem_pass(rs.getString("MEM_PASS"));
		member.setMem_name(rs.getString("MEM_NAME"));
		member.setMem_regno1(rs.getString("MEM_REGNO1"));
		member.setMem_regno2(rs.getString("MEM_REGNO2"));
		*/
		
	}
}
