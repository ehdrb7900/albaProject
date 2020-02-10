package kr.or.ddit.mvc.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/** 2.
 * 특정 요청을 처리할 수 있는 Backend Controller 마킹.
 *
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME) //스캔할때까지 살아있어야함
public @interface CommandHandler {
	
}
