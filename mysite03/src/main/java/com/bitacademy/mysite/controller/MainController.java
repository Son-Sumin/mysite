package com.bitacademy.mysite.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bitacademy.mysite.vo.UserVo;

@Controller
public class MainController {
	private static final Log Logger = LogFactory.getLog(MainController.class);
	
	@RequestMapping({"", "/main"})
	public String index() {
		Logger.debug("MainController.index() called");
		return "main/index";  // WEB-INF/web.xml 참고
	}
	
	// Message Converter 실습
	// String; 한글 넣으면 깨짐 -> utf-8 encoding setting 필요
	@ResponseBody
	@RequestMapping("/msg01")
	public String message01() {
		return "안녕";
	}
	
	// Object
	@ResponseBody
	@RequestMapping("/msg02")
	public Object message02() {
		UserVo vo = new UserVo();
		vo.setNo(1L);
		vo.setEmail("kickscar@gmail.com");
		vo.setName("맹구");
		return vo;
	}
	
	@ResponseBody
	@RequestMapping("/msg03")
	public Object message03() {
		List<UserVo> list = new ArrayList<>();
		
		UserVo vo1 = new UserVo();
		vo1.setNo(1L);
		vo1.setEmail("kickscar1@gmail.com");
		vo1.setName("맹구1");
		list.add(vo1);
		
		UserVo vo2 = new UserVo();
		vo2.setNo(2L);
		vo2.setEmail("kickscar2@gmail.com");
		vo2.setName("맹구2");
		list.add(vo2);
		
		return list;
	}
}
