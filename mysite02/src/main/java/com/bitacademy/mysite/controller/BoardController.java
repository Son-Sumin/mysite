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
						
//		} else if("deleteform".equals(action)) {
////			//// Access Control
////			HttpSession session = request.getSession();
////			UserVo authUser = (UserVo)session.getAttribute("authUser");
////			if(authUser == null) {
////				response.sendRedirect(request.getContextPath() + "/guestbook?a=deleteform");
////				return;
////			}
////					////
//			request
//			.getRequestDispatcher("/WEB-INF/views/board/deleteform.jsp")
//			.forward(request, response);
			
		} else if("delete".equals(action)) {
			String sno = request.getParameter("no");
			Long no = Long.parseLong(sno);
			
			new BoardDao().deleteByNo(no);

			response.sendRedirect(request.getContextPath() + "/board");

		} else if ("view".equals(action)) {
			request
			.getRequestDispatcher("/WEB-INF/views/board/view.jsp")
			.forward(request, response);
			
		} else if ("modify".equals(action)) {
			request
			.getRequestDispatcher("/WEB-INF/views/board/modify.jsp")
			.forward(request, response);
			
		}  else if ("reply".equals(action)) {
			request
			.getRequestDispatcher("/WEB-INF/views/board/reply.jsp")
			.forward(request, response);
			
		} else {
			request
				.getRequestDispatcher("/WEB-INF/views/board/list.jsp")
				.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
