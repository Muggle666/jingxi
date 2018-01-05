package com.jingxi.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jingxi.common.pojo.JingXiResult;
import com.jingxi.mapper.TbItemDescMapper;
import com.jingxi.model.TbItemDesc;

@Service
public class ItemDescServiceImp implements ItemDescService{

	@Autowired
	private TbItemDescMapper itemDescMapper;
	
	@Override
	public JingXiResult getDescById(Long id) {
		TbItemDesc itemDesc = itemDescMapper.selectByPrimaryKey(id);
		return JingXiResult.ok(itemDesc);
	}

	@Override
	public JingXiResult updateItemDesc(TbItemDesc itemDesc) {
		Date date = new Date();
		itemDesc.setUpdated(date);
		itemDescMapper.updateByPrimaryKey(itemDesc);
		return JingXiResult.ok();
	}

}
