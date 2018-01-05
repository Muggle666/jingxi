package com.jingxi.portal.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jingxi.model.TbItem;
import com.jingxi.portal.service.SearchService;

@Controller
public class SearchController {

	@Autowired
	private SearchService searchService;

	// portal
	@RequestMapping("/search")
	public void search(@RequestParam(value = "q") String q, Model model,
			@RequestParam(value = "page", defaultValue = "1") String page) throws UnsupportedEncodingException {
		String str = new String(q.getBytes("ISO-8859-1"), "utf-8");
		String rows = "20";
		List<TbItem> list = searchService.search(str, page, rows);
		// 返回数据到jsp
		model.addAttribute("query", str);
		model.addAttribute("page", page);
		model.addAttribute("totalPages", list.size());
		model.addAttribute("rows", rows);
		model.addAttribute("itemList", list);
	}
}
