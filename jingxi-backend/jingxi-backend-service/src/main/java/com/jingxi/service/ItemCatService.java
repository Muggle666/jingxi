package com.jingxi.service;

import java.util.List;

import com.jingxi.model.TbItemCat;

public interface ItemCatService {
	public List<TbItemCat> getItemCatList(Long parentId) throws Exception;
}
