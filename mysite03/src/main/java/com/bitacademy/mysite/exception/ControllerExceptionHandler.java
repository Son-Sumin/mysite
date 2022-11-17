package com.bitacademy.mysite.exception;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

// Controller의 모든 메소드에 Exception 발생시 AOP를 활용한 ExceptionHandler 만듬

@ControllerAdvice  // AOP의 where 지정
public class ControllerExceptionHandler {
	
	@ExceptionHandler(Exception.class)  // AOP의 when 지정
	public String HandlerException(Model model, Exception e) {
		// 로깅
		StringWriter errors = new StringWriter();  // Stringbuffer 갖고 있는 StringWriter, 보조스트림
		e.printStackTrace(new PrintWriter(errors));  // 주스트림  // 어떤 흐름, 어떤 메소드에서 발생했는지 알게끔
		System.out.println(errors.toString());
		
		// 사과 페이지(HTML 응답, 정상종료)
		model.addAttribute("exception", errors.toString());	
		return "error/exception";
	}
}
