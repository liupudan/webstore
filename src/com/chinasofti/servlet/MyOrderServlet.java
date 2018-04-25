package com.chinasofti.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.chinasofti.entity.Order;
import com.chinasofti.service.OrderService;

public class MyOrderServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		String userName = (String) session.getAttribute("username");
		OrderService orderService = new OrderService();
		ArrayList<Order> orders = orderService.findOrderByUsername(userName);
		JSONArray jsonArray = new JSONArray();
		for (Order order : orders) {
			JSONObject jsonObj = new JSONObject();
			jsonObj.put("orderNum", order.getOrderNum());
			jsonObj.put("total", order.getOrderTotal());
			//格式化时间戳
			Timestamp time = order.getOrderDate();
			String timeStr = time.toString();
			int lastIndex = timeStr.lastIndexOf(".");
			String result = timeStr.substring(0, lastIndex);
			jsonObj.put("orderDate",result);
			jsonObj.put("userName", userName);
			if (order.orderStatus == 1) {
				jsonObj.put("orderStatus", "未支付");
				System.out.println("未支付");
			}else {
				jsonObj.put("orderStatus", "已支付");
				System.out.println("已支付");
			}
			jsonObj.put("orderId", order.getOrderId());
			jsonArray.add(jsonObj);
		}
		PrintWriter out = response.getWriter();
		out.print(jsonArray);
		out.flush();
		out.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
