package kr.or.ddit.vo;

import java.io.IOException;
import java.util.List;

import org.apache.commons.codec.binary.Base64;

import kr.or.ddit.file.PartWrapper;
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
	
	private byte[] al_img;
	private PartWrapper al_image;
	
	
	public void setAl_image(PartWrapper al_image) throws IOException {
		this.al_image = al_image;
		if(al_image!=null)
			al_img = al_image.getBytes();
	}
	
	public String getImgBase64() {
		if(al_image==null) return null;
		return Base64.encodeBase64String(al_img);
	}
	
	
	private List<LicenseVO> licenseList; //albaVO가 LicenseVO를 가지는 has many관계
	private List<Lic_AlbaVO> lic_AlbaList;
	private GradeVO grade; //albaVO가 GradeVO 가지는 has a 관계 (1:1)
	
	
	//delete 
	private int rowcnt;
	private int[] delAttNos;
	
}
