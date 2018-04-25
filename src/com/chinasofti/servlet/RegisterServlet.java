package com.chinasofti.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chinasofti.dao.UserDao;

public class RegisterServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		System.out.println(username+"-----"+password);
		UserDao ud = new UserDao();
		boolean verify = ud.verifyAdd(username);
		if (verify) {
			request.setAttribute("register", "用户名重复");
			System.out.println("重复");
			request.getRequestDispatcher("register.jsp").forward(request, response);
		}else {
			boolean register = ud.insertUser(username, password);
			if (register) {
				response.sendRedirect("register_success.jsp");
			}else {
				response.sendRedirect("register_defeated.jsp");
			}
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
