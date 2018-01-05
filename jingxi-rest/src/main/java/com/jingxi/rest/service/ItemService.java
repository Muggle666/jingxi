package com.jingxi.rest.service;

import com.jingxi.common.pojo.JingXiResult;

public interface ItemService {
	
	public JingXiResult getItemBaseInfo(long itemId);

	public JingXiResult getItemDesc(Long itemId);

	public JingXiResult getItemParam(Long itemId);

}
