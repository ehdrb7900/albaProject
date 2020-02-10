package kr.or.ddit.mvc.annotation;

import java.lang.reflect.Method;

/** 5.
 * 어떤 요청과 그 요청을 처리할 수 있는 핸들러(핸들러 객체, 핸들러 메서드)에 대한 정보 캡슐화
 *
 */
public class URIMappingInfo {
	private URIMappingCondition mappingCondition; //모든정보
	private Object commandHandler; //핸들러 객체에 대한 정보 //commandHandler가 다양하기때문에 다양성 보장 : Object타입
	private Method handlerMethod; //같은 핸들러 안에있는 메서드 구분
	
	//생성자
	public URIMappingInfo(URIMappingCondition mappingCondition, Object commandHandler, Method handlerMethod) {
		super();
		this.mappingCondition = mappingCondition;
		this.commandHandler = commandHandler;
		this.handlerMethod = handlerMethod;
	}

	//Getter만 -> immutable객체로 운영
	public URIMappingCondition getMappingCondition() {
		return mappingCondition;
	}

	public Object getCommandHandler() {
		return commandHandler;
	}

	public Method getHandlerMethod() {
		return handlerMethod;
	}
	
	@Override
	public String toString() {
		return String.format("%s.%s", commandHandler.getClass().getName(), handlerMethod.getName()); //어떤클래스가 갖고있는 어떤 메서드
	}
}
