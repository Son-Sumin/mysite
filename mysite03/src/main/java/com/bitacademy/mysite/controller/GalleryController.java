package com.bitacademy.mysite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bitacademy.mysite.service.FileUploadService;
import com.bitacademy.mysite.service.GalleryService;
import com.bitacademy.mysite.vo.GalleryVo;

@Controller
@RequestMapping("/gallery")
public class GalleryController {
	@Autowired
	private GalleryService galleryService;
	
	@Autowired
	private FileUploadService fileUploadService;
	
	@RequestMapping("")
	public String index() {
		return "gallery/index";
	}
	
	@RequestMapping("/upload")
	public String upload(GalleryVo galleryVo) {
		fileUploadService.restore(null);
		galleryService.saveImages(galleryVo);
		return "redirect:/gallery";
	}
	
	@RequestMapping("/delete/{no}")
	public String delete(@PathVariable("no") Long no) {
		galleryService.removeImages(no);
		return "redirect:/gallery";
	}
}
