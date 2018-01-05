package com.jingxi.portal.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.jingxi.common.pojo.JingXiResult;
import com.jingxi.common.util.HttpClientUtil;
import com.jingxi.common.util.JsonUtils;
import com.jingxi.portal.pojo.Item;
import com.jingxi.portal.pojo.OrderCart;
import com.jingxi.portal.pojo.OrderInfo;

/**
 * 订单管理
 */
@Service
public class OrderServiceImp implements OrderService {

	@Autowired
	private CartServiceImp cartServiceImpl; 
	@Value("${ORDER_BASE_URL}")
	private String ORDER_BASE_URL;
	@Value("${ORDER_CREATE_URL}")
	private String ORDER_CREATE_URL;
	
	
	@Override
	public OrderCart getOrderCart(Long userId, HttpServletRequest request, HttpServletResponse response) {
		//根据id查询用户的配送地址列表（未实现）
		//从cookie中取商品列表
		List<Item> list = cartServiceImpl.getCartItemList(request, response);
		OrderCart orderCart = new OrderCart();
		orderCart.setItemList(list);
		return orderCart;
	}
	@Override
	public JingXiResult createOrder(OrderInfo orderInfo) {
		//先把orderInfo转换成json数据
		String json = JsonUtils.objectToJson(orderInfo);
		//调用订单系统的服务
		String string = HttpClientUtil.doPostJson(ORDER_BASE_URL + ORDER_CREATE_URL, json);
		//把string转换成JingXiResult对象
		JingXiResult result = JingXiResult.format(string);
//		if(result.getStatus() == 200){
//			Object orderId = result.getData();
//			return orderId.toString();
//		}
		return result;
	}

}
