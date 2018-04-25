package com.chinasofti.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.chinasofti.entity.Cart;
import com.chinasofti.entity.Product;
import com.chinasofti.service.CartService;
import com.chinasofti.service.ProductService;

public class ShowCartServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String userName = (String) request.getSession().getAttribute("username");
		CartService cs = new CartService();
		ArrayList<Cart> arrayList = cs.showCart(userName);
		JSONArray jsonArray = new JSONArray();
		ProductService ps = new ProductService();
		for (Cart cart : arrayList) {
			int pid = cart.getProductId();
			Product product = ps.findProductById(pid);
			JSONObject jsonObj = new JSONObject();
			jsonObj.put("proLogo", product.getProductLogo());
			jsonObj.put("proName", product.getProductName());
			jsonObj.put("proId", product.getId());//产品id
			jsonObj.put("proPrice", product.getProductPrice());
			jsonObj.put("buyCount", cart.getProductCount());
			jsonObj.put("subTotal", cart.getSubTotal());//小计价格
			jsonArray.add(jsonObj);
		}
		PrintWriter pw = response.getWriter();
		pw.print(jsonArray);
		pw.flush();
		pw.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
