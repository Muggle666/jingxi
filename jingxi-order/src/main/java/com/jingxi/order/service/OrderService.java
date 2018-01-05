package com.jingxi.order.service;

import com.jingxi.common.pojo.JingXiResult;
import com.jingxi.order.pojo.OrderInfo;

public interface OrderService {

	JingXiResult createOrder(OrderInfo orderInfo);
}
