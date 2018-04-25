package com.chinasofti.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import oracle.net.aso.a;

import com.alibaba.fastjson.JSON;
import com.chinasofti.service.ClassifyService;

public class SearchClassNameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String keyWord = request.getParameter("keyName");
		ArrayList<String> arrayList = new ClassifyService().getClassNames(keyWord);
		String jString = JSON.toJSONString(arrayList);
		PrintWriter pw = response.getWriter();
		pw.write(jString);
		pw.flush();
		pw.close();
	}
}
