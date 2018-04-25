package com.chinasofti.entity;

public class Cart {
	private int productId;
	private int productCount;
	private String userName;
	private double price;//单价
	private double subTotal;//小计 
	
	public Cart(int productId, int productCount, String userName,double price) {
		super();
		this.productId = productId;
		this.productCount = productCount;
		this.userName = userName;
		this.price = price;
	}
	public Cart(int productId, int productCount, String userName) {
		super();
		this.productId = productId;
		this.productCount = productCount;
		this.userName = userName;
	}
	
	
	public Cart(int productId, int productCount, String userName, double price,
			double subTotal) {
		super();
		this.productId = productId;
		this.productCount = productCount;
		this.userName = userName;
		this.price = price;
		this.subTotal = subTotal;
	}
	public double getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(double subTotal) {
		this.subTotal = subTotal;
	}


	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public int getProductCount() {
		return productCount;
	}
	public void setProductCount(int productCount) {
		this.productCount = productCount;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Override
	public String toString() {
		return "Cart [productId=" + productId + ", productCount=" + productCount + ", userName=" + userName + ", price="
				+ price + "]";
	}
}
