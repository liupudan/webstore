package com.chinasofti.entity;

import java.sql.Timestamp;
import java.util.Date;

public class Order {
	
	private int orderId;
	private int orderNum;//商品数量
	private Timestamp orderDate;//下单时间
	private double orderTotal;//订单总额
	private String userName;//用户名
	public int orderStatus;
	public Order() {}
	public Order(int orderId , int num, Timestamp time, double total, String userName) {
		this.orderId = orderId;
		this.orderNum = num;
		this.orderDate = time;
		this.orderTotal = total;
		this.userName = userName;
	}
	public Order(int orderId, int orderNum, Timestamp orderDate,
			double orderTotal, String userName, int orderStatus) {
		super();
		this.orderId = orderId;
		this.orderNum = orderNum;
		this.orderDate = orderDate;
		this.orderTotal = orderTotal;
		this.userName = userName;
		this.orderStatus = orderStatus;
	}
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public int getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(int orderNum) {
		this.orderNum = orderNum;
	}
	public Timestamp getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Timestamp orderDate) {
		this.orderDate = orderDate;
	}
	public double getOrderTotal() {
		return orderTotal;
	}
	public void setOrderTotal(double orderTotal) {
		this.orderTotal = orderTotal;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", orderNum=" + orderNum + ", orderDate=" + orderDate + ", orderTotal="
				+ orderTotal + ", userName=" + userName + "]";
	}
}
