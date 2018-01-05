package com.jingxi.service;

import com.jingxi.common.pojo.EasyUIDataGridResult;
import com.jingxi.common.pojo.JingXiResult;
import com.jingxi.model.TbContent;

public interface ContentService {

	public EasyUIDataGridResult getContentList(Long categoryId,int page, int rows);
	
	JingXiResult insertContent(TbContent content);
	
	JingXiResult editContent(TbContent content);
	
	JingXiResult deleteContent(Long ids);
}
