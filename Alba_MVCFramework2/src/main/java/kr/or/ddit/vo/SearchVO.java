package kr.or.ddit.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor //생성자1
@NoArgsConstructor //생성자2
public class SearchVO {
	private String searchType;
	private String searchWord;
}
