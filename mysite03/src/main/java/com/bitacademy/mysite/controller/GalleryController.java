package com.bitacademy.mysite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.bitacademy.mysite.service.FileUploadService;
import com.bitacademy.mysite.service.GalleryService;

@Controller
@RequestMapping("/gallery")
public class GalleryController {
	@Autowired
	private GalleryService galleryService;
	
	@Autowired
	private FileUploadService fileUploadService;
	
	@RequestMapping("")
	public String index(Model model) {
		model.addAttribute("list", galleryService.getContentsList());
		return "gallery/index";
	}
	
	@RequestMapping("/upload")
	public String upload(
			@RequestParam("comments") String comments,
			@RequestParam("file") MultipartFile multipartFile,
			Model model) {
		String url = fileUploadService.restore(multipartFile);
		model.addAttribute("url", url);
		//galleryService.saveImages(galleryVo);
		return "redirect:/gallery";
	}
	
	@RequestMapping("/delete/{no}")
	public String delete(@PathVariable("no") Long no) {
		galleryService.removeImages(no);
		return "redirect:/gallery";
	}
}
