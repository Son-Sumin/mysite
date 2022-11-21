package com.bitacademy.mysite.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bitacademy.mysite.security.Auth;
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
	
	@RequestMapping(value="/auth", method=RequestMethod.GET)
	public String login() {
		return "user/login";
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)  //HttpSession; Spring의 취지에 맞게 controller 밖에서 제어하기
	public String login(HttpSession session, UserVo userVo, Model model) {
		UserVo authUser = userService.findUser(userVo);
		if(authUser == null) {
			model.addAttribute("email", userVo.getEmail());
			return"user/login";
		}
		session.setAttribute("authUser", authUser);
		return "redirect:/";
	}
	
	@RequestMapping("/logout")
	public String logout(HttpSession session) {	
		session.removeAttribute("authUser");
		session.invalidate();
		return "redirect:/";
	}
	
	@Auth
	@RequestMapping(value="/update", method=RequestMethod.GET)
	public String update(Model model, @AuthUser UserVo authUser) {
//		// Access Control
		UserVo authUser = (UserVo)session.getAttribute("authUser");
//		if(authUser == null) {
//			return "redirect:/";
//		}
//		//////////////////
		
		UserVo userVo = userService.findUser(authUser.getNo());
		model.addAttribute("UserVo", userVo);
		return "user/update";
	}
	
	@Auth
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public String update(HttpSession session, UserVo userVo) {
//		// Access Control
		UserVo authUser = (UserVo)session.getAttribute("authUser");  // authUser의 정보를 써야해서 필요함
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
