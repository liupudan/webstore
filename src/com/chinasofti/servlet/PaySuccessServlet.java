package com.chinasofti.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chinasofti.entity.Order;
import com.chinasofti.service.OrderService;

public class PaySuccessServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String orderIdString = request.getParameter("orderjspo");
		int orderIdint = Integer.parseInt(orderIdString);
		OrderService os = new OrderService();
		Order order = os.updateStatuById(orderIdint);
		response.sendRedirect("paysuccess.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
