package com.chinasofti.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chinasofti.service.CartService;

public class DeleteCart extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String proIdString = request.getParameter("proId");
		int proIdInt = Integer.parseInt(proIdString);
		String username = (String) request.getSession().getAttribute("username");
		CartService cs = new CartService();
		cs.deleteCart(proIdInt, username);
		PrintWriter pw = response.getWriter();
		pw.write("ok");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
}
