package com.jingxi.portal.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.jingxi.common.pojo.JingXiResult;
import com.jingxi.common.util.HttpClientUtil;
import com.jingxi.common.util.JsonUtils;
import com.jingxi.model.TbItem;
import com.jingxi.portal.pojo.CartItem;
import com.jingxi.portal.pojo.CookieUtils;
import com.jingxi.portal.pojo.Item;

@Service
public class CartServiceImp implements CartService {
	@Value("${REST_BASE_URL}")
	private String REST_BASE_URL;
	@Value("${ITME_INFO_URL}")
	private String ITME_INFO_URL;

	@Override
	public JingXiResult addCartItem(long itemId, int num, HttpServletRequest request, HttpServletResponse response) {
		// 取商品信息
		Item cartItem = null;
		// 取购物车商品列表
		List<Item> itemList = getCartItemList(request,response);
		// 判断购物车商品列表中是否存在此商品
		for (Item cItem : itemList) {
			// 如果存在此商品
			if (cItem.getId() == itemId) {
				// 增加商品数量
				cItem.setNum(cItem.getNum() + num);
				cartItem = cItem;
				break;
			}
		}
		if (cartItem == null) {
			cartItem = new Item();
			// 根据商品id查询商品基本信息。
			String json = HttpClientUtil.doGet(REST_BASE_URL + ITME_INFO_URL + itemId);
			// 把json转换成java对象
			JingXiResult jingXiResult = JingXiResult.formatToPojo(json, TbItem.class);
			if (jingXiResult.getStatus() == 200) {
				TbItem item = (TbItem) jingXiResult.getData();
				cartItem.setId(item.getId());
				cartItem.setTitle(item.getTitle());
				cartItem.setImage(item.getImage() == null ? "" : item.getImage().split(",")[0]);
				cartItem.setNum(num);
				cartItem.setPrice(item.getPrice());
			}
			// 添加到购物车列表
			itemList.add(cartItem);
		}
		// 把购物车列表写入cookie
		CookieUtils.setCookie(request, response, "TT_CART", JsonUtils.objectToJson(itemList), true);
		return JingXiResult.ok();
	}
	@Override
	public List<Item> getCartItemList(HttpServletRequest request, HttpServletResponse response) {
		// 从cookie中取商品列表
		String cartJson = CookieUtils.getCookie(request, response,"TT_CART", true);
		if (cartJson == null) {
			return new ArrayList<>();
		}
		// 把json转换成商品列表
		try {
			List<Item> list = JsonUtils.jsonToList(cartJson, Item.class);
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ArrayList<>();
	}

	@Override
	public JingXiResult deleteCartItem(long itemId, HttpServletRequest request, HttpServletResponse response) {
		//从cookie中取购物车商品列表
		List<Item> itemList= getCartItemList(request,response);
		//从列表中找到此商品
		for(Item cartItem: itemList) {
		if(cartItem.getId() == itemId) {
			itemList.remove(cartItem);
			break;
			}
		}
		//把购物车列表重新写入cookie
		CookieUtils.setCookie(request, response, "TT_CART", JsonUtils.objectToJson(itemList), true);
		return JingXiResult.ok();
		}
}
