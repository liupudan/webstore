package com.chinasofti.entity;

import java.io.Serializable;

public class User implements Serializable{
	private String username;
	private String pwd;
	private String status;//0  审核未通过    1 审核中   2审核通过
	public User() {}
	public User(String username, String pwd, String status) {
		super();
		this.username = username;
		this.pwd = pwd;
		this.status = status;
	}
	public User(String username, String pwd) {
		super();
		this.username = username;
		this.pwd = pwd;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "User [username=" + username + ", pwd=" + pwd + ", status=" + status + "]";
	}
	
}
