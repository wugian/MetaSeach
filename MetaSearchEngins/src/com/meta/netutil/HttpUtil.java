package com.meta.netutil;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;

import com.meta.util.LOG;
import com.meta.util.TypeConversion;

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
	public String getPageContent(String url) {
		String result = null;

		HttpClient client = new HttpClient();
		LOG.debug("request url:\t" + url);
		HttpMethod method = new GetMethod(url);

		try {
			client.executeMethod(method);
			LOG.debug("status line:\t" + method.getStatusLine().toString());
			Header[] headers = method.getRequestHeaders();
			for (int i = 0; i < headers.length; i++) {
				LOG.debug("header" + (i + 1) + ":\t" + headers[i].getName()
						+ ":" + headers[i].getValue());
			}

			InputStream s = method.getResponseBodyAsStream();
			BufferedReader in = new BufferedReader(new InputStreamReader(s,
					"utf-8"));
			String temp = "";
			while ((temp = in.readLine()) != null) {
				result += (temp);
				System.out.println("lovely: " + temp);
			}
			in.close();
			method.releaseConnection();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 得到页面内容
	 * 
	 * @param url
	 * @return
	 */
	public String getPageContentT(String urlStr) {
		URL url = null;
		String result = null;
		LOG.debug("request url:\t" + urlStr);
		StringBuffer sb = new StringBuffer();
		try {
			url = new URL(urlStr);
			URLConnection conn = url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestProperty("User-Agent",
					"Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1)");
			InputStream input = conn.getInputStream();
			result = TypeConversion.inputStream2String(input, "utf-8");
			// saveHtml("a", test); for debug
			sb.append(result);
		} catch (final MalformedURLException me) {
			System.out.println("the url that you input is wrong");
			me.getMessage();
		} catch (final IOException e) {
			e.printStackTrace();
		}
		return result;
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
