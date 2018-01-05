package com.jingxi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jingxi.common.pojo.EasyUIDataGridResult;
import com.jingxi.common.pojo.JingXiResult;
import com.jingxi.model.TbItem;
import com.jingxi.model.TbItemParam;
import com.jingxi.model.TbItemParamItem;
import com.jingxi.service.ItemDescService;
import com.jingxi.service.ItemParamService;
import com.jingxi.service.ItemService;

@Controller
@RequestMapping("/item")
public class ItemController {
	
	@Autowired
	private ItemService itemService;
	
	@Autowired
	private ItemDescService itemDescService;
	
	@Autowired
	private ItemParamService itemParamService;
	
	@RequestMapping("/{id}")
	@ResponseBody
	public TbItem getTbItemById(@PathVariable long id){
		TbItem tbItem = itemService.getTnItemById(id);
		return tbItem;
	}
	
	@RequestMapping("/list")
	@ResponseBody
	public EasyUIDataGridResult getContentList(Integer page , Integer rows){
		EasyUIDataGridResult result = itemService.getItemList(page, rows);
		return result;
	}
	
	@RequestMapping("/save")
	@ResponseBody
	public JingXiResult saveItem(TbItem item ,String itemParams , String desc) throws Exception{
		return itemService.createItem(item,itemParams,desc);
	}
	
	@RequestMapping("/update")
	@ResponseBody
	public JingXiResult editItem(TbItem item ,String desc) throws Exception{
		return itemService.editItem(item, desc);
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public JingXiResult deleteItem(long ids) throws Exception{
		return itemService.deleteItem(ids);
	}
	
	@RequestMapping("/desc/{id}")
	@ResponseBody
	public JingXiResult selectDescById(@PathVariable Long id){
		long idDemo = id;
		System.out.println(idDemo);
		JingXiResult result = itemDescService.getDescById(id);	
		return result;
	}

	//商品规格参数列表展示
	@RequestMapping("/param/list")
	@ResponseBody
	public EasyUIDataGridResult getTbItemParam(Integer page , Integer rows){
		EasyUIDataGridResult result = itemParamService.getItemParam(page, rows);
		return result;
	}
	
	//判断商品规格参数列表是否已添加
	@RequestMapping("/param/query/itemcatid/{id}")
	@ResponseBody
	public JingXiResult getTbItemParam(@PathVariable Long id){
		JingXiResult result = itemParamService.createItemParam(id);
		return result;
	}
	
	//商品规格保存
	@RequestMapping("/param/save/{cid}")
	@ResponseBody
	public JingXiResult saveTbItemParam(@PathVariable Long cid,TbItemParam tbItemParam){
		JingXiResult result = itemParamService.saveItemParam(cid,tbItemParam);
		return result;
	}
	
	//新增商品：判断商品规格参数列表是否已添加
	@RequestMapping("/param/add/itemcatid/{id}")
	@ResponseBody
	public JingXiResult getAddParam(@PathVariable Long id){
		JingXiResult result = itemParamService.getAddParam(id);
		return result;
	}
}
