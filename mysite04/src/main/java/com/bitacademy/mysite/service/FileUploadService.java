package com.bitacademy.mysite.service;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Calendar;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bitacademy.mysite.exception.FileUploadServiceException;

@Service
public class FileUploadService {
	private static String RESTORE_PATH = "/mysite-uploads";
	private static String URL_BASE = "/gallery/images"; 
	 // 서버 위에서 실행되므로 파일(저장 디렉토리)은 tomcat 외부에 저장해야함 (내 디렉토리와 서버 디렉토리는 당연히 다름)
	 // 위와 같이 하면 URL을 딸 수 없음 -> 가상의 URL과 매핑하기(spring-servlet에서 resource mapping 설정 필요)

	public String restore(MultipartFile multipartFile) throws FileUploadServiceException {
		String url = null;
		
		try {
			if(multipartFile.isEmpty()) {
				return url;
			}
			
			File restoreDirectory = new File(RESTORE_PATH);
			if(!restoreDirectory.exists()) {
				restoreDirectory.mkdirs();
			}
			
			String originalFilename = multipartFile.getOriginalFilename();
			String extName = originalFilename.substring(originalFilename.lastIndexOf('.') +1);  // 파일명 중간에 . 있을 경우 대비 뒤에서 찾기
			String restoreFilename = generateSaveFilename(extName);	
			Long fileSize = multipartFile.getSize();
			
			System.out.println("#########" + originalFilename);
			System.out.println("#########" + restoreFilename);
			System.out.println("#########" + fileSize);
			byte[] data = multipartFile.getBytes();
			
			OutputStream os = new FileOutputStream(RESTORE_PATH + "/"+ restoreFilename);
			os.write(data);
			os.close();
			
			url = URL_BASE + "/" + restoreFilename;
		} catch (IOException e) {
			throw new FileUploadServiceException(e.toString());
		}
		return url;
	}

	// 여러 사용자에 의해 이름 중복될 가능성 배제하여 사진 파일 저장하기 (시간을 파일명으로 저장!!)
	private String generateSaveFilename(String extName) {
		String filename = "";
		
		Calendar calendar = Calendar.getInstance();
		filename += calendar.get(Calendar.YEAR);
		filename += calendar.get(Calendar.MONTH);
		filename += calendar.get(Calendar.DATE);
		filename += calendar.get(Calendar.HOUR);
		filename += calendar.get(Calendar.MINUTE);
		filename += calendar.get(Calendar.SECOND);
		filename += calendar.get(Calendar.MILLISECOND);
		filename += ("." + extName);
		
		return filename;
	}
}
