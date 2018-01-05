package com.jingxi.portal.pojo;

import java.util.List;

import com.jingxi.model.TbOrder;
import com.jingxi.model.TbOrderItem;
import com.jingxi.model.TbOrderShipping;


/**
 * 订单信息pojo
 */
public class OrderInfo extends TbOrder {
	private List<TbOrderItem> orderItems;
	private TbOrderShipping orderShipping;
	public List<TbOrderItem> getOrderItems() {
		return orderItems;
	}
	public void setOrderItems(List<TbOrderItem> orderItems) {
		this.orderItems = orderItems;
	}
	public TbOrderShipping getOrderShipping() {
		return orderShipping;
	}
	public void setOrderShipping(TbOrderShipping orderShipping) {
		this.orderShipping = orderShipping;
	}
	
}
