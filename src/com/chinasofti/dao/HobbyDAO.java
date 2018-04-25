
package com.chinasofti.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.chinasofti.entity.UserHobby;
import com.mall.jdbc.ConnectOracle;

public class HobbyDAO {
	// 插入数据
	public void insert(UserHobby userHobby) {
		Connection connection = ConnectOracle.connection;
		String username = userHobby.getUsername();
		String className = userHobby.getClassName();
		int viewCount = findViewCount(username, className);
		if (viewCount != 0) {
			//当前得用户已经在hobby表中存储过对指定类型商品得浏览
			viewCount ++;
			// 更新操作
			String update = "update hobby set viewCount = ? where username = ? and className = ?";
			try {
				PreparedStatement ps = connection.prepareStatement(update);
				ps.setInt(1, viewCount);
				ps.setString(2, username);
				ps.setString(3, className);
				ps.execute();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			//插入新得一条记录
			String sql = "insert into hobby(username,className,viewCount) values (?,?,?)";
			try {
				PreparedStatement ps = connection.prepareStatement(sql);
				ps.setString(1, username);
				ps.setString(2, className);
				ps.setInt(3, userHobby.getViewCount());
				ps.execute();
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
	}

	// 查找商品浏览次数
	public int findViewCount(String username, String className) {
		Connection connection = ConnectOracle.connection;
		String sql = "select viewCount from hobby where username = ? and className = ?";
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, className);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int viewCount = rs.getInt(1);
				return viewCount;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	/*public static void main(String[] args) {
		new HobbyDAO().insert(new UserHobby("winnie", "水果"));
		int n = new HobbyDAO().findViewCount("winnie", "水果");
		System.out.println(n);
	}*/
	public ArrayList<UserHobby> findHobbies(String username) {
		Connection connection = ConnectOracle.connection;
		String sql = "select * from hobby where username = '" + username +"'";
		System.out.println(sql);
		ArrayList<UserHobby> arrayList = new ArrayList<>();
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String className = rs.getString(3);
				int viewCount = rs.getInt(4);
				UserHobby userHobby = new UserHobby(username, className, viewCount);
				arrayList.add(userHobby);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arrayList;
	}
}
