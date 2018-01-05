package com.jingxi.rest.service;

import com.jingxi.common.pojo.EasyUIDataGridResult;
import com.jingxi.common.pojo.JingXiResult;

public interface SearchService {
	public JingXiResult search(String q,int rage,int rows);
}
