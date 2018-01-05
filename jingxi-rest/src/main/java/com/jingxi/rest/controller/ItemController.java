package com.jingxi.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jingxi.common.pojo.JingXiResult;
import com.jingxi.rest.service.ItemService;

@Controller
@RequestMapping("/item")
public class ItemController {
	
	@Autowired
	private ItemService itemService;
	
	@RequestMapping("/info/{itemId}")
	@ResponseBody
	public JingXiResult getItemBaseInfo(@PathVariable Long itemId){
		JingXiResult result = itemService.getItemBaseInfo(itemId);
		return result;
	}
	@RequestMapping("/desc/{itemId}")
	@ResponseBody
	public JingXiResult getItemDesc(@PathVariable Long itemId){
		JingXiResult result = itemService.getItemDesc(itemId);
		return result;
	}
	@RequestMapping("/param/{itemId}")
	@ResponseBody
	public JingXiResult getItemParam(@PathVariable Long itemId){
		System.out.println("rest--getItemParam");
		JingXiResult result = itemService.getItemParam(itemId);
		return result;
	}
	
}
