package com.chinasofti.service;

import java.util.ArrayList;

import com.chinasofti.dao.HobbyDAO;
import com.chinasofti.entity.UserHobby;

public class HobbyService {

	// 插入数据
	public void insert(UserHobby userHobby) {
		new HobbyDAO().insert(userHobby);
	}
	//查找商品浏览次数
	public int findViewCount(String username, String className) {
		return new HobbyDAO().findViewCount(username,className);
	}
	public ArrayList<UserHobby> findHobbies(String username) {
		return new HobbyDAO().findHobbies(username);
	}
}
