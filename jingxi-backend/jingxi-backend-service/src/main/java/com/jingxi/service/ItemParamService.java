package com.jingxi.service;

import com.jingxi.VO.TbItemParamVO;
import com.jingxi.common.pojo.EasyUIDataGridResult;
import com.jingxi.common.pojo.JingXiResult;
import com.jingxi.model.TbItemParam;

public interface ItemParamService {

	public EasyUIDataGridResult getItemParam(Integer page , Integer rows);
	
	public JingXiResult createItemParam(Long id);
	
	public JingXiResult saveItemParam(Long cid, TbItemParam tbItemParam);
	
	public JingXiResult getAddParam(Long id);
	
}
