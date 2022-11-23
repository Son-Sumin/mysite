package com.bitacademy.mysite.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.bitacademy.mysite.security.Auth;
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
	public String index(Model model) {
		List<GalleryVo> list = galleryService.getImageList();
		model.addAttribute("list", list);
		
		// System.out.println(list);  // 확인용
		return "gallery/index";
	}
	
	@Auth(role="admin")
	@RequestMapping("/upload")
	public String upload(
			@RequestParam("file") MultipartFile multipartFile,
			GalleryVo galleryVo) {
		String url = fileUploadService.restore(multipartFile);
		
		galleryVo.setUrl(url);
		galleryService.saveImages(galleryVo);
		
		//model.addAttribute("url", url);
		//model.addAttribute("galleryVo", galleryVo);
		return "redirect:/gallery";
	}
	
	@Auth(role="admin")
	@RequestMapping("/delete/{no}")
	public String delete(@PathVariable("no") Long no) {
		galleryService.removeImages(no);
		return "redirect:/gallery";
	}
}

/*
 * 
mysite pom > common fileupload 세팅
spring-servlet >  MVC Resources Mapping, MultipartResolver 세팅
FileUploadService URL 등 상황에 맞게 수정

Vo - Controller -Service- Repo - mybatis(gallery.xml)순으로 작성
configuration > mybatis(gallery.xml) 세팅
 */