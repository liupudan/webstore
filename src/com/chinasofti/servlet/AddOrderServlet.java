package com.chinasofti.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.chinasofti.entity.Order;
import com.chinasofti.entity.ProOrder;
import com.chinasofti.entity.Product;
import com.chinasofti.service.CartService;
import com.chinasofti.service.OrderService;
import com.chinasofti.service.ProductService;

public class AddOrderServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String[] strIds = request.getParameterValues("proId");
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");
		CartService cs = new CartService();
		int[] strCounts = cs.findProductCountById(strIds, username);
		double total = Double.parseDouble(request.getParameter("total"));
		OrderService orderService = new OrderService();
		Order order = orderService.addOrder(username,total,strIds ,strCounts);
		int orderId = order.getOrderId();
		//根据orderId查找所有的商品-订单对象
		ArrayList<ProOrder> proOrderList = orderService.getProOrder(orderId);
		ProductService proService = new ProductService();
		//该条订单包含的所有的商品集合
		ArrayList<Product> proList = new ArrayList<Product>();
		for(ProOrder proOrder:proOrderList) {
			int proId = proOrder.getProId();//商品编号
			//根据商品ID查找具体的商品对象
			Product pro = proService.findProductById(proId);
			proList.add(pro);
		}
		//从购物车表中删除已经结算的商品
		for(int i = 0;i < strIds.length;i++) {
			int proId = Integer.parseInt(strIds[i]);
			cs.deleteCart(proId,username);
		}
		request.setAttribute("order", order);//订单的总价格
		request.setAttribute("proOrderList", proOrderList);//商品购买的数量
		request.setAttribute("proList", proList);//名称、单价、图片
		request.getRequestDispatcher("order.jsp").forward(request, response);
	}
}
