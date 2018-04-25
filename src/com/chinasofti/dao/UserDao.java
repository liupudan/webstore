package com.chinasofti.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.chinasofti.entity.User;
import com.mall.jdbc.ConnectOracle;

public class UserDao {
	public User login(String username,String pwd) {
		Connection connection = ConnectOracle.connection;
		String sql = "select * from user where username = ? and pwd = ?";
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, pwd);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String usernameSingle = rs.getString(1);
				String pwdSingle = rs.getString(2);
				User user = new User(usernameSingle,pwdSingle);
				return user;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	public boolean verifyAdd(String address) {
		Connection connection = ConnectOracle.connection;
		String sql = "select * from user where userName=?";
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement(sql);
			ps.setString(1, address);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	public void updatePwd(String username,String pwd) {
		Connection connection = ConnectOracle.connection;
		String sql = "Update user set pwd=? where userName=?";
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, pwd);
			ps.setString(2, username);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public boolean insertUser(String username,String password) {
		Connection connection = ConnectOracle.connection;
		String sql = "insert into user values(?,?,2,123,'2017-08-16 14:22:49')";
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1,username);
			ps.setString(2,password);
			if (ps.executeUpdate() == 1) {
				return true;
			}
		} catch (SQLException e) {
		}
		return false;
	}
}
