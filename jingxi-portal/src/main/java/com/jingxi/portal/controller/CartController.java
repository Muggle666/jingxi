package com.jingxi.portal.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.druid.support.json.JSONUtils;
import com.jingxi.common.pojo.JingXiResult;
import com.jingxi.common.util.JsonUtils;
import com.jingxi.portal.pojo.CartItem;
import com.jingxi.portal.pojo.CookieUtils;
import com.jingxi.portal.pojo.Item;
import com.jingxi.portal.service.CartService;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/cart")
public class CartController {

	@Autowired
	private CartService cartService;

	@RequestMapping("/add/{itemId}")
	public String addCartItem(@PathVariable Long itemId, @RequestParam(defaultValue = "1") Integer num,
			HttpServletRequest request, HttpServletResponse response) {
		System.out.println("Cart--add");
		JingXiResult result = cartService.addCartItem(itemId, num, request, response);
		return "redirect:/cart/success.html";
	}

	@RequestMapping("/success")
	public String showSuccess() {
		return "cartSuccess";
	}

	@RequestMapping("/cart")
	public String showCart(HttpServletRequest request, HttpServletResponse response, Model model) {
		List<Item> list = cartService.getCartItemList(request, response);
		model.addAttribute("cartList", list);
		return "cart";
	}

	@RequestMapping("/delete/{itemId}")
	public String deleteCartItem(@PathVariable Long itemId, HttpServletRequest request, HttpServletResponse response) {
		cartService.deleteCartItem(itemId, request, response);
		return "redirect:/cart/cart.html";
	}

	// 更新购物车商品数量
	@RequestMapping("/update/num/{itemId}/{num}")
	@ResponseBody
	public JingXiResult incrementCartItem(@PathVariable Long itemId, @PathVariable Integer num, HttpServletRequest request,
			HttpServletResponse response) {
		return JingXiResult.ok();
	}

	// 直接更新购物车商品数量
	@RequestMapping("/update_direct/num/{itemId}/{num}")
	@ResponseBody
	public JingXiResult incrementDirectCartItem(@PathVariable Long itemId, @PathVariable Long num,
			HttpServletRequest request, HttpServletResponse response) {
		return JingXiResult.ok();
	}
}
