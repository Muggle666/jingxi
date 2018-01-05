package com.jingxi.portal.service;

import com.jingxi.portal.pojo.ItemInfo;

public interface ItemService {

	ItemInfo getItemById(Long itemId);
	
	String getItemDescById(Long itemId);
	
	String getItemParam(Long itemId);

}
