package com.jingxi.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jingxi.common.pojo.JingXiResult;
import com.jingxi.rest.service.SearchService;

@Controller
public class SearchController {
	
	@Autowired
	private SearchService searchService;
	
	@RequestMapping("/search")
	@ResponseBody
	public JingXiResult search(@RequestParam(value="q") String q,int page,int rows) throws Exception{
		String str = new String(q.getBytes("ISO-8859-1"),"utf-8");
		JingXiResult list = searchService.search(str,page,rows);
		return list;
	}
}
