package com.bitacademy.mysite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bitacademy.mysite.security.Auth;
import com.bitacademy.mysite.security.AuthUser;
import com.bitacademy.mysite.service.UserService;
import com.bitacademy.mysite.vo.UserVo;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;
	
	@Auth
	@RequestMapping(value="/join", method=RequestMethod.GET)
	public String join() {
		return "user/join";
	}
	
	@RequestMapping(value="/join", method=RequestMethod.POST)
	public String join(UserVo userVo) {
		userService.join(userVo);
		return "redirect:/user/joinsuccess";
	}
	
	@RequestMapping("/joinsuccess")
	public String joinsuccess() {
		return "user/joinsuccess";
	}
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String login() {
		return "user/login";
	}
	
	@Auth
	@RequestMapping(value="/update", method=RequestMethod.GET)
	public String update(Model model, @AuthUser UserVo authUser) {
		UserVo userVo = userService.findUser(authUser.getNo());
		model.addAttribute("UserVo", userVo);
		return "user/update";
	}
	
	@Auth
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public String update(UserVo userVo, @AuthUser UserVo authUser) {
//		// Access Control
//		UserVo authUser = (UserVo)session.getAttribute("authUser");  // authUser의 정보를 써야해서 필요함
//		if(authUser == null) {										 // authUser도 변수로 받기 위해 위를 활용
//			return "redirect:/";
//		}
//		//////////////////
		
		userVo.setNo(authUser.getNo());
		userService.updateUser(userVo);
		
		authUser.setName(userVo.getName());
		
		return "redirect:/user/update";
	}
}
