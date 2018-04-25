package com.chinasofti.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.chinasofti.entity.Product;
import com.chinasofti.service.ProductService;

public class LoadHomeServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		ProductService ps = new ProductService();
		ArrayList<Product> arrayList = ps.selectAllPro();
		JSONArray jsonArr = new JSONArray();
		for (Product pro : arrayList) {
			JSONObject jsonObj = new JSONObject();
			jsonObj.put("id", pro.getId());
			jsonObj.put("logo", pro.getProductLogo());
			jsonObj.put("price", pro.getProductPrice());
			jsonObj.put("count", pro.getCount());
			jsonObj.put("name", pro.getProductName());
			jsonObj.put("viewCount", pro.getViewCount());
			jsonObj.put("city", pro.getProductCity());
			jsonArr.add(jsonObj);
		}
		System.out.println(jsonArr);
		PrintWriter pw = response.getWriter();
		pw.print(jsonArr);
		pw.flush();
		pw.close();
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
