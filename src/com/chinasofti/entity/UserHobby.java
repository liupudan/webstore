package com.chinasofti.entity;

public class UserHobby {
	private String username;//用户名
	private String className;//商品分类
	private int viewCount;//浏览次数
	public UserHobby() {}
	
	public UserHobby(String username, String className, int viewCount) {
		super();
		this.username = username;
		this.className = className;
		this.viewCount = viewCount;
	}
	
	public UserHobby(String username, String className) {
		super();
		this.username = username;
		this.className = className;
	}

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public int getViewCount() {
		return viewCount;
	}
	public void setViewCount(int viewCount) {
		this.viewCount = viewCount;
	}
	@Override
	public String toString() {
		return "UserHobby [username=" + username + ", className=" + className + ", viewCount=" + viewCount + "]";
	}
	
}
