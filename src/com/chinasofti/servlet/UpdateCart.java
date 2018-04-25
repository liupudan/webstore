package com.chinasofti.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chinasofti.service.CartService;

public class UpdateCart extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String proIdString = request.getParameter("proId");
		int proIdint = Integer.parseInt(proIdString);
		String countString = request.getParameter("count");
		int countint = Integer.parseInt(countString);
		String username = (String) request.getSession().getAttribute("username");
		CartService cs = new CartService();
		double subTotal = cs.updateCart(proIdint, countint, username);
		PrintWriter pw = response.getWriter();
		pw.print(subTotal);
	}
}
