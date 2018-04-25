package com.chinasofti.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mall.jdbc.ConnectOracle;

public class ClassifyDao {
	public ArrayList<String> getClassNames(String keyWord) {
		Connection connection = ConnectOracle.connection;
		ArrayList<String> arrayList = new ArrayList<>();
		String sql;
		if (keyWord == null || keyWord.equals("")) {
			sql = "select * from classify limit 0,5";
		}else {
			sql = "select * from classify where className like '%" + keyWord + "%'";
		}
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				arrayList.add(rs.getString(2));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arrayList; 
	}
}
