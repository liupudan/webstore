package com.chinasofti.servlet;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chinasofti.service.CartService;

public class CartServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		int pid = Integer.parseInt(request.getParameter("pid"));
		int pcount = Integer.parseInt(request.getParameter("count"));
		double price = Double.parseDouble(request.getParameter("price"));
		String loginName = (String) request.getSession().getAttribute("username");
		CartService cService = new CartService();
		cService.insertProducts(pid, pcount, loginName, price);
		/*request.getRequestDispatcher("cart.jsp").forward(request, response);*/
		response.sendRedirect("cart.jsp");
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
