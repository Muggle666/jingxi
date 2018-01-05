package com.jingxi.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jingxi.common.pojo.EasyUIDataGridResult;
import com.jingxi.common.pojo.JingXiResult;
import com.jingxi.common.util.HttpClientUtil;
import com.jingxi.mapper.TbContentMapper;
import com.jingxi.model.TbContent;
import com.jingxi.model.TbContentExample;

@Service
public class ContentServiceImp implements ContentService {

	@Autowired
	private TbContentMapper contentMapper;

	@Override
	public JingXiResult insertContent(TbContent content) {
		// 补全pojo内容
		content.setCreated(new Date());
		content.setUpdated(new Date());
		contentMapper.insert(content);

		// 添加缓存同步逻辑
		try {
			HttpClientUtil.doGet("http://localhost:8082/rest" + "/cache/sync/content/89" + content.getCategoryId());	
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return JingXiResult.ok();
	}

	@Override
	public EasyUIDataGridResult getContentList(Long categoryId, int page, int rows) {
		if (categoryId != 0) {
			TbContentExample example = new TbContentExample();
			// 分页处理
			PageHelper.startPage(page, rows);
			List<TbContent> list = contentMapper.selectByExampleWithBLOBs(example);
			List<TbContent> selectByIdList = new ArrayList<>();
			for (TbContent selectList : list) {
				if (selectList.getCategoryId() == categoryId) {
					selectByIdList.add(selectList);
				}
			}
			// 创建一个返回值对象
			EasyUIDataGridResult result = new EasyUIDataGridResult();
			result.setRows(selectByIdList);
			// 取记录总条数
			PageInfo<TbContent> pageInfo = new PageInfo<>(selectByIdList);
			result.setTotal(pageInfo.getTotal());
			return result;
		} else {
			return null;
		}
	}

	@Override
	public JingXiResult editContent(TbContent tbContent) {
		tbContent.setUpdated(new Date());
		int status = contentMapper.updateByPrimaryKeySelective(tbContent);
		if (status == 1) {
			return JingXiResult.build(200, "更新成功");
		} else {
			return JingXiResult.build(500, "更新失败");
		}
	}

	@Override
	public JingXiResult deleteContent(Long ids) {
		int status = contentMapper.deleteByPrimaryKey(ids);
		if (status == 1) {
			return JingXiResult.build(200, "删除成功");
		} else {
			return JingXiResult.build(500, "删除失败");
		}
	}

}
