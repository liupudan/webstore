package com.chinasofti.jfreechart;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.jfree.chart.JFreeChart;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.chinasofti.entity.UserHobby;
import com.chinasofti.util.FileUtil;

public class SAXParseXML extends DefaultHandler{
	private static List<UserHobby> hobbys = null;
	private UserHobby userHobby = null;
	private String preTag = null;// 作用是记录解析时的上一个节点名称
	private static List<UserHobby> sampleName;// 相同名字的用户集合
	
	public static JFreeChart drawChart(String username) {
		try {
			SAXParseXML saxParseXML = new SAXParseXML();
			saxParseXML.testSAX();
			HashMap<String, List<UserHobby>> sm = saxParseXML.sampleName(hobbys, username);
			//Set<String> keySet = sm.keySet();
			//for (String key : keySet) {
				//if (key.equals(username)) {
					// 与用户名字一样
					//List<UserHobby> list = sm.get(key);
					JFreeChart chart = saxParseXML.createPieChart(username, sampleName);
					return chart;
				//}
			//}
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return null;
	}
	public static void testSAX() throws Throwable {
		SAXParseXML sax = new SAXParseXML();
		InputStream input = new FileInputStream(FileUtil.xmlFilePath);
		hobbys = sax.getHobbys(input);
		for (UserHobby userHobby : hobbys) {
			System.out.println(userHobby);
		}
	}
	public List<UserHobby> getHobbys(InputStream xmlStream) throws Exception {
		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser parser = factory.newSAXParser();
		SAXParseXML handler = new SAXParseXML();
		parser.parse(xmlStream, handler);
		return handler.getHobbys();
	}
	public List<UserHobby> getHobbys() {
		return hobbys;
	}
	public void startDocument() throws SAXException {
		hobbys = new ArrayList<UserHobby>();
	}
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		if ("userhobby".equals(qName)) {
			userHobby = new UserHobby();
		}
		preTag = qName;// 将正在解析的节点名称赋给preTag
	}
	public void characters(char[] ch, int start, int length) throws SAXException {
		if (preTag != null) {
			String content = new String(ch, start, length);
			if ("username".equals(preTag)) {
				userHobby.setUsername(content);
			} else if ("viewCount".equals(preTag)) {
				userHobby.setViewCount(Integer.parseInt(content));
			} else if ("className".equals(preTag)) {// classId
				userHobby.setClassName(content);
			} else {

			}
		}
	}
	public void endElement(String uri, String localName, String qName) throws SAXException {
		if ("userhobby".equals(qName)) {
			hobbys.add(userHobby);
			userHobby = null;
		}
		preTag = null;
	}
	public HashMap<String, List<UserHobby>> sampleName(List<UserHobby> hobbys, String username) {
		HashMap<String, List<UserHobby>> maps = new HashMap<String, List<UserHobby>>();
		String name = "";
		for (UserHobby userHobby : hobbys) {
			name = userHobby.getUsername();
			if (name.equals(username)) {
				if (!maps.containsKey(username)) {
					sampleName = new ArrayList<UserHobby>();
					sampleName.add(userHobby);
					maps.put(username, sampleName);
				} else {
					sampleName.add(userHobby);
					maps.put(username, sampleName);
				}
			}
		}
		return maps;
	}
	public JFreeChart createPieChart(String username, List<UserHobby> list) {
		return JfreeChartTest.createPie(username, list);
	}
}
