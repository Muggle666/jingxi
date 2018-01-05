package com.jingxi.rest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jingxi.common.pojo.EasyUIDataGridResult;
import com.jingxi.common.pojo.JingXiResult;
import com.jingxi.mapper.TbItemMapper;
import com.jingxi.model.TbItem;
import com.jingxi.model.TbItemExample;
import com.jingxi.model.TbItemExample.Criteria;

@Service
public class SearchServiceImp implements SearchService{

	@Autowired
	private TbItemMapper itemMapper;

	@Override
	public JingXiResult search(String str,int rage,int rows) {
		TbItemExample example = new TbItemExample();
		PageHelper.startPage(rage, rows);
		Criteria criteria = example.createCriteria();
		criteria.andTitleLike("%"+str+"%");
		List<TbItem> list = itemMapper.selectByExample(example);
		EasyUIDataGridResult result = new EasyUIDataGridResult();
		result.setRows(list);
		PageInfo<TbItem> pageInfo = new PageInfo<>(list);
		result.setTotal(pageInfo.getTotal());
		return JingXiResult.ok(result);
	}

	
}
