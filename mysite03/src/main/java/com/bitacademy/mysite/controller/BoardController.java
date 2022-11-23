package com.bitacademy.mysite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bitacademy.mysite.security.Auth;
import com.bitacademy.mysite.security.AuthUser;
import com.bitacademy.mysite.service.BoardService;
import com.bitacademy.mysite.vo.BoardVo;

@Controller
@RequestMapping("/board")
public class BoardController {
	@Autowired
	private BoardService boardService;
	
	@RequestMapping({"", "/list"})
	public String list(Model model) {
		model.addAttribute("list", boardService.getContentsList());
		return "board/list";
	}
	
	@Auth
	@RequestMapping(value="/write", method=RequestMethod.GET)
	public String write() {
		return "board/write";
	}
	
	@Auth
	@RequestMapping(value="/write", method=RequestMethod.POST)
	public String write(BoardVo vo) {
		boardService.addContents(vo);
		return "redirect:/board";
	}

	@Auth
	@RequestMapping({"/delete/{no}"})
	public String delete(@PathVariable("no") Long no, Long userNo) {
		boardService.deleteContents(no, userNo);
		return "redirect:/board";
	}
	
	@RequestMapping({"/view/{no}"})
	public String view(@PathVariable("no") Long no, Model model) {
		BoardVo boardVo = boardService.findContents(no);
		model.addAttribute("no", no);
		model.addAttribute("boardVo", boardVo);
		return "board/view";
	}
	
//	@RequestMapping({"/view/{no}"})
//	public String view(@PathVariable("no") Long no, BoardVo boardVo, Model model) {
//		model.addAttribute("boardVo", boardService.findContents(no));
//		return "board/view";
//	}
	
	@Auth
	@RequestMapping(value="/modify/{no}", method=RequestMethod.GET)
	public String modify(
			@PathVariable("no") Long no,
			@ModelAttribute BoardVo boardVo) {
		boardVo = boardService.findContents(no, boardVo.getUserNo());
	
		System.out.println(boardVo);
		return "board/modify";
	}
	
	@Auth
	@RequestMapping(value="/modify/{no}", method=RequestMethod.POST)
	public String modify(@ModelAttribute BoardVo boardVo) {
		boardService.updateContents(boardVo);
		return "redirect:/board";
	}
	
	@Auth
	@RequestMapping(value="/reply", method=RequestMethod.GET)
	public String reply() {
		return "board/reply";
	}
	
	@Auth
	@RequestMapping(value="/reply", method=RequestMethod.POST)
	public String reply(BoardVo boardVo) {
		boardService.updateContents(boardVo);
		return "redirect:/board";
	}
	
//	@RequestMapping({"", "/list"})
//	public String list(Model model) {
//		model.addAttribute("list", boardService.findContentsList());
//		return "board/list";
//	}
	
}
