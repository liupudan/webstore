package com.chinasofti.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.chinasofti.entity.Product;
import com.chinasofti.service.ProductService;

public class TransferHomeServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			if (request.getParameter("flag") == null) {
				System.out.println("第一个if");
				request.getSession().setAttribute("flag", "n");
				ProductService productService = new ProductService();
				ArrayList<Product> pages = productService.getPaging(0, 8);
				/*JSONArray jsonArr = new JSONArray();
				for (Product pro : pages) {
					JSONObject jsonObj = new JSONObject();
					jsonObj.put("id", pro.getId());
					jsonObj.put("logo", pro.getProductLogo());
					jsonObj.put("price", pro.getProductPrice());
					jsonObj.put("count", pro.getCount());
					jsonObj.put("name", pro.getProductName());
					jsonObj.put("viewCount", pro.getViewCount());
					jsonObj.put("city", pro.getProductCity());
					jsonArr.add(jsonObj);
				}*/
				ArrayList<Product> pros = productService.selectAllPro();
				int total = pros.size();
				int page = 1;
				request.setAttribute("all", pages);
				request.setAttribute("total", total);
				request.setAttribute("curPage", page);
				request.getRequestDispatcher("home.jsp").forward(request, response);
			}else {
				System.out.println("第二个if");
				ProductService productService = new ProductService();
				int offset = Integer.parseInt((String)request.getParameter("offset"));
				ArrayList<Product> pages = productService.getPaging(offset, 8);
				ArrayList<Product> pros = productService.selectAllPro();
				int total = pros.size();
				int page = Integer.parseInt((String)request.getParameter("num"));
				System.out.println("page是" + page);
				request.setAttribute("all", pages);
				request.setAttribute("total", total);
				request.setAttribute("curPage", page);
				request.getRequestDispatcher("home.jsp").forward(request, response);
			}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
