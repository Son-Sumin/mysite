package com.bitacademy.mysite.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bitacademy.mysite.dao.GuestbookDao;
import com.bitacademy.mysite.dao.UserDao;
import com.bitacademy.mysite.vo.GuestbookVo;
import com.bitacademy.mysite.vo.UserVo;

public class GuestbookController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String action = request.getParameter("a");
		
		if("deleteform".equals(action)) {
			request
			.getRequestDispatcher("/WEB-INF/views/guestbook/deleteform.jsp")
			.forward(request, response);
//			//// Access Control
//			HttpSession session = request.getSession();
//			UserVo authUser = (UserVo)session.getAttribute("authUser");
//			if(authUser == null) {
//				response.sendRedirect(request.getContextPath() + "/guestbook?a=deleteform");
//				return;
//			}
//			////
//			
//			UserVo vo = new UserDao().!!findByNo(authUser.getNo());
//			request.setAttribute("userVo", vo);
//			
//			request
//				.getRequestDispatcher("/WEB-INF/views/guestbook/deleteform.jsp")
//				.forward(request, response);
			
		} else if("list".equals(action)) {
			request
			.getRequestDispatcher("/WEB-INF/views/guestbook/list.jsp")
			.forward(request, response);
			
		} else if("insert".equals(action)) {
			//// Access Control
			HttpSession session = request.getSession();
			UserVo authUser = (UserVo)session.getAttribute("authUser");
			if(authUser == null) {
				response.sendRedirect(request.getContextPath() + "user?a=loginform");
				return;
			}
			////
			
			String name = request.getParameter("name");
			String password = request.getParameter("password");
			String contents = request.getParameter("contents");
			
			GuestbookVo vo = new GuestbookVo();
			vo.setName(name);
			vo.setPassword(password);
			vo.setContents(contents);
			
			new GuestbookDao().insert(vo);
			
			authUser.setName(name);
			
			request.setAttribute("guestbookVo", vo);
			
			response.sendRedirect(request.getContextPath() + "/guestbook?a=list");
			
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
