package com.jingxi.service;

import java.util.List;

import com.jingxi.common.pojo.EUTreeNode;
import com.jingxi.common.pojo.JingXiResult;
import com.jingxi.model.TbContentCategory;

public interface ContentCategoryService {

	List<EUTreeNode> getCategoryList(long parentId);

	JingXiResult insertContentCategory(long parentId, String name);

	JingXiResult deleteContentCategory(long id);

	JingXiResult updateContentCategory(TbContentCategory contentCategory, long id, String name);
}
