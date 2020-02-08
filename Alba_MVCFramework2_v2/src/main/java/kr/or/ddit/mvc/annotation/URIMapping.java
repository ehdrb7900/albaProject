package kr.or.ddit.mvc.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/** 3.
 * 컨트롤러 클래스의 특정 요청을 처리할 수 있는 핸들러 메서드에 사용
 * 특정 요청 매핑 방법(주소, http method)
 *
 */
@Target(ElementType.METHOD) //핸들러 역할 하는 메서드에 사용
@Retention(RetentionPolicy.RUNTIME) //retention없으면 source로 설정되어있음
public @interface URIMapping {
	String value();
	HttpMethod method() default HttpMethod.GET; //optional => default get으로 설정
}
