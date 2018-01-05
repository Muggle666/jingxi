package com.jingxi.portal.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.jingxi.common.pojo.JingXiResult;
import com.jingxi.common.util.HttpClientUtil;
import com.jingxi.common.util.JsonUtils;
import com.jingxi.model.TbContent;

@Service
public class ContentServiceImp implements ContentService {

	@Value("${REST_BASE_URL}")
	private String REST_BASE_URL;
	@Value("${REST_INDEX_AD_URL}")
	private String REST_INDEX_AD_URL;

	@Override
	public String getContentList() {
		// 调用服务层的服务
		String result = HttpClientUtil.doGet(REST_BASE_URL+REST_INDEX_AD_URL);
		System.out.println(result);
		// 把字符串转换成TaotaoResult
		try {
			JingXiResult jingXiResult = JingXiResult.formatToList(result, TbContent.class);
			System.out.println(jingXiResult);
			// 取内容列表
			List<TbContent> list = (List<TbContent>) jingXiResult.getData();
			List<Map> resultList = new ArrayList<>();
			// 创建一个jsp页码要求的pojo列表
			for (TbContent tbContent : list) {
				Map map = new HashMap<>();
				map.put("src", tbContent.getPic());
				map.put("height", 240);
				map.put("width", 670);
				map.put("srcB", tbContent.getPic2());
				map.put("widthB", 550);
				map.put("heightB", 240);
				map.put("href", tbContent.getUrl());
				map.put("alt", tbContent.getSubTitle());
				resultList.add(map);
			}
			return JsonUtils.objectToJson(resultList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
