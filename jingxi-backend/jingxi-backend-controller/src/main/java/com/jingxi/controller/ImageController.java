package com.jingxi.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.jingxi.service.ImageUploadService;

@Controller
public class ImageController {
	
	@Autowired
	private ImageUploadService imageUploadService;
	
	@SuppressWarnings("rawtypes")
	@RequestMapping("/pic/upload")
	@ResponseBody
	public Map upload(MultipartFile uploadFile) throws Exception{
		Map imageResult = imageUploadService.uploadFile(uploadFile);
		return imageResult;
	}
}
