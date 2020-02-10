package kr.or.ddit.vo;

import kr.or.ddit.validator.rules.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(of = "gr_code")
public class GradeVO {
	@NotBlank
	private String gr_code;
	@NotBlank
	private String gr_name;
}
