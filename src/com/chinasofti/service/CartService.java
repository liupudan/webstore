package com.chinasofti.service;

import java.util.ArrayList;

import com.chinasofti.dao.CartDao;
import com.chinasofti.entity.Cart;

public class CartService {
	public void insertProducts(int pid,int pcount,String loginName,double price) {
		new CartDao().insertProducts(pid,pcount,loginName,price);
	}
	public ArrayList<Cart> showCart(String username) {
		return new CartDao().showCart(username);
	}
	public int[] findProductCountById(String[] proId,String username) {
		return new CartDao().findProductCountById(proId, username);
	}
	public void deleteCart(int proId,String userName) {
		new CartDao().deleteCart(proId,userName);
	}
	public double updateCart (int proId,int proCount,String username) {
		return new CartDao().updateCart(proId, proCount, username);
	}
}
