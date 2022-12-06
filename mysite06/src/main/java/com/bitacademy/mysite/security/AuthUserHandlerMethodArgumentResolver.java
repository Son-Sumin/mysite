package com.bitacademy.mysite.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebArgumentResolver;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.bitacademy.mysite.vo.UserVo;

public class AuthUserHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {
	
	// supportsParameter이고 session 있으면
	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
		if(!supportsParameter(parameter)) {
			return WebArgumentResolver.UNRESOLVED;  // = null
		}
		
		HttpServletRequest request = (HttpServletRequest)webRequest.getNativeRequest();
		HttpSession session = request.getSession();
		if(session == null) {
			return null;
		}
		return session.getAttribute("authUser");
	}
	
	// authUser가 없으면 차단
	// authUser가 있지만 Uservo를 변수로 안 가지면 차단
	// supportsParameter = true ; authUser O, Uservo를 변수로 가짐
	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		AuthUser authUser = parameter.getParameterAnnotation(AuthUser.class);
		
		//@AuthUser가 안 붙어 있으면...
		if(authUser == null) {
			return false;
		}
		
		// 파라미터 타입이 UserVo가 아니라면...
		if(parameter.getParameterType().equals(UserVo.class)) {
			return false;
		}
		
		return true;
	}

}

/* 각 Controller에서 Access Control을 지우는 과정에서 authUser가 필요한 경우가 생겨 해당 페이지 작성
 * Http 없애기 위해 해당 페이지 작성
  interface AuthUser 생성 후 해당 페이지에 구현,  @AuthUser 통해 각 Controller에서 사용
  
UserVo authUser = (UserVo)session.getAttribute("authUser");  
if(authUser == null) {										
	return "redirect:/";
}
*/
