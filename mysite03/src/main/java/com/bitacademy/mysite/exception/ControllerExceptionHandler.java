package com.bitacademy.mysite.exception;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

// Controller의 모든 메소드에 Exception 발생시 AOP를 활용한 ExceptionHandler 만듬

@ControllerAdvice  // AOP의 where 지정
public class ControllerExceptionHandler {
	private static final Log Logger = LogFactory.getLog(ControllerExceptionHandler.class);
	
	@ExceptionHandler(Exception.class)  // AOP의 when 지정
	public String HandlerException(Model model, Exception e) {
		// 로깅
		StringWriter errors = new StringWriter();  // Stringbuffer 갖고 있는 StringWriter, 보조스트림
		e.printStackTrace(new PrintWriter(errors));  // 주스트림  // 어떤 흐름, 어떤 메소드에서 발생했는지 알게끔
		
		//System.out.println(errors.toString());   
		/*
		 * 출력이 아니라 파일로 로그를 남겨보자!!!!!!!!
		 * 오류 발생 시 사용자에게 남기는 경우는 없음.
		 * 오류 조치를 위해 관리자가 볼 수 있도록 로그를 파일로 기록
		 * mysite pom, 03 pom 추가!(설정)
		 */
		
		Logger.error(errors.toString());
		
		// 사과 페이지(HTML 응답, 정상종료)
		model.addAttribute("exception", errors.toString());	
		return "error/exception";
	}
}
