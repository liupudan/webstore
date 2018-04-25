package com.chinasofti.jfreechart;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import com.chinasofti.entity.UserHobby;
import com.chinasofti.util.FileUtil;

public class CreateXMLDocument {
	public static void createXMLDocument(ArrayList<UserHobby> arrayList) {
		Element root = DocumentHelper.createElement("userhobbies");
		for (UserHobby uhobby : arrayList) {
			Element userhobby = root.addElement("userhobby");
			userhobby.addElement("username").addText(uhobby.getUsername());
			userhobby.addElement("viewCount").addText(String.valueOf(uhobby.getViewCount()));
			userhobby.addElement("className").addText(uhobby.getClassName());
		}
		Document document = DocumentHelper.createDocument(root);
		OutputFormat format = new OutputFormat("     ", true);
		format.setEncoding("UTF-8");
		try {
			XMLWriter xmlWriter = new XMLWriter(new FileOutputStream(FileUtil.xmlFilePath), format);
			xmlWriter.write(document);
			xmlWriter.flush();
			xmlWriter.close();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void createXMLDocument(String username,int viewCount,String className) {
		if (FileUtil.flag == 1) {
			FileUtil.root = DocumentHelper.createElement("userhobbies");
			FileUtil.document = DocumentHelper.createDocument(FileUtil.root);
			FileUtil.flag = 2;
		}
		Element element1 = FileUtil.root.addElement("userhobby");
		element1.addElement("username").addText(username);
		element1.addElement("viewCount").addText(String.valueOf(viewCount));
		element1.addElement("className").addText(className);
		OutputFormat format = new OutputFormat("", true);
		try {
			XMLWriter xmlWriter = new XMLWriter(new FileOutputStream(FileUtil.xmlFilePath), format);
			xmlWriter.write(FileUtil.document);
			xmlWriter.flush();
			xmlWriter.close();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
