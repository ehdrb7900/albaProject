package kr.or.ddit.utils;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * 쿠키 생성과 핸들링을 지원하는 유틸리티
 *
 */
public class CookieUtils {
	public static String encoding = "UTF-8"; //class변수
	private Map<String, Cookie> cookieMap;
	private HttpServletRequest request;
	
	//객체지향 5원칙 중 D-의존 역전원칙
	//전략패턴 구현
	//두번째 주입방식 : 생성자 주입
	public CookieUtils(HttpServletRequest request) {
		super();
		this.request = request;
		Cookie[] cookies =  request.getCookies();
		
		cookieMap = new LinkedHashMap<>();
		if(cookies!=null) {
			for(Cookie tmp : cookies) { //모든 쿠키 접근 가능
				//비어있는 Map채우기
				cookieMap.put(tmp.getName(), tmp);
			}
		}
	}
	
	public Map<String, Cookie> getCookieMap() {
		return cookieMap;
	}
	
	/**
	 * @param name 검색할 쿠키명
	 * @return 검색된 쿠키, 존재하지 않으면 null반환
	 */
	public Cookie getCookie(String name){ //객체 생성된 이후에만 사용가능(static X)
		return cookieMap.get(name);
	}
	
	/**
	 * @param name 검색할 쿠키명
	 * @return 검색된 쿠키의 값, 없으면 null반환, 있으면 UTF-8
	 * @throws IOException
	 */
	public String getCookieValue(String name) throws IOException{
		Cookie cookie = getCookie(name);
		
		String value=null;
		if(cookie!=null) {
			value = URLDecoder.decode(cookie.getValue(), encoding); 
		}
		return value;
	}
	
/*//전략패턴 구현 첫번째 -setter injection 
	public void setRequest(HttpServletRequest request) { 
		this.request = request;
	}
*/
	/*필요없음
	public static void setEncoding(String encoding) { //setter //setEncoding을통해 encoding바꿀 수 있음
		CookieUtils.class.encoding = encoding;
		//클래스로더를 통해 접근
	}
	*/
	
	/**쿠키 생성
	 * @param name 쿠키명
	 * @param value 쿠키값(기본인코딩 UTF-8)
	 * @return 생성된 쿠키
	 * @throws IOException
	 */
	public static Cookie createCookie(String name, String value) throws IOException {
													//UnsupportedEncodingException은 IOException의 하위구조(포괄적인 예외처리)
		value = URLEncoder.encode(value, encoding);
		Cookie cookie = new Cookie(name, value);
		return cookie;
	}
	
	//enum-데이터의 식별성 부여
	public static enum TextType{DOMAIN, PATH}
	
	
	/**쿠키 생성
	 * @param name 쿠키명
	 * @param value 쿠키값(기본인코딩 UTF-8)
	 * @param text 도메인이나 경로로 사용될 텍스트
	 * @param kind 텍스트 사용 속성을 결정할 기준
	 * @return 생성된 쿠키
	 * @throws IOException
	 */
	//다형성의 원리 중 overloding : 같은 이름의 메서드 여러개, 넘기는 데이터 개수나 타입 달라야함, 상호간의 호출 가능
	public static Cookie createCookie(String name, String value, String text, TextType kind) throws IOException{
		
//		Cookie cookie = new Cookie(name, value);	//새로 생성할 필요X
		Cookie cookie = createCookie(name, value);	//오버로딩 메서드는 상호간 호출 가능
		
		if(TextType.DOMAIN.equals(kind)) { //if(kind) : kind=true(데이터 자체에 식별성X,정하기나름)면
			cookie.setDomain(text);		// text를 도메인으로 사용
			
		}else if(TextType.PATH.equals(kind)){
			cookie.setPath(text);
		}
		return cookie;
	}
	
	
	/**
	 * 쿠키생성
	 * @param name 쿠키명
	 * @param value 쿠키값(기본인코딩 UTF-8)
	 * @param domain 도메인
	 * @param path 경로
	 * @return 생성된 쿠키
	 * @throws IOException
	 */
	//domain,path모두 사용
	public static Cookie createCookie(String name, String value, String domain, String path) throws IOException{
		
		Cookie cookie = createCookie(name, value);	//오버로딩 메서드는 상호간 호출 가능
		
		cookie.setDomain(domain);
		cookie.setPath(path);
		
		return cookie;
	}
	
	/**쿠키 생성
	 * @param name 쿠키명
	 * @param value 쿠키값(기본인코딩 UTF-8)
	 * @param maxAge 초단위 만료시간
	 * @return 생성된 쿠키
	 * @throws IOException 
	 */
	public static Cookie createCookie(String name, String value, int maxAge) throws IOException{
		Cookie cookie = createCookie(name, value);
		cookie.setMaxAge(maxAge);
		return cookie;
	}
	

	/**쿠키 생성
	 * @param name 쿠키명
	 * @param value 쿠키값(기본인코딩 UTF-8)
	 * @param text 도메인이나 경로로 사용될 텍스트
	 * @param kind 텍스트 사용 속성을 결정할 기준
	 * @param maxAge 초단위 만료시간
	 * @return 생성된 쿠키
	 * @throws IOException
	 */
	public static Cookie createCookie(String name, String value, String text, TextType kind, int maxAge) throws IOException{
		Cookie cookie = createCookie(name, value, text, kind);
		cookie.setMaxAge(maxAge);
		return cookie;
	}
	
	/**쿠키 생성
	 * @param name 쿠키명
	 * @param value 쿠키값(기본인코딩 UTF-8)
	 * @param domain 도메인 
	 * @param path 경로
	 * @param maxAge 초단위 만료시간
	 * @return 생성된 쿠키
	 * @throws IOException
	 */
	public static Cookie createCookie(String name, String value, String domain, String path, int maxAge) throws IOException{
		Cookie cookie = createCookie(name, value, domain, path);
		cookie.setMaxAge(maxAge);
		return cookie;
	}
}
