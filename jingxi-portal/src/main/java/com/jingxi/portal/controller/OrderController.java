package com.jingxi.portal.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jingxi.common.pojo.JingXiResult;
import com.jingxi.model.TbUser;
import com.jingxi.portal.pojo.OrderCart;
import com.jingxi.portal.pojo.OrderInfo;
import com.jingxi.portal.service.OrderService;

/**
 * 商品订单管理
 */
@Controller
@RequestMapping("/order")
public class OrderController {

	@Autowired
	private OrderService orderService;
	
	@RequestMapping("/order-cart")
	public String showOrderCart(HttpServletRequest request, HttpServletResponse response, Model model) {
		//取用户信息
		TbUser user = (TbUser) request.getAttribute("user");
		OrderCart orderCart = orderService.getOrderCart(user.getId(), request, response);
		//把物流信息和购物车商品列表传递给jsp
		model.addAttribute("shippingList", orderCart.getShippingList());//未实现
		model.addAttribute("cartList", orderCart.getItemList());
		//返回逻辑视图
		return "order-cart";
	}
	
	@RequestMapping("/create")
	public String createOrder(OrderInfo orderInfo, HttpServletRequest request, Model model) {
		//取用户信息
		TbUser user = (TbUser) request.getAttribute("user");
		orderInfo.setUserId(user.getId());
		orderInfo.setBuyerNick(user.getUsername());
		//调用service创建订单
		JingXiResult result = orderService.createOrder(orderInfo);
		if (result.getStatus() == 200) {
			model.addAttribute("orderId", result.getData());
			model.addAttribute("payment", orderInfo.getPayment());
			model.addAttribute("date", new DateTime().plusDays(3).toString("yyyy-MM-dd"));
			return "success";
		}
		model.addAttribute("message", result.getMsg());
		return "error/exception";
	}
}
