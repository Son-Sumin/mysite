package com.bitacademy.mysite.security;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Retention(RUNTIME)
@Target(METHOD)
public @interface Auth {
	/* 
	 * value 입력할 시 사용
	 * default 없으면 value값 입력해야함
	   
	   public String value() default "";
	   public int method () default 0;
	   예시: @RequestMapping(value="/update", method=RequestMethod.POST)
	 */ 
	
	public String role() default "user";
	public boolean test() default false;

}

/*
 * 위는 '인증' 실시 중
 * 
 * 보안 = 인증(Authentification-login) + 권한(Authorization-role)
 * 
 * 보안 = 인프라(네트워크, 서버, 소프트웨어[보안+권한])
 */
