package kr.or.ddit.vo;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PagingVO<T> {	//페이징처리를 위한 VO

	public PagingVO(int screenSize, int blockSize) {
		super();
		this.screenSize = screenSize;
		this.blockSize = blockSize;
	}

	private int screenSize = 10;//한화면의 레코드 수
	private int blockSize = 5;	//한화면에 보여줄 페이지 수
	private int currentPage; 	//client의 parameter

	private int totalRecord;	//db 조회 후 전체 레코드 수 -쿼리문1 (cf.데이터리스트조회-쿼리문2)
	private int totalPage;		//총 페이지수

	// currentPage와 screenSize에 따라 결정되는 변수들
	private int startRow;	//시작 게시물번호
	private int endRow;		//종료 게시물번호

	//blockSize와 currentPage가 있으면 결정됨
	private int startPage;	//시작페이지
	private int endPage;	//종료페이지

	private List<T> dataList;	//목록을 의미 //<T>:범용적으로 사용하기 위해 제너릭타입!!(엘리먼트타입제한X)

	private SearchVO searchVO;	//일반검색 //pagingVO가 searchVO를 가지고 있다. has a 관계
	
	private T searchDetail;		//상세검색 //T : buyerVO로 들어오면 거래처검색할때 상세조건 담을 수 있음

	// totalRecord가 결정됐을 때
	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
		totalPage = (int) Math.ceil(totalRecord / (double) screenSize);// 나보다 큰값중에 제일 작은값 리턴
		// 천장값을 찾아내면 다운캐스팅 //나머지가 나오게끔 upcasting
	}

	// currentPage가 결정됐을 때
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
		endRow = screenSize * currentPage;
		startRow = endRow - (screenSize - 1);
		endPage = blockSize * ((currentPage + (blockSize - 1)) / blockSize);
		startPage = endPage - (blockSize - 1);
	}

	public String getPagingHTML() {
		StringBuffer html = new StringBuffer();
		html.append("<nav aria-label='...'>\n");
		html.append(" <ul class='pagination'>\n");
		html.append("\n");

		int lastPage = endPage > totalPage ? totalPage : endPage;// end or total page중 작은값

		if (startPage > blockSize) {// 이전구간 존재
			html.append("<li class='page-item'>\n");
			html.append("<a data-page='" + (startPage - blockSize) + "' class='page-link' href='?page="
					+ (startPage - blockSize) + "' tabindex='-1' aria-disabled='true'>Previous</a>\n");
			html.append("</li>\n");
		}
		for (int i = startPage; i <= lastPage; i++) {
			if (i == currentPage) { // 현재페이지는 링크 지원안됨
				html.append("<li class='page-item active' aria-current='page'>\n");
				html.append("<a data-page='" + i + "' class='page-link' href='#'>" + i
						+ "<span class='sr-only'>(current)</span></a>\n");
				html.append("</li>\n");
			} else {
				html.append("<li class='page-item'>\n");
				html.append("<a data-page='" + i + "' class='page-link' href='?page=" + i + "'>" + i + "</a>\n");
				html.append("</li>\n");
				// ? : 현재 브라우저의 주소창 사용
			}
		}
		if (lastPage < totalPage) {// 다음구간 존재
			html.append("<li class='page-item'>\n");
			html.append(" <a data-page='" + (lastPage + 1) + "'class='page-link' href='?page=" + (lastPage + 1)
					+ "'>Next</a>\n");
			html.append("</li>\n");
		}
		html.append(" </ul>\n");
		html.append("</nav>\n");

		return html.toString();
	}

}
