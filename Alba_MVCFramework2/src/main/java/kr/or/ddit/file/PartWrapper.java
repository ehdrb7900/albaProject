package kr.or.ddit.file;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

import javax.servlet.http.Part;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(exclude = "part") //part는 출력X
public class PartWrapper {
	public PartWrapper(Part part){
		this.part = part;
		filename = getOriginalName(part);
		savename = UUID.randomUUID().toString(); // unique identifier : 1c184b16-4390-4e2b-a404-130fa1d6095c로 저장
		filesize = part.getSize(); //파일사이즈
		mime = part.getContentType(); 
		fancySize = FileUtils.byteCountToDisplaySize(filesize);
		
	}
	
	private Part part;
	private String filename;
	private String savename; //파일 저장할때 이름
	private long filesize;
	private String mime;
	private String fancySize;
	
	public byte[] getBytes() throws IOException { //바이트 배열을 받아오는 메서드
		try(//return직전에 자동은로 close
			InputStream inputStream = part.getInputStream();
		){
			return IOUtils.toByteArray(inputStream);
		}
	}
	
	public void saveFile(File saveFolder) throws IOException{ //파일을 위치에 저장하는 코드
		File saveFile = new File(saveFolder, savename); //확장자 사라짐
		
		try(
			InputStream input = part.getInputStream();
			FileOutputStream output = new FileOutputStream(saveFile);
		){
			IOUtils.copy(input, output); //byte단위로
		}
	}
	
	public void delete() throws IOException {
		part.delete();
	}

	private String getOriginalName(Part part) {
		
		String disposition = part.getHeader("Content-Disposition");
		int fromIndex = disposition.indexOf("filename=");
		int qtFirst = disposition.indexOf('"',fromIndex); //filename="test.exe"의 "
		int qtLast = disposition.indexOf('"',qtFirst+1); //마지막 "

		return disposition.substring(qtFirst+1, qtLast); //원본파일명 나옴
	}
	
	
}
