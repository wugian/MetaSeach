package com.meta.netutil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HttpUtil {

	private static String ENCODE = "GBK";
	private static String TITLE_REGEX = "<title>.*?</title>";

	public static String getCotentFromUrl(String urlStr) {
		URL url = null;
		String temp;
		StringBuffer sb = new StringBuffer();
		try {
			url = new URL(urlStr);

			/** 修改 ****************/
			URLConnection conn = url.openConnection();
			conn.setRequestProperty("User-Agent",
					"Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
			BufferedReader in = new BufferedReader(new InputStreamReader(
					conn.getInputStream(), "utf-8")); 
			// HttpURLConnection connection = (HttpURLConnection) url.
			// openConnection();
			//
			//
			// connection.setRequestProperty("User-Agent",
			// "Mozilla/4.0 (compatible; MSIE 5.0; Windows 7; DigExt)");
			//
			// BufferedReader in = new BufferedReader(new InputStreamReader(
			// url.openStream(), ENCODE));// 读取网页全部内容

			while ((temp = in.readLine()) != null) {
				sb.append(temp);
			}
			in.close();
		} catch (final MalformedURLException me) {
			System.out.println("你输入的URL格式有问题!");
			me.getMessage();
		} catch (final IOException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}

	public static String getTitle(String s) {
		String regex;
		String title = "";
		List<String> list = new ArrayList<String>();
		regex = "<title>.*?</title>";
		Pattern pa = Pattern.compile(regex, Pattern.CANON_EQ);
		Matcher ma = pa.matcher(s);
		while (ma.find()) {
			list.add(ma.group());
		}
		for (int i = 0; i < list.size(); i++) {
			title = title + list.get(i);
		}
		return outTag(title);
	}

	/**
	 * 
	 * @param s
	 * @return 获得链接
	 */
	public static List<String> getLink(String s) {
		String regex;
		List<String> list = new ArrayList<String>();
		regex = "<a[^>]*href=(\"([^\"]*)\"|\'([^\']*)\'|([^\\s>]*))[^>]*>(.*?)</a>";
		Pattern pa = Pattern.compile(regex, Pattern.DOTALL);
		Matcher ma = pa.matcher(s);
		while (ma.find()) {
			list.add(ma.group());
		}
		return list;
	}

	/**
	 * 
	 * @param s
	 * @return 获得脚本代码
	 */
	public List<String> getScript(String s) {
		String regex;
		List<String> list = new ArrayList<String>();
		regex = "<SCRIPT.*?</SCRIPT>";
		Pattern pa = Pattern.compile(regex, Pattern.DOTALL);
		Matcher ma = pa.matcher(s);
		while (ma.find()) {
			list.add(ma.group());
		}
		return list;
	}

	public List<String> getNews(String s) {
		String regex = "<a.*?</a>";
		Pattern pa = Pattern.compile(regex, Pattern.DOTALL);
		Matcher ma = pa.matcher(s);
		List<String> list = new ArrayList<String>();
		while (ma.find()) {
			list.add(ma.group());
		}
		return list;
	}

	/**
	 * 
	 * @param s
	 * @return 获得CSS
	 */
	public List<String> getCSS(String s) {
		String regex;
		List<String> list = new ArrayList<String>();
		regex = "<style.*?</style>";
		Pattern pa = Pattern.compile(regex, Pattern.DOTALL);
		Matcher ma = pa.matcher(s);
		while (ma.find()) {
			list.add(ma.group());
		}
		return list;
	}

	/**
	 * 
	 * @param s
	 * @return 去掉标记
	 */
	public static String outTag(final String s) {
		return s.replaceAll("<.*?>", "");
	}

	public static void main(String[] args) {
		// Test t = new Test();
		// String content = t.getHtmlContent("http://www.taobao.com");
		// //content = content.replaceAll("(<br>)+?", "\n");// 转化换行
		// //content = content.replaceAll("<p><em>.*?</em></p>", "");// 去图片注释
		// System.out.println(content);
		// System.out.println(t.getTitle(content));
		// List<String> a = t.getNews(content);
		// List<String> news = new ArrayList<String>();
		// for (String s : a) {
		// news.add(s.replaceAll("<.*?>", ""));
		// }
		// System.out.println(news);
		// //…… 获取js、css等操作省略
	}
}
