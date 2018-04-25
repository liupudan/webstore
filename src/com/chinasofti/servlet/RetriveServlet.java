package com.chinasofti.servlet;

import java.io.IOException;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chinasofti.dao.UserDao;
import com.chinasofti.util.MailUtil;
import com.chinasofti.util.RandomUtil;

public class RetriveServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String retriveAdd = request.getParameter("retrive");
		UserDao ud = new UserDao();
		boolean verify = ud.verifyAdd(retriveAdd);
		if (verify) {
			String password = RandomUtil.getRandomPassword();
			ud.updatePwd(retriveAdd, password);
			try {
				MailUtil.sendRetrieveMail(retriveAdd, password);
			} catch (AddressException e) {
				e.printStackTrace();
			} catch (MessagingException e) {
				e.printStackTrace();
			}
			response.sendRedirect("retrive_success.jsp");
		}else {
			request.getSession().setAttribute("retriveRemind", "邮箱不存在请重新输入");
			response.sendRedirect("retrive.jsp");
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
