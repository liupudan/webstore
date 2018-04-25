package com.chinasofti.service;

import java.util.ArrayList;

import com.chinasofti.dao.ClassifyDao;

public class ClassifyService {
	public ArrayList<String> getClassNames(String keyWord) {
		return new ClassifyDao().getClassNames(keyWord);
	}
}
