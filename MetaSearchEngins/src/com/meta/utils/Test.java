package com.meta.utils;

import java.util.List;

import com.sun.org.apache.xerces.internal.parsers.DOMParser;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.io.File;
import java.net.HttpURLConnection;
import java.net.URL;

import org.htmlparser.visitors.TextExtractingVisitor;

import org.htmlparser.Parser;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		// NetUtil netUtil = new NetUtil();
		// netUtil.getPageContent("http://www.cnblogs.com/zhuawang/archive/2012/12/08/2809380.html");
		// Document doc = null;
		// try {
		// doc = Jsoup.connect("http://www.baidu.com/#wd=天空").get();
		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// // Document doc = Jsoup.parse(input, "gb2312","");
		// System.out.println("-----------------------------------------------");
		// System.out.println(doc.html());
		// System.out.println("-----------------------------------------------");
		// paser(doc);tp
		String s = HttpUtil.getCotentFromUrl("http://www.baidu.com/#wd=天空");
		String title = HttpUtil.getTitle(s);
		DOMParser parser = new DOMParser();
		//TODO download htmlparser after parser the js might be parse the get the html String
		//TIME 23时0分 二○一四年三月十三日
		try {
			Parser parser = new Parser(
					(HttpURLConnection) (new URL(
							"http://127.0.0.1:8080/HTMLParserTester.html"))
							.openConnection());

			TextExtractingVisitor visitor = new TextExtractingVisitor();
			parser.visitAllNodesWith(visitor);
			String textInPage = visitor.getExtractedText();

		} catch (Exception e) {
			// TODO: handle exception
		}

		// (new DOMParser()).parseFromString(s, "text/html");
		// System.out.println(s);
		List<String> all = HttpUtil.getLink(s);
		for (int index = 0; index < all.size(); index++) {
			System.out.println(all.get(index));
		}
	}
}
