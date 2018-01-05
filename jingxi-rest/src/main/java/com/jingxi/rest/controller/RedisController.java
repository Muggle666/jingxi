package com.jingxi.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jingxi.common.pojo.JingXiResult;
import com.jingxi.rest.service.RedisService;

@Controller
public class RedisController {

	@Autowired
	private RedisService redisService;
	
	@RequestMapping("/content/{contentCid}")
	@ResponseBody
	public JingXiResult contentCacheSync(@PathVariable Long contentCid){
		JingXiResult result = redisService.syncContent(contentCid);
		return result;
		
	}
}
