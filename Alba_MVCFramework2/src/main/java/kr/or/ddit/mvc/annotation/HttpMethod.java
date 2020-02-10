package kr.or.ddit.mvc.annotation;
//1
public enum HttpMethod {
	GET, POST, PUT, DELETE, 
	HEAD /* body없는상태 응답데이터 */, 
	OPTIONS /* preplight요청으로 메서드 지원여부 확인 */, 
	TRACE /* 서버 디버깅 목적 */
}
