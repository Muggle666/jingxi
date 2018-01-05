package com.jingxi.portal.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jingxi.common.pojo.JingXiResult;
import com.jingxi.portal.pojo.CartItem;
import com.jingxi.portal.pojo.Item;

public interface CartService {

	JingXiResult addCartItem(long itemId, int num, HttpServletRequest request, HttpServletResponse response);

	List<Item> getCartItemList(HttpServletRequest request, HttpServletResponse response);

	JingXiResult deleteCartItem(long itemId, HttpServletRequest request, HttpServletResponse response);

}
