package com.jingxi.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jingxi.common.pojo.EasyUIDataGridResult;
import com.jingxi.common.pojo.JingXiResult;
import com.jingxi.common.util.IDUtil;
import com.jingxi.mapper.TbItemDescMapper;
import com.jingxi.mapper.TbItemMapper;
import com.jingxi.mapper.TbItemParamItemMapper;
import com.jingxi.mapper.TbItemParamMapper;
import com.jingxi.model.TbItem;
import com.jingxi.model.TbItemDesc;
import com.jingxi.model.TbItemExample;
import com.jingxi.model.TbItemParam;
import com.jingxi.model.TbItemParamItem;

@Service
public class itemServiceImp implements ItemService{

	@Autowired
	private TbItemMapper itemMapper;
	
	@Autowired
	private TbItemDescMapper itemDescMapper;
	
	@Autowired
	private ItemDescService itemDescService;
	
	@Autowired
	private TbItemParamItemMapper itemParamItemMapper;
	
	@Override
	public TbItem getTnItemById(long id) {
		return itemMapper.selectByPrimaryKey(id);
	}

	//查找
	@Override
	public EasyUIDataGridResult getItemList(int page,int rows){
		TbItemExample example = new TbItemExample();
		//分页处理
		PageHelper.startPage(page, rows);
		List<TbItem> list = itemMapper.selectByExample(example);
		//创建一个返回值对象
		EasyUIDataGridResult result = new EasyUIDataGridResult();
		result.setRows(list);
		//取记录总条数
		PageInfo<TbItem> pageInfo = new PageInfo<>(list);
		result.setTotal(pageInfo.getTotal());
		return result;
	}

	//保存
	@Override
	public JingXiResult createItem(TbItem item , String itemParams ,String desc) {
		Date date = new Date();
		//存进TbItem表
		//获得商品id
		long id = IDUtil.genItemId();
		//添加商品信息
		item.setId(id);
		//商品状态，1，正常 2，下架 3，删除
		item.setStatus((byte)1);
		item.setCreated(date);
		item.setUpdated(date);
		itemMapper.insert(item);
		//添加商品描述信息
		TbItemDesc itemDesc = new TbItemDesc();
		itemDesc.setCreated(date);
		itemDesc.setUpdated(date);
		itemDesc.setItemId(id);
		itemDesc.setItemDesc(desc);
		itemDescMapper.insert(itemDesc);
		//存进TbItemParamItem表
		TbItemParamItem itemParamItem = new TbItemParamItem();
		itemParamItem.setItemId(item.getId());
		itemParamItem.setCreated(date);
		itemParamItem.setUpdated(date);
		itemParamItem.setParamData(itemParams);
		itemParamItemMapper.insert(itemParamItem);
		return JingXiResult.ok();
	}

	//修改
	@Override
	public JingXiResult editItem(TbItem item,String desc) {
		Date date = new Date();
		TbItemDesc itemDesc = new TbItemDesc();
		//商品状态，1，正常 2，下架 3，删除
		item.setStatus((byte) 1);
		//修改商品信息
		itemDesc.setItemId(item.getId());
		itemDesc.setUpdated(date);
		int status = itemMapper.updateByPrimaryKeySelective(item);
		itemDescService.updateItemDesc(itemDesc);
		if(status == 1){
			return JingXiResult.build(200, "更新成功");
		}else{
			return JingXiResult.build(500, "更新失败");
		}
	}

	//删除
	@Override
	public JingXiResult deleteItem(long ids) {
		int status = itemMapper.deleteByPrimaryKey(ids);
		if(status == 1){
			return JingXiResult.build(200, "更新成功");
		}else{
			return JingXiResult.build(500, "更新失败");
		}
	}
}
