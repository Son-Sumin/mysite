package com.bitacademy.mysite.exception;

public class UserRepositoryException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public UserRepositoryException() {
		super("UserRepositoryException Occurs");
	}
	
	public UserRepositoryException(String message) {
		super(message);
	}
}

/*
  reposiroty에서 발생한 에러(코드작성) Runtime때 발생시키기 위해
  RuntimeException을 super로 둔 나의 exception 만들기(ex. UserRepositoryException.java)
  
  그리고 reposiroty 예외부분 수정
  
  spring-servlet.xml에서 base-package에 'com.bitacademy.mysite.exception' 추가
  
  프로그램 차원의 오류 - 500.jsp - web.xml 추가
  con+ser+repo 차원의 오류 - exception.jsp
  
 
 */