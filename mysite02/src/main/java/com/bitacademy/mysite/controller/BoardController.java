package com.bitacademy.mysite.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bitacademy.mysite.dao.BoardDao;
import com.bitacademy.mysite.dao.UserDao;
import com.bitacademy.mysite.vo.BoardVo;
import com.bitacademy.mysite.vo.UserVo;

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
			String userNo = request.getParameter("userNo");
			
			BoardVo vo = new BoardVo();
			vo.setTitle(title);
			vo.setContents(contents);
			vo.setUserNo(Long.parseLong(userNo));
			
			new BoardDao().insert(vo);
			
			response.sendRedirect(request.getContextPath() + "/board");
			
		} else if("delete".equals(action)) {
			String sno = request.getParameter("no");
			Long no = Long.parseLong(sno);
			
			new BoardDao().deleteByNo(no);

			response.sendRedirect(request.getContextPath() + "/board");

		} else if ("view".equals(action)) {
			String no = request.getParameter("no");
			
			BoardVo vo = new BoardDao().findByNo(Long.parseLong(no));
			request.setAttribute("boardVo", vo);
			
			request
			.getRequestDispatcher("/WEB-INF/views/board/view.jsp")
			.forward(request, response);
			
		} else if ("modifyform".equals(action)) {
			String no = request.getParameter("no");
			BoardVo vo = new BoardDao().findByNo(Long.parseLong(no));
			request.setAttribute("boardVo", vo);
			
			request
			.getRequestDispatcher("/WEB-INF/views/board/modify.jsp")
			.forward(request, response);
			
		} else if ("modify".equals(action)) {
			String title = request.getParameter("title");
			String contents = request.getParameter("contents");
			String no = request.getParameter("no");
			
			BoardVo vo = new BoardVo();
			vo.setTitle(title);
			vo.setContents(contents);
			vo.setNo(Long.parseLong(no));
			
			new BoardDao().update(vo);
		
			response.sendRedirect(request.getContextPath() + "/board");
			
		}  else if ("reply".equals(action)) {
			request
			.getRequestDispatcher("/WEB-INF/views/board/reply.jsp")
			.forward(request, response);
			
		} else {
			List<BoardVo> list = new BoardDao().findAll();
			
			request.setAttribute("list", list);
			request
				.getRequestDispatcher("/WEB-INF/views/board/list.jsp")
				.forward(request, response);
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}

// 데이터 변경 없을 때 : request.getRequestDispatcher("/WEB-INF/경로~").forward(request, response);  -> 경로
// 데이터 변경 있을 때 : response.sendRedirect(request.getContextPath() + "/board");  -> URL
