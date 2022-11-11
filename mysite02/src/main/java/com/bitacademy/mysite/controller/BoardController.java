package com.bitacademy.mysite.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bitacademy.mysite.dao.GuestbookDao;

public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("a");
		
		if("writeform".equals(action)) {
			
		} else if("deleteform".equals(action)) {
//			//// Access Control
//			HttpSession session = request.getSession();
//			UserVo authUser = (UserVo)session.getAttribute("authUser");
//			if(authUser == null) {
//				response.sendRedirect(request.getContextPath() + "/guestbook?a=deleteform");
//				return;
//			}
//					////
			request
			.getRequestDispatcher("/WEB-INF/views/guestbook/deleteform.jsp")
			.forward(request, response);
			
		} else if("delete".equals(action)) {
			String no = request.getParameter("no");
			String password = request.getParameter("password");
			new GuestbookDao().deleteByNoAndPassword(Long.parseLong(no), password);

			response.sendRedirect(request.getContextPath() + "/guestbook");

		}else {
			request
				.getRequestDispatcher("/WEB-INF/views/board/list.jsp")
				.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
