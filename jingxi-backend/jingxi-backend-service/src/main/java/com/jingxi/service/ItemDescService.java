package com.jingxi.service;

import com.jingxi.common.pojo.JingXiResult;
import com.jingxi.model.TbItemDesc;

public interface ItemDescService {
	public JingXiResult getDescById(Long id);
	public 	JingXiResult updateItemDesc(TbItemDesc itemDesc);
}
