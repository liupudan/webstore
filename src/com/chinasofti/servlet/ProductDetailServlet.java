package com.chinasofti.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.chinasofti.entity.Product;
import com.chinasofti.entity.UserHobby;
import com.chinasofti.jfreechart.CreateXMLDocument;
import com.chinasofti.service.HobbyService;
import com.chinasofti.service.ProductService;

public class ProductDetailServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String pid = request.getParameter("pid");
		int id = Integer.parseInt(pid);
		ProductService proService = new ProductService();
		Product p = proService.findProductById(id);
		p = proService.updateViewCount(p.getId());
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");
		String className = proService.findClassNameByProductId(id);
		UserHobby userHobby = new UserHobby(username,className);
		HobbyService hobbyService = new HobbyService();
		int viewCount = hobbyService.findViewCount(username, className);
		if (viewCount == 0) {
			//新的浏览数据
			viewCount = 1;
		}
		userHobby.setViewCount(viewCount);
		hobbyService.insert(userHobby);
		ArrayList<UserHobby> arrayList = hobbyService.findHobbies(username);
		System.out.println(arrayList+"----------------------------");
		CreateXMLDocument.createXMLDocument(arrayList);
		request.setAttribute("product", p);
		//存储图片名称
		request.setAttribute("proLogoName", p.getProductLogo());
		request.getRequestDispatcher("details.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
