package com.bitacademy.mysite.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import com.bitacademy.mysite.vo.UserVo;

public class AuthInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(
			HttpServletRequest request,
			HttpServletResponse response,
			Object handler)
			throws Exception {
		
		// 1. handler 종류 확인
		// '<mvc:exclude-mapping path="/assets/**"/>' 이 spring-servlet에 없을 경우 대비
		// 위가 없으면 이미지 파일 확인 불가
		// 바로 2번으로 시작하면 핸들러가 DefaultServlet일 경우 충돌할 경우 대비
		if(handler instanceof HandlerMethod == false) {
			return true;
		}
		
		// 2. casting
		HandlerMethod handlerMethod = (HandlerMethod)handler;
		
		// 3. Handler Method의 @Auth를 받아오기
		// @Auth 있으면 갖고 와
		Auth auth = handlerMethod.getMethodAnnotation(Auth.class);
		
		// 4. Handler Method에 @Auth가 없다면...(인증이 필요없다는 의미)
		if(auth == null) {
			return true;
		}
		
		// 5. @Auth가 붙어있기 때문에 인증(Authentification) 여부 확인
		HttpSession session = request.getSession();
		if(session == null) {
			response.sendRedirect(request.getContextPath() + "/user/login");
			return false;
		}
		
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		if(authUser == null) {
			response.sendRedirect(request.getContextPath() + "/user/login");
			return false;
		}
		
		// 6. role(권한) 체크하기 "user" / "admin"
		String role = auth.role();
		String authUserRole = authUser.getRole();
//		if(role.equals(authUserRole)) {
//			
//		}
		
		// 7. @Auth도 붚어있고, 인증도 되어 있고 권한도 있음 -> 접근! (Controller 실행)
		return true;
	}

}
