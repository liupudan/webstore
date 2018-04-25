package com.chinasofti.service;

import java.util.ArrayList;

import com.chinasofti.dao.ProductDao;
import com.chinasofti.entity.Product;

public class ProductService {
	public ArrayList<Product> selectAllPro() {
		ProductDao pd = new ProductDao();
		return pd.selectAllPro();
	}
	public Product findProductById(int id) {
		return new ProductDao().findProductById(id);
	}
	public Product updateViewCount(int id) {
		ProductDao productDAO = new ProductDao();
		// 更新商品浏览次数
		productDAO.updateViewCount(id);
		// 查询到更新过浏览次数的商品对象
		Product p = productDAO.findProductById(id);
		return p;
	}
	public String findClassNameByProductId(int pid) {
		return new ProductDao().findClassNameByProductId(pid);
	}
	public ArrayList<Product> getPaging(int offset, int pageSize) {
		return new ProductDao().getPaging(offset, pageSize);
	}
}	
