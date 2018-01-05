package com.jingxi.rest.service;

import com.jingxi.common.pojo.JingXiResult;

public interface RedisService {
	JingXiResult syncContent(long contentCid);
}
