package com.jingxi.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jingxi.common.pojo.JingXiResult;
import com.jingxi.common.util.ExceptionUtil;
import com.jingxi.order.pojo.OrderInfo;
import com.jingxi.order.service.OrderService;

@Controller
public class OrderController {

	@Autowired
	private OrderService orderService;

	@RequestMapping("/create")
	@ResponseBody
	public JingXiResult createOrder(@RequestBody OrderInfo orderInfo) {
		try {
			JingXiResult result = orderService.createOrder(orderInfo);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return JingXiResult.build(500, ExceptionUtil.getStackTrace(e));
		}
	}
}
