package kr.or.ddit.mvc.annotation;

/** 4.
 * @URIMapping 어노테이션 읽어서 그안의 정보를 캡슐화하기 위한 객체
 *
 */
public class URIMappingCondition {
	private String uri;
	private HttpMethod mothod;
	
	//생성자
	public URIMappingCondition(String uri, HttpMethod mothod) {
		super();
		this.uri = uri;
		this.mothod = mothod;
	}
	
	public String getUri() {
		return uri;
	}
	
	public HttpMethod getMothod() {
		return mothod;
	}
	
	//equlas : HandlerMapper에서 찾아야돼서
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((mothod == null) ? 0 : mothod.hashCode());
		result = prime * result + ((uri == null) ? 0 : uri.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		URIMappingCondition other = (URIMappingCondition) obj;
		if (mothod != other.mothod)
			return false;
		if (uri == null) {
			if (other.uri != null)
				return false;
		} else if (!uri.equals(other.uri))
			return false;
		return true;
	}

	//log찍기 편하게
	@Override
	public String toString() {
		return String.format("[uri : %s, method : %s]", uri, mothod);
	}
	
	
}
