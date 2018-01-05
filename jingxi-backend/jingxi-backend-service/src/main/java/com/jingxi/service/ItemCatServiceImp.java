package com.jingxi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jingxi.mapper.TbItemCatMapper;
import com.jingxi.model.TbItemCat;
import com.jingxi.model.TbItemCatExample;
import com.jingxi.model.TbItemCatExample.Criteria;

@Service
public class ItemCatServiceImp implements ItemCatService {

	@Autowired
	private TbItemCatMapper itemCatMapper;
	
	@Override
	public List<TbItemCat> getItemCatList(Long parentId) throws Exception {
		TbItemCatExample example = new TbItemCatExample();
		//设置查询条件
		Criteria criteria = example.createCriteria();
		//根据parentid查询子节点
		criteria.andParentIdEqualTo(parentId);
		//返回子节点列表
		List<TbItemCat> list = itemCatMapper.selectByExample(example);
		return list;
	}

}
