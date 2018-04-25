package com.chinasofti.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.jfree.chart.JFreeChart;
import org.jfree.chart.servlet.ServletUtilities;

import com.chinasofti.jfreechart.SAXParseXML;

public class DrawChartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");
		JFreeChart chart = SAXParseXML.drawChart(username);
		
		String fileName = ServletUtilities.saveChartAsPNG(chart, 550, 350, session);
		System.out.println("fileName="+fileName);//C:\Users\home\AppData\Local\Temp
		//生成的餅狀圖片访问的地址：graphURL--》img src
		String graphURL = request.getContextPath() + "/DisplayChart?filename=" + fileName;
		System.out.println("graphURL="+graphURL);
		request.setAttribute("graphURL", graphURL);
		request.getRequestDispatcher("client/personal.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
