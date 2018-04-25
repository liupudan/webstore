package com.chinasofti.entity;

public class Product {
	
	private int id;
	private String productName;
	private double productPrice;
	private int count;//购买人数
	private String productLogo;
	private String productCity;
	private int viewCount;//浏览次数
	private int classId;//分类名称
	//用户购买个数
	private int productCount;

	public Product() {}
	public Product(int id,String productName, double productPrice, int count, String productLogo, String productCity,int classId,int viewCount) {
		super();
		this.id = id;
		this.productName = productName;
		this.productPrice = productPrice;
		this.count = count;
		this.productLogo = productLogo;
		this.productCity = productCity;
		this.classId = classId;
		this.viewCount = viewCount;
	}
	public Product(int id,String productName, double productPrice, int count, String productLogo, String productCity,int viewCount) {
		super();
		this.id = id;
		this.productName = productName;
		this.productPrice = productPrice;
		this.count = count;
		this.productLogo = productLogo;
		this.productCity = productCity;
		this.viewCount = viewCount;
	}
	
	public int getProductCount() {
		return productCount;
	}
	public void setProductCount(int productCount) {
		this.productCount = productCount;
	}
	public int getClassId() {
		return classId;
	}
	public void setClassId(int classId) {
		this.classId = classId;
	}
	public int getViewCount() {
		return viewCount;
	}

	public void setViewCount(int viewCount) {
		this.viewCount = viewCount;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public double getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getProductLogo() {
		return productLogo;
	}
	public void setProductLogo(String productLogo) {
		this.productLogo = productLogo;
	}
	public String getProductCity() {
		return productCity;
	}
	public void setProductCity(String productCity) {
		this.productCity = productCity;
	}
	@Override
	public String toString() {
		return "Product [productName=" + productName + ", productPrice=" + productPrice + ", count=" + count
				+ ", productLogo=" + productLogo + ", productCity=" + productCity + "viewCount="+viewCount+"]";
	}
}
