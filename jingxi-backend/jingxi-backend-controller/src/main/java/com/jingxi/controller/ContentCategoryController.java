package com.jingxi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jingxi.common.pojo.EUTreeNode;
import com.jingxi.common.pojo.JingXiResult;
import com.jingxi.model.TbContentCategory;
import com.jingxi.service.ContentCategoryService;

@Controller
@RequestMapping("/content/category")
public class ContentCategoryController {

	@Autowired
	private ContentCategoryService contentCategoryService;

	@RequestMapping("/list")
	@ResponseBody
	public List<EUTreeNode> getContentCatList(@RequestParam(value = "id", defaultValue = "0") Long parentId) {
		List<EUTreeNode> result = contentCategoryService.getCategoryList(parentId);
		return result;
	}

	@RequestMapping("/create")
	@ResponseBody
	public JingXiResult createContentCategory(Long parentId, String name) {
		JingXiResult result = contentCategoryService.insertContentCategory(parentId, name);
		return result;
	}

	@RequestMapping("/delete")
	@ResponseBody
	public JingXiResult deleteContentCategory(Long id) {
		JingXiResult result = contentCategoryService.deleteContentCategory(id);
		return result;
	}

	@RequestMapping("/update")
	@ResponseBody
	public JingXiResult updateContentCategory(TbContentCategory contentCategory, Long id, String name) {
		JingXiResult result = contentCategoryService.updateContentCategory(contentCategory,id, name);
		return result;
	}
}
