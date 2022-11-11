package com.bitacademy.mysite.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bitacademy.mysite.dao.BoardDao;
import com.bitacademy.mysite.dao.GuestbookDao;
import com.bitacademy.mysite.vo.BoardVo;

public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("a");
		
		if("write".equals(action)) {
			request
			.getRequestDispatcher("/WEB-INF/views/board/write.jsp")
			.forward(request, response);
			
		} else if("insert".equals(action)) {	
			String title = request.getParameter("title");
			String contents = request.getParameter("contents");
			
			BoardVo vo = new BoardVo();
			vo.setTitle(title);
			vo.setContents(contents);
			
			new BoardDao().insert(vo);
			
			response.sendRedirect(request.getContextPath() + "/board");
						
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
			.getRequestDispatcher("/WEB-INF/views/board/deleteform.jsp")
			.forward(request, response);
			
		} else if("delete".equals(action)) {
			String no = request.getParameter("no");
			String password = request.getParameter("password");
			new GuestbookDao().deleteByNoAndPassword(Long.parseLong(no), password);

			response.sendRedirect(request.getContextPath() + "/board");

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
