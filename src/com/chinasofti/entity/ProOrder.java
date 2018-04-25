package com.chinasofti.entity;
//中间表，关联order表和商品表
public class ProOrder {
	private int id;
	private int proNum;//商品购买数量
	private double proTotal;//单价
	private int orderId;//关联order表
	private int proId;//关联商品表
	public ProOrder() {}
	public ProOrder(int id, double proTotal, int ordersId, int proId,int proNum) {
		this.id = id;
		this.proNum = proNum;
		this.proTotal = proTotal;
		this.orderId = ordersId;
		this.proId = proId;
	}
	public ProOrder(int orderId, double proTotal, int proId) {
		this.orderId = orderId;
		this.proTotal = proTotal;
		this.proId = proId;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getProNum() {
		return proNum;
	}
	public void setProNum(int proNum) {
		this.proNum = proNum;
	}
	public double getProTotal() {
		return proTotal;
	}
	public void setProTotal(double proTotal) {
		this.proTotal = proTotal;
	}
	public int getOrdersId() {
		return orderId;
	}
	public void setOrdersId(int ordersId) {
		this.orderId = ordersId;
	}
	public int getProId() {
		return proId;
	}
	public void setProId(int proId) {
		this.proId = proId;
	}
	@Override
	public String toString() {
		return "ProOrder [id=" + id + ", proNum=" + proNum + ", proTotal=" + proTotal + ", ordersId=" + orderId
				+ ", proId=" + proId + "]";
	}
	
}
