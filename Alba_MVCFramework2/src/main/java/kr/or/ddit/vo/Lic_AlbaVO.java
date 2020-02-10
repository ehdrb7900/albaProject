package kr.or.ddit.vo;

import kr.or.ddit.validator.rules.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(of = "al_id")
public class Lic_AlbaVO {
	@NotBlank
	private String al_id;
	@NotBlank
	private String lic_code;
	private String lic_image;
}
