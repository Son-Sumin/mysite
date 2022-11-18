package com.bitacademy.mysite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bitacademy.mysite.service.BoardService;
import com.bitacademy.mysite.vo.BoardVo;

@Controller
@RequestMapping("/board")
public class BoardController {
	@Autowired
	private BoardService boardService;
	
	@RequestMapping({"", "/list"})
	public String list(Model model) {
		model.addAttribute("list", boardService.findContentsList(2));
		return "board/list";
	}
	
	@RequestMapping(value="/write", method=RequestMethod.GET)
	public String write() {
		return "board/write";
	}
	
	@RequestMapping(value="/write", method=RequestMethod.POST)
	public String write(BoardVo vo) {
		boardService.addContents(vo);
		return "redirect:/board";
	}

	@RequestMapping({"/delete/{no}"})
	public String delete(
			@PathVariable("no") Long no,
			@PathVariable("userNo") Long userNo) {
		boardService.deleteContents(no, userNo);
		return "redirect:/board";
	}
	
	@RequestMapping({"/view/{no}"})
	public String view(
			@PathVariable("no") Long no,
			@PathVariable("userNo") Long userNo) {
		boardService.findContents(no, userNo);
		return "board/view";
	}
}
