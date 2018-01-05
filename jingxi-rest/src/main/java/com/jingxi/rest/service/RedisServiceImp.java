package com.jingxi.rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jingxi.common.pojo.JingXiResult;
import com.jingxi.common.util.ExceptionUtil;
import com.jingxi.rest.dao.JedisClient;

@Service
public class RedisServiceImp implements RedisService{
	@Autowired
	private JedisClient jedisClient;
	private static final String INDEX_CONTENT_REDIS_KEY = "INDEX_CONTENT_REDIS_KEY";

	@Override
	public JingXiResult syncContent(long contentCid) {
		try {
			jedisClient.hdel(INDEX_CONTENT_REDIS_KEY, contentCid + "");	
		} catch (Exception e) {
			e.printStackTrace();
			return JingXiResult.build(500, ExceptionUtil.getStackTrace(e));
		}
		
		return JingXiResult.ok();
	}

}
