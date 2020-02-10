package kr.or.ddit.file;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.Part;

import org.apache.commons.lang3.StringUtils;

public class FileUploadRequestWrapper extends HttpServletRequestWrapper {
	private Map<String , List<PartWrapper>> partWrapperMap;
	
	//생성자
	public FileUploadRequestWrapper(HttpServletRequest request) throws IOException, ServletException {
		super(request);
		partWrapperMap = new LinkedHashMap<>();
		//현재 원본요청에 포함된 모든 part꺼내서 partWrapperMap안에 넣음
		parseRequest(request);
		
	}

	private void parseRequest(HttpServletRequest request) throws IOException, ServletException {
		Collection<Part> parts = request.getParts();
		Iterator<Part> it = parts.iterator();
		while (it.hasNext()) {
			Part part = (Part) it.next();
			String contentType =  part.getContentType(); //파일은 content-Type이 있고, text는 contentTyp이 없음
			if(contentType==null) continue; //contentTyp이 없으면 문자 -> wrapper를 만들필요x
			
			String partName = part.getName();
			
			List<PartWrapper> already = partWrapperMap.get(partName);
			if(already==null) {
				already = new ArrayList<PartWrapper>();
				partWrapperMap.put(partName, already);
			}
			
			PartWrapper wrapper = new PartWrapper(part);
			if(StringUtils.isBlank(wrapper.getFilename())) continue; //원본파일명이 비어있으면 add시킬필요X
			
			already.add(wrapper);
		}
	}
	
	public Map<String, List<PartWrapper>> getPartWrapperMap() {
		return partWrapperMap;
	}

	public PartWrapper getParWrapper(String partName){
		List<PartWrapper> wrappers = partWrapperMap.get(partName);
		if(wrappers!=null&&wrappers.size()>0) {
			return wrappers.get(0);
		}else {
			return null;
		}
	}
	
	public List<PartWrapper> getPartWrappers(String PartName){
		return partWrapperMap.get(PartName);
	}
	
	public Enumeration<String> getPartNames(){
		Iterator<String> it =  partWrapperMap.keySet().iterator();
		return new Enumeration<String>() {
			@Override
			public boolean hasMoreElements() {
				// TODO Auto-generated method stub
				return it.hasNext();
			}
			
			@Override
			public String nextElement() {
				// TODO Auto-generated method stub
				return it.next();
			}
		};
	}
	
	public void deleteAll() throws IOException{
		for(Entry<String, List<PartWrapper>> entry : partWrapperMap.entrySet()) {
			List<PartWrapper> wrappers = entry.getValue();
			for(PartWrapper tmp : wrappers) {
				tmp.delete();
			}
		}
	}
}
