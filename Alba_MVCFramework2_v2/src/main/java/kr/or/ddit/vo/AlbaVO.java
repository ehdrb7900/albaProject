package kr.or.ddit.vo;

import java.util.List;

import kr.or.ddit.validator.rules.constraints.NotBlank;
import kr.or.ddit.validator.rules.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(of ="al_id")
public class AlbaVO {
	private int rnum;
	@NotBlank
	private String al_id;
	@NotBlank
	private String al_name;
	@NotNull
	private Integer al_age;
	@NotBlank
	private String al_address;
	@NotBlank
	private String al_hp;
	private String al_spec;
	private String al_desc;
	@NotBlank
	private String gr_code;
	private String al_career;
	@NotBlank
	private String al_gen;
	@NotBlank
	private String al_btype;
	@NotBlank
	private String al_mail;
	
	private List<LicenseVO> licenseList; //albaVO가 LicenseVO를 가지는 has many관계
	private GradeVO grade; //albaVO가 GradeVO 가지는 has a 관계 (1:1)
}
