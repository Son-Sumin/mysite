package com.bitacademy.mysite.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
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
	
	@RequestMapping(value="/join", method=RequestMethod.GET)
	public String join(@ModelAttribute UserVo userVo) {
		return "user/join";
	}
	
	@RequestMapping(value="/join", method=RequestMethod.POST)
	public String join(@ModelAttribute @Valid UserVo userVo, BindingResult result, Model model) {
		  // validation에 대한 결과를 BindingResult가 받음
		
		if(result.hasErrors()) {  // 에러 내용이 많으니 console에 출력하면 파싱하기 어렵다
			List<ObjectError> errors = result.getAllErrors();
			for(ObjectError error : errors) {
				System.out.println(error);
			}
			
			model.addAllAttributes(result.getModel());  // map을 parameter로 받아 value 얻기
			// @ModelAttribute로 대체 가능
			// model.addAttribute("userVo", userVo);  
			return "user/join";
		}
		userService.join(userVo);
		return "redirect:/user/joinsuccess";
	}
	
	@RequestMapping("/joinsuccess")
	public String joinsuccess() {
		return "user/joinsuccess";
	}
	
	// auth, logout -> spring-servlet.xml에서 확인
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
		userVo.setNo(authUser.getNo());
		userService.updateUser(userVo);
		
		authUser.setName(userVo.getName());
		
		return "redirect:/user/update";
	}
	
	@RequestMapping("/auth")
	public void auth() {
	}
	
	@RequestMapping("/logout")
	public void logout() {
	}
}
