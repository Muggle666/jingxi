package com.jingxi.portal.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jingxi.common.pojo.JingXiResult;
import com.jingxi.portal.pojo.OrderCart;
import com.jingxi.portal.pojo.OrderInfo;


public interface OrderService {

	OrderCart getOrderCart(Long userId, HttpServletRequest request, HttpServletResponse response);
	JingXiResult createOrder(OrderInfo orderInfo);
}
