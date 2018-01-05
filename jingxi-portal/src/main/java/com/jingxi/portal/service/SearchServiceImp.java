package com.jingxi.portal.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.jingxi.common.pojo.EasyUIDataGridResult;
import com.jingxi.common.pojo.JingXiResult;
import com.jingxi.common.util.HttpClientUtil;
import com.jingxi.model.TbItem;

@Service
public class SearchServiceImp implements SearchService {

	@Value("${REST_BASE_URL}")
	private String REST_BASE_URL;

	@Value("${ITEM_SEARCH_URL}")
	private String ITEM_SEARCH_URL;

	@SuppressWarnings("unchecked")
	@Override
	public List<TbItem> search(String q, String page, String rows) {
		Map<String, String> param = new HashMap<String, String>();
		param.put("q", q);
		param.put("page", page);
		param.put("rows", rows);
		String json = HttpClientUtil.doGet(REST_BASE_URL + ITEM_SEARCH_URL, param);
		try {
			JingXiResult jingxi = JingXiResult.formatToPojo(json, EasyUIDataGridResult.class);
			EasyUIDataGridResult result = (EasyUIDataGridResult) jingxi.getData();
			List<TbItem> list = (List<TbItem>) result.getRows();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
