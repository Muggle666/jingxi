package com.jingxi.rest.service;

import java.util.List;

import com.jingxi.model.TbContent;

public interface ContentService {
	List<TbContent> getContentList(long contentCid);
}
