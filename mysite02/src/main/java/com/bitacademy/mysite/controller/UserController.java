package com.bitacademy.mysite.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bitacademy.mysite.dao.UserDao;
import com.bitacademy.mysite.vo.UserVo;

public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String action = request.getParameter("a");
		
		if("joinform".equals(action)) {
			request
				.getRequestDispatcher("/WEB-INF/views/user/joinform.jsp")
				.forward(request, response);
			
		}  else if("join".equals(action)){
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			String gender = request.getParameter("gender");
			
			UserVo vo = new UserVo();
			vo.setName(name);
			vo.setEmail(email);
			vo.setPassword(password);
			vo.setGender(gender);

			new UserDao().insert(vo);

			response.sendRedirect(request.getContextPath() + "/user?a=joinsuccess");
			
		} else if("joinsuccess".equals(action)) {
			request
			.getRequestDispatcher("/WEB-INF/views/user/joinsuccess.jsp")
			.forward(request, response);
			
		} else if("updateform".equals(action)) {
			//// Access Control
//			HttpSession session = request.getSession();
//			UserVo authUser = (UserVo)request.getAttribute("authUser");
//			if(authUser == null) {
//				response.sendRedirect(request.getContextPath() + "user?a=loginform");
//				return;
//			}
			////
			
			// 과제
			// UserVo vo = new UserDao().findByNo(authUser.getNo());
			// request.setAttribute("UserVo", vo);
			// 다 수정되면 response.sendRedirect("/mysite02/user?a=updateform")
			
			request
				.getRequestDispatcher("/WEB-INF/views/user/updateform.jsp")
				.forward(request, response);
			
		} else if("update".equals(action)) {
			String name = request.getParameter("name");
			String password = request.getParameter("password");
			String gender = request.getParameter("gender");
			
			UserVo vo = new UserVo();
			vo.setName(name);
			vo.setPassword(password);
			vo.setGender(gender);

			new UserDao().update(vo);

			response.sendRedirect(request.getContextPath() + "/user?a=updatesuccess");
			
		} else if("updatesuccess".equals(action)) {
			request
			.getRequestDispatcher("/WEB-INF/views/user/updatesuccess.jsp")
			.forward(request, response);
			
			
		} else if("loginform".equals(action)) {
			request
				.getRequestDispatcher("/WEB-INF/views/user/loginform.jsp")
				.forward(request, response);
			
		} else if("login".equals(action)) {
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			
			UserVo authUser = new UserDao().findByEmailAndPassword(email, password);
			
			if(authUser == null) {
				/* 인증 실패 */
				request.setAttribute("email", email);
				request
					.getRequestDispatcher("/WEB-INF/views/user/loginform.jsp")
					.forward(request, response);
				return;
			}
			/* 로그인 처리 */
					/* SessionFilter가 요청 당한 JSESSIONID가 없으면 만들어 달라고 답변함.
					 Session Manager가 JSESSIONID와 HttpSession mapping 시켜준다.
					HttpSession 안에 객체 존재 */
			HttpSession session = request.getSession(true);  // 없으면 null 처리, true면 만들어서 준다
			session.setAttribute("authUser", authUser);
			
			response.sendRedirect(request.getContextPath());
			
		} else if("logout".equals(action)) {
			HttpSession session = request.getSession();
			if(session != null) {
				session.removeAttribute("authUser");
				session.invalidate();
			}
			response.sendRedirect(request.getContextPath());
			
		} else {
			response.sendRedirect(request.getContextPath());
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
