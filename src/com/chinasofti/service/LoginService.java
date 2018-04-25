package com.chinasofti.service;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chinasofti.dao.UserDao;
import com.chinasofti.entity.User;
import com.mall.jdbc.ConnectOracle;


public class LoginService extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String check_number = request.getParameter("check_number");
		String session_number = (String)request.getSession().getAttribute(ValidateServlet.CHECK_CODE_KEY);
		if(check_number.equalsIgnoreCase(session_number)) {
			String username = request.getParameter("username");
			String pwd = request.getParameter("pwd");
			UserDao ud = new UserDao();
			User user = ud.login(username, pwd);
			String cname = "";
			String cpwd = "";
			if (user != null) {
				request.getSession().setAttribute("username", username);
				String freelanding = request.getParameter("freelanding");
				if (freelanding != null) {
					Cookie ck_username = new Cookie("ck_username", username);
					Cookie ck_pwd = new Cookie("ck_pwd",pwd);
					response.addCookie(ck_username);
					response.addCookie(ck_pwd);
					response.sendRedirect(request.getContextPath() + "/loginsuccess.jsp");
				}else {
					Cookie[] cookies = request.getCookies();
					for (Cookie coo : cookies) {
						String cooName = coo.getName();
						if (cooName.equals("ck_username") || cooName.equals("ck_pwd")) {
							coo.setMaxAge(0);
							response.addCookie(coo);
						}
					}
					response.sendRedirect(request.getContextPath() + "/loginsuccess.jsp");
				}
				
			}else {
				request.getSession().setAttribute("msg", "用户名或密码错误请重新输入");
				Integer flag = (Integer) request.getSession().getAttribute("count");
				if (flag == null) {
					flag = new Integer(1); 
					request.getSession().setAttribute("count", flag);
					response.sendRedirect(request.getContextPath() + "/login.jsp");
				}else if(flag < 2) {
					flag++;
					request.getSession().setAttribute("count", flag);
					response.sendRedirect(request.getContextPath() + "/login.jsp");
				}else{
					flag++;
					request.getSession().setAttribute("msg", "您已输入三次");
					request.getSession().setAttribute("count", flag);
					response.sendRedirect(request.getContextPath() + "/login.jsp");
				}
			}
		}else {
			request.getSession().setAttribute("msg", "验证码错误");
			response.sendRedirect(request.getContextPath() + "/login.jsp");
		}
	}
}
