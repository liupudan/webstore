package com.chinasofti.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import com.chinasofti.entity.Order;
import com.chinasofti.entity.ProOrder;
import com.chinasofti.entity.Product;
import com.mall.jdbc.ConnectOracle;

public class OrderDAO {
	static Connection conn = ConnectOracle.connection;

	// 生成订单--根据用户名查询购物车表的所有商品
	public ArrayList<Order> findOrderByUsername(String userName) {
		String sql = "select * from uorder where userName = ?";
		ArrayList<Order> orders = new ArrayList<Order>();
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, userName);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int orderId = rs.getInt(1);
				int orderNum = rs.getInt(2);
				Timestamp orderDate = rs.getTimestamp(3);
				double orderTotal = rs.getDouble(4);
				int orderStatus = rs.getInt(6);
				Order order = new Order(orderId, orderNum, orderDate, orderTotal, userName,orderStatus);
				orders.add(order);
			}
			return orders;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	// 插入订单
//	public void insertOrder(Order order) {
//		String sql = "insert into uorder (orderNum,orderDate,orderTotal,userName) values (?,?,?,?)";
//		try {
//			PreparedStatement ps = conn.prepareStatement(sql);
//			ps.setInt(1, order.getOrderNum());
//			ps.setTimestamp(2, order.getOrderDate());
//			ps.setDouble(3, order.getOrderTotal());
//			ps.setString(4, order.getUserName());
//
//			ps.execute();
//			System.out.println("订单插入成功！");
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//	}
	// 插入数据--注意关联表的数据插入
	public Order addOrder(String userName, double total, String[] proIdArr, int[] proCount) {
		String sql = "insert into uorder (orderNum,orderDate,orderTotal,userName) values (?,?,?,?)";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, proIdArr.length);//订单商品数量
			// 格式化当前系统时间
			Timestamp now = formateNow();
			ps.setTimestamp(2, now);
			ps.setDouble(3, total);
			ps.setString(4, userName);
			ps.execute();
			// 获取新插入的订单编号
			Order order = getOrder(now);
			// 向中间表插入数据
			insertProOrder(proIdArr, order.getOrderId(), proCount);
			return order;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	private Timestamp formateNow() {
		Date dt = new Date();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String nowTime = df.format(dt);
		java.sql.Timestamp buydate = java.sql.Timestamp.valueOf(nowTime);
		return buydate;

	}

	private void insertProOrder(String[] proIdArr, int orderId, int[] proCountArr) {
		for (int i = 0; i < proIdArr.length; i++) {
			String proIdStr = proIdArr[i];
			int proId = Integer.parseInt(proIdStr);
			// 该商品购买数量
			int proCount = proCountArr[i];
			System.out.println("proCount===="+proCount);
			//proTotal   单价
			String sql = "insert into order_product(proTotal,proId,orderId,proNum) values (" + "?,?,?,?)";
			try {
				PreparedStatement ps = conn.prepareStatement(sql);
				// 根据id查看单价
				double price = getPriceById(proId);
				ps.setDouble(1, price);
				ps.setInt(2, proId);
				ps.setInt(3, orderId);
				ps.setInt(4, proCount);
				ps.execute();
				System.out.println("order_product插入数据成功！");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	private double getPriceById(int proId) {
		String sql = "select productPrice from product where id = " + proId;
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				return rs.getDouble(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	// 根据订单时间查找订单
	public Order getOrder(Timestamp now) {
		String sql = "select * from uorder where orderDate = ? ";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setTimestamp(1, now);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int orderId = rs.getInt(1);
				int num = rs.getInt(2);
				Timestamp time = rs.getTimestamp(3);
				double total = rs.getDouble(4);
				String userName = rs.getString(5);
				Order order = new Order(orderId, num, time, total, userName);
				return order;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public ArrayList<ProOrder> getProOrder(int orderId) {
		ArrayList<ProOrder> proOrders = new ArrayList<ProOrder>();
		String sql = "select * from order_product where orderId = " + orderId;
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int proOrderId = rs.getInt(1);
				double proTotal = rs.getDouble(2);
				int proId = rs.getInt("proId");
				int proNum = rs.getInt("proNum");
				ProOrder proOrder = new ProOrder(proOrderId, proTotal, orderId, proId, proNum);
				proOrders.add(proOrder);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return proOrders;
	}
	public Order updateStatuById(int id) {
		String sql = "update uorder set orderStatus = 2 where orderId = " + id;
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return getOrder(id);
	}
	public Order getOrder(int id) {
		String sql = "select * from uorder where orderId = ? ";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int orderId = rs.getInt(1);
				int num = rs.getInt(2);
				Timestamp time = rs.getTimestamp(3);
				double total = rs.getDouble(4);
				String userName = rs.getString(5);
				int orderStatus = rs.getInt(6);
				Order order = new Order(orderId, num, time, total, userName,orderStatus);
				return order;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
