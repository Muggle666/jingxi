package com.jingxi.portal.service;

import java.util.List;

import com.jingxi.common.pojo.EasyUIDataGridResult;
import com.jingxi.model.TbItem;

public interface SearchService {
	public List<TbItem> search(String q,String page,String rows);
}
