package com.chinasofti.service;

import java.sql.Timestamp;
import java.util.ArrayList;

import com.chinasofti.dao.OrderDAO;
import com.chinasofti.entity.Order;
import com.chinasofti.entity.ProOrder;

public class OrderService {

	// 生成订单--根据用户名查询购物车表的所有商品
	public ArrayList<Order> findOrderByUsername(String userName) {
		return new OrderDAO().findOrderByUsername(userName);
	}
	//增加订单信息
	//参数：用户名、总价格、结算商品id、商品购买数量
	public Order addOrder(String userName,double total, String[] proIdArr,int[] proCount) {
		return new OrderDAO().addOrder(userName,total,proIdArr,proCount);
	}
	//在中间表中根据订单编号查找所有商品
	public ArrayList<ProOrder> getProOrder(int orderId) {
		return new OrderDAO().getProOrder(orderId);
	}
	
	public Order getOrder(Timestamp now) {
		return new OrderDAO().getOrder(now);
	}
	
	public Order updateStatuById(int id) {
		return new OrderDAO().updateStatuById(id);
	}
}
