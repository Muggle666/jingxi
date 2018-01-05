package com.jingxi.service;

import com.jingxi.common.pojo.EasyUIDataGridResult;
import com.jingxi.common.pojo.JingXiResult;
import com.jingxi.model.TbItem;
import com.jingxi.model.TbItemParam;
import com.jingxi.model.TbItemParamItem;

public interface ItemService {
	public TbItem getTnItemById(long id);

	public EasyUIDataGridResult getItemList(int page, int rows);

	public JingXiResult createItem(TbItem Item, String itemParams, String desc);

	public JingXiResult editItem(TbItem item, String desc);

	public JingXiResult deleteItem(long id);
}
