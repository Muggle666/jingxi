package com.jingxi.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jingxi.VO.TbItemParamVO;
import com.jingxi.common.pojo.EasyUIDataGridResult;
import com.jingxi.common.pojo.JingXiResult;
import com.jingxi.mapper.TbItemCatMapper;
import com.jingxi.mapper.TbItemMapper;
import com.jingxi.mapper.TbItemParamMapper;
import com.jingxi.model.TbItemParam;
import com.jingxi.model.TbItemParamExample;

@Service
public class ItemParamServiceImp implements ItemParamService {

	@Autowired
	private TbItemParamMapper tbItemParamMapper;

	@Autowired
	private TbItemMapper tbItemMapper;

	@Autowired
	private TbItemCatMapper tbItemCatMapper;

	// 商品规格参数展示
	@Override
	public EasyUIDataGridResult getItemParam(Integer page, Integer rows) {
		TbItemParamExample paramExample = new TbItemParamExample();
		// 分页处理
		PageHelper.startPage(page, rows);
		List<TbItemParam> list = tbItemParamMapper.selectByExample(paramExample);
		List<TbItemParamVO> paramVO = new ArrayList<>();
		for (TbItemParam param : list) {
			TbItemParamVO vo = new TbItemParamVO();
			vo.setId(param.getId());
			vo.setName(tbItemCatMapper.selectByPrimaryKey(param.getItemCatId()).getName());
			vo.setItemCatId(param.getItemCatId());
			vo.setCreated(param.getCreated());
			vo.setUpdated(param.getUpdated());
			paramVO.add(vo);
		}
		// 创建一个返回值对象
		EasyUIDataGridResult result = new EasyUIDataGridResult();
		result.setRows(paramVO);
		// 取记录总条数
		PageInfo<TbItemParam> pageInfo = new PageInfo<>(list);
		result.setTotal(pageInfo.getTotal());
		return result;
	}

	// 判断商品规格参数列表是否已添加
	@Override
	public JingXiResult createItemParam(Long id) {
		TbItemParamExample paramExample = new TbItemParamExample();
		List<TbItemParam> list = tbItemParamMapper.selectByExample(paramExample);
		for (TbItemParam param : list) {
			Long itemCatId = param.getItemCatId();
			if (itemCatId == id) {
				return JingXiResult.build(500, "该类目已经添加，请选择其他类目。", "Error");
			}
		}
		TbItemParam tbItemParam = new TbItemParam();
		return JingXiResult.build(200,"还未添加，允许添加规格列表", tbItemParam);
	}

	//保存
	@Override
	public JingXiResult saveItemParam(Long cid, TbItemParam tbItemParam) {
		Date date = new Date();
		tbItemParam.setItemCatId(cid);
		tbItemParam.setCreated(date);
		tbItemParam.setUpdated(date);
		tbItemParamMapper.insert(tbItemParam);
		return JingXiResult.ok();
	}

	//新增商品：获得已添加的规格列表
	@Override
	public JingXiResult getAddParam(Long id) {
		TbItemParamExample example = new TbItemParamExample();
		List<TbItemParam> tbItemParam = tbItemParamMapper.selectByExampleWithBLOBs(example);
		for(TbItemParam param : tbItemParam){
			if(param.getItemCatId() == id){
				return JingXiResult.build(200,"允许添加规格参数", param);
			}
		}
		return JingXiResult.build(500, "该类目还未添加，请先添加规格列表...", "Error");
	}
}
