package com.chinasofti.dao;

import java.math.BigDecimal;
import java.nio.channels.SelectableChannel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.chinasofti.entity.Cart;
import com.chinasofti.entity.Product;
import com.chinasofti.service.ProductService;
import com.mall.jdbc.ConnectOracle;

public class CartDao {
	public void insertProducts(int pid,int pcount,String loginName,double price) {
		Connection connection = ConnectOracle.connection;
		Cart cart = findRepeatProduct(pid,loginName,price);
		if (cart == null) {
			String sql = "insert into cart values(?,?,?,?,?)";
			try {
				PreparedStatement ps = connection.prepareStatement(sql);
				ps.setInt(1, pid);
				ps.setInt(2, pcount);
				ps.setString(3, loginName);
				ps.setDouble(4, price);
				BigDecimal bd_price = new BigDecimal(Double.toString(price));
				BigDecimal bd_count = new BigDecimal(Integer.toString(pcount));
				double subTotal = bd_price.multiply(bd_count).doubleValue();
				ps.setDouble(5, subTotal);
				ps.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}	
		}else {
			String sql = "update cart set productCount=?,subTotal=? where userName=? and productId=?";
			try {
				PreparedStatement ps = connection.prepareStatement(sql);
				pcount = pcount + cart.getProductCount();
				ps.setInt(1, pcount);
				BigDecimal bd_price = new BigDecimal(Double.toString(price));
				BigDecimal bd_count = new BigDecimal(Integer.toString(pcount));
				double subTotal = bd_price.multiply(bd_count).doubleValue();
				ps.setDouble(2, subTotal);
				ps.setString(3, loginName);
				ps.setInt(4, pid);
				ps.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}	
		}
	}
	public Cart findRepeatProduct(int pid,String loginName,double price) {
		Connection connection = ConnectOracle.connection;
		String sql = "select * from cart where userName=? and productId=?";
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, loginName);
			ps.setInt(2, pid);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int pcount = rs.getInt(2);
				return new Cart(pid,pcount,loginName,price);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	public ArrayList<Cart> showCart(String username) {
		Connection connection = ConnectOracle.connection;
		String sql = "select * from cart where userName = ?";
		ArrayList<Cart> arrayList = new ArrayList<>();
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				/*int productId, int productCount, String userName, double price,
				double subTotal*/
				int pid = rs.getInt(1);
				int pCount = rs.getInt(2);
				double price = rs.getDouble(4);
				double subTotal = rs.getDouble(5);
				Cart cart = new Cart(pid, pCount, username, price, subTotal);
				arrayList.add(cart);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arrayList;
	}
	public int[] findProductCountById(String[] proId,String username) {
		Connection connection = ConnectOracle.connection;
		String sql = "select productCount from cart where productId=? and userName=?";
		int[] proIdInt = new int[proId.length];
		int[] proCount = new int[proId.length];
		for (int i = 0; i < proIdInt.length; i++) {
			proIdInt[i] = Integer.parseInt(proId[i]);
		}
		try {
			for (int i = 0; i < proIdInt.length; i++) {
				PreparedStatement ps = connection.prepareStatement(sql);
				ps.setInt(1, proIdInt[i]);
				ps.setString(2, username);
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					proCount[i] = rs.getInt(1);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return proCount;
	}
	public void deleteCart(int proId,String userName) {
		Connection connection = ConnectOracle.connection;
		String sql = "delete from cart where productId = ? and userName = ?";
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, proId);
			ps.setString(2, userName);
			ps.executeUpdate();
			System.out.println("删除成功");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public double updateCart(int proId,int proCount,String username) {
		Connection connection = ConnectOracle.connection;
		ProductService productService = new ProductService();
		Product product = productService.findProductById(proId);
		double productPrice = product.getProductPrice();
		String sql = "update cart set productCount=?,subTotal=? where productId=? and username=?";
		BigDecimal bd_price = new BigDecimal(Double.toString(productPrice));
		BigDecimal bd_count = new BigDecimal(Integer.toString(proCount));
		double subTotal = bd_price.multiply(bd_count).doubleValue();
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, proCount);
			ps.setDouble(2, subTotal);
			ps.setInt(3, proId);
			ps.setString(4, username);
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return subTotal;
	}
}
