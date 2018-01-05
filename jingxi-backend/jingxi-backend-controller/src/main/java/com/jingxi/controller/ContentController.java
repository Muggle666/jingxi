package com.jingxi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jingxi.common.pojo.EasyUIDataGridResult;
import com.jingxi.common.pojo.JingXiResult;
import com.jingxi.model.TbContent;
import com.jingxi.service.ContentService;

@Controller
@RequestMapping("/content")
public class ContentController {

	@Autowired
	private ContentService contentService;
	
	@RequestMapping("/query/list")
	@ResponseBody
	public EasyUIDataGridResult getContentList(Long categoryId,Integer page , Integer rows){
		return contentService.getContentList(categoryId,page, rows);
	}
	
	@RequestMapping("/save")
	@ResponseBody
	public JingXiResult insertContent(TbContent content){
		return contentService.insertContent(content);
	}
	
	@RequestMapping("/edit")
	@ResponseBody
	public JingXiResult editContent(TbContent content){
		return contentService.editContent(content);
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public JingXiResult deleteContent(Long ids){
		return contentService.deleteContent(ids);
	}
	
}
