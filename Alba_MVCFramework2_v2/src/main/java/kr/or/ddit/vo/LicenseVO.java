package kr.or.ddit.vo;

import kr.or.ddit.validator.rules.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(of = "lic_code")
public class LicenseVO {
	@NotBlank
	private String lic_code;
	@NotBlank
	private String lic_name;
}
