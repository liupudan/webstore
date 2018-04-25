package com.chinasofti.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.chinasofti.entity.Product;
import com.mall.jdbc.ConnectOracle;

public class ProductDao {
	public ArrayList<Product> selectAllPro() {
		Connection connection = ConnectOracle.connection;
		String sql = "select * from product";
		ArrayList<Product> arrayList = new ArrayList<>();
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int id = rs.getInt(1);
				String logo = rs.getString(5);
				double price = rs.getDouble(3);
				int count = rs.getInt(4);
				String name = rs.getString(2);
				int viewCount = rs.getInt(8);
				String city = rs.getString(6);
				int classId = rs.getInt(7);
				Product product = new Product(id,name,price,count,logo,city,viewCount);
				arrayList.add(product);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arrayList;
	}
	public Product findProductById(int id) {
		Product p = null;
		Connection connection = ConnectOracle.connection;
		try {
			String query = "select * from product where id = ? ";
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int pid = rs.getInt(1);
				String productName = rs.getString(2);
				double productPrice = rs.getDouble(3);
				int count = rs.getInt(4);
				String productLogo = rs.getString(5);
				String productCity = rs.getString(6);
				int viewCount = rs.getInt(8);
				// 构造具体的商品对象
				p = new Product(pid, productName, productPrice, count, productLogo, productCity, viewCount);
			}
			// 关闭对象
			ps.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return p;
	}
	public void updateViewCount(int id) {
		Connection connection = ConnectOracle.connection;
		try {
			String sql = "update product set viewCount = viewCount + 1 where id = " + id;
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.executeUpdate();
			// 关闭对象
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public String findClassNameByProductId(int pid) {
		String className = "";
		Connection connection = ConnectOracle.connection;
		// 根据id查找商品分类的id
		int classId = findClassIdByProductId(pid);
		// 通过id查找商品分类名称
		String query = "select className from classify where id = ?";
		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setInt(1, classId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				className = rs.getString(1);
			}
			// 关闭对象
			ps.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return className;
	}
	public int findClassIdByProductId(int pid) {
		Connection connection = ConnectOracle.connection;
		String sql = "select classId from product where id =  ?";
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, pid);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int classId = rs.getInt(1);
				return classId;
			}
			// CloseUtil.close(ps, rs);
		} catch (Exception e) {

		}
		return 0;
	}
	public ArrayList<Product> getPaging(int offset, int pageSize) {
		String query = "select * from product limit ?,?";
		ArrayList<Product> pros = new ArrayList<Product>();
		try {
			PreparedStatement ps = ConnectOracle.connection.prepareStatement(query);
			ps.setInt(1, offset);
			ps.setInt(2, pageSize);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int id = rs.getInt(1);
				String name = rs.getString(2);
				double price = rs.getDouble(3);
				int count = rs.getInt(4);
				String proLogo = rs.getString(5);
				String city = rs.getString(6);
				int classId = rs.getInt(7);
				int viewCount = rs.getInt(8);
				Product product = new Product(id, name, price, count, proLogo, city, classId, viewCount);
				pros.add(product);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pros;
	}
}
