package kr.or.ddit.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;

public class TemplateUtils {
	public static StringBuffer readTemplate(String filePath) throws IOException {
		
		URL targetURL = TemplateUtils.class.getResource(filePath);
	
		File targetFile = new File(targetURL.getFile());
		FileReader reader = new FileReader(targetFile);			//한번에 한글자씩읽음 -> 느림
		BufferedReader bufReader = new BufferedReader(reader); 	//한번에 한줄씩 읽음(2차 스트림)
		StringBuffer html = new StringBuffer();
		String tmp = null;										//읽은 한줄 관리하는 임시변수
		
		while((tmp = bufReader.readLine())!=null){	//더이상 읽을게 없을때->null //while(null이 아닌동안)
			html.append(tmp+"\n");
		}
		return html;
	}
}
