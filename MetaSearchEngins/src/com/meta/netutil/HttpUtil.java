package com.meta.netutil;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import com.meta.util.LOG;

/**
 * 
 * 
 * @author tezuka-pc
 * 
 */
public class HttpUtil {
	/**
	 * 得到页面内容
	 * 
	 * @param url
	 * @return
	 */
	public String getPageContent(String urlStr) {
		URL url = null;
		String result = null;
		LOG.debug("request url:\t" + urlStr);
		StringBuffer sb = new StringBuffer();
		BufferedReader in = null;
		try {
			url = new URL(urlStr);
			URLConnection conn = url.openConnection();
			// 设置通用的请求属性
			conn.setDoOutput(true);
			String userAgent = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.1 (KHTML, like Gecko) Chrome/21.0.1180.89 Safari/537.1 LBBROWSER";
			conn.setRequestProperty("User-Agent", userAgent);
			// ("User-Agent","Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1)");
			// 定义BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(
					conn.getInputStream(), "utf-8"));
			String line;
			while ((line = in.readLine()) != null) {
				sb.append(line);
				// System.out.println(line);
			}
		} catch (final MalformedURLException me) {
			System.out.println("the url that you input is wrong");
			me.getMessage();
		} catch (final IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return sb.toString();
	}

	/**
	 * save the html page for debug
	 * 
	 * @param filepath
	 * @param str
	 */
	public static void saveHtml(String filepath, String str) {

		try {
			/*
			 * @SuppressWarnings("resource") FileWriter fw = new
			 * FileWriter(filepath); fw.write(str); fw.flush();
			 */
			File file = new File("D:/a.html");
			if (!file.exists()) {
				try {
					file.createNewFile();
					System.out.println("create file");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					System.out.println("exception when create file");
					e.printStackTrace();
				}
			}
			OutputStreamWriter outs = new OutputStreamWriter(
					new FileOutputStream("D:/a.html", true), "utf-8");
			outs.write(str);
			outs.close();
		} catch (IOException e) {
			System.out.println("Error at save html...");
			e.printStackTrace();
		}
	}

	/**
	 * 得到中转后真正的URL主要针对 baidu yahoo...
	 * 
	 * @param url
	 * @return
	 */
	public String getRealUrl(String url) {
		HttpURLConnection conn = null;
		try {
			conn = (HttpURLConnection) new URL(url).openConnection();
			conn.setConnectTimeout(5 * 1000);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		(conn).setInstanceFollowRedirects(false);
		url = (conn.getHeaderField("Location"));
		return url;
	}
}
