package com.jingxi.rest.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.jingxi.common.pojo.JingXiResult;
import com.jingxi.common.util.JsonUtils;
import com.jingxi.mapper.TbItemDescMapper;
import com.jingxi.mapper.TbItemMapper;
import com.jingxi.mapper.TbItemParamItemMapper;
import com.jingxi.mapper.TbItemParamMapper;
import com.jingxi.model.TbItem;
import com.jingxi.model.TbItemDesc;
import com.jingxi.model.TbItemParamItem;
import com.jingxi.model.TbItemParamItemExample;
import com.jingxi.model.TbItemParamItemExample.Criteria;
import com.jingxi.rest.dao.JedisClient;

@Service
public class ItemServiceImp implements ItemService {

	@Autowired
	private TbItemMapper itemMapper;
	@Value("${REDIS_ITEM_KEY}")
	private String REDIS_ITEM_KEY;
	@Value("${REDIS_ITEM_EXPIRE}")
	private Integer REDIS_ITEM_EXPIRE;

	@Autowired
	private JedisClient jedisClient;

	@Autowired
	private TbItemDescMapper itemDescMapper;

	@Autowired
	private TbItemParamItemMapper itemParamItemMapper;
	
	@Override
	public JingXiResult getItemBaseInfo(long itemId) {
		try {
			// 添加缓存逻辑
			// 从缓存中取商品信息，商品id对应的信息
			String json = jedisClient.get(REDIS_ITEM_KEY + ":" + itemId + ":base");
			// 判断是否有值
			if (!StringUtils.isBlank(json)) {
				// 把json转换成java对象
				TbItem item = (TbItem) JsonUtils.jsonToPojo(json, TbItem.class);
				return JingXiResult.ok(item);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 根据商品id查询商品信息
		TbItem item = itemMapper.selectByPrimaryKey(itemId);
		// 使用TaotaoResult包装一下
		try {
			// 把商品信息写入缓存
			jedisClient.set(REDIS_ITEM_KEY + ":" + itemId + ":base", JsonUtils.objectToJson(item));
			// 设置key的有效期
			jedisClient.expire(REDIS_ITEM_KEY + ":" + itemId + ":base", REDIS_ITEM_EXPIRE);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JingXiResult.ok(item);
	}

	@Override
	public JingXiResult getItemDesc(Long itemId) {
		// 添加缓存
		try {
			// 添加缓存逻辑
			// 从缓存中取商品信息，商品id对应的消息
			String json = jedisClient.get(REDIS_ITEM_KEY + ":" + itemId + ":desc");
			// 判断是否有值
			if (!StringUtils.isBlank(json)) {
				TbItemDesc itemDesc = (TbItemDesc) JsonUtils.jsonToPojo(json, TbItemDesc.class);
				return JingXiResult.ok(itemDesc);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		TbItemDesc itemDesc = itemDescMapper.selectByPrimaryKey(itemId);
		try {
			// 把商品信息写入缓存
			jedisClient.set(REDIS_ITEM_KEY + "" + itemId + ":desc", JsonUtils.objectToJson(itemDesc));
			// 设置key的有效期
			jedisClient.expire(REDIS_ITEM_KEY + ":" + itemId + ":desc", REDIS_ITEM_EXPIRE);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JingXiResult.ok(itemDesc);
	}

	@Override
	public JingXiResult getItemParam(Long itemId) {
		// 添加缓存
		try {
			// 添加缓存逻辑
			// 从缓存中取商品信息，商品id对应的信息
			String json = jedisClient.get(REDIS_ITEM_KEY + ":" + itemId + ":param");
			// 判断是否有值
			if (!StringUtils.isBlank(json)) {
				// 把json转换成java对象
				TbItemParamItem paramItem = (TbItemParamItem) JsonUtils.jsonToPojo(json, TbItemParamItem.class);
				return JingXiResult.ok(paramItem);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 根据商品id查询规格参数
		// 设置查询条件
		TbItemParamItemExample example = new TbItemParamItemExample();
		Criteria criteria = example.createCriteria();
		criteria.andItemIdEqualTo(itemId);
		// 执行查询
		List<TbItemParamItem> list=itemParamItemMapper.selectByExampleWithBLOBs(example);
		if(list!=null&& list.size()>0){
			TbItemParamItem paramItem=list.get(0);
			try{
				//把商品信息写入缓存
				jedisClient.set(REDIS_ITEM_KEY+":"+ itemId+ ":param",JsonUtils.objectToJson(paramItem));
				//设置key的有效期jedisClient.expire(REDIS_ITEM_KEY+":"+ itemId+ ":param",REDIS_ITEM_EXPIRE);
				}catch(Exception e){
					e.printStackTrace();
					}
			return JingXiResult.ok(paramItem);
			}
		return JingXiResult.build(400,"无此商品规格");
	}
}