package com.meta.netutil;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
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
public class NetUtil {
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

	public String getPageContentT(String urlStr) {
		URL url = null;
		String result = null;
		LOG.debug("request url:\t" + urlStr);
		StringBuffer sb = new StringBuffer();
		try {
			// String yahooTestStr =
			// "https://search.yahoo.com/search;_ylt=AjVasyo77rLzvtghPRtgaz.bvZx4?p=java+%E8%AE%BE%E8%AE%A1%E6%A8%A1%E5%BC%8F+&toggle=1&cop=mss&ei=UTF-8&fr=yfp-t-234";//""http://www.baidu.com/#wd=java%20%E8%AE%BE%E8%AE%A1%E6%A8%A1%E5%BC%8F&ie=utf-8&f=3&rsv_bp=1&rsv_spt=1&rsv_sug3=1&rsv_sug4=158&rsv_sug1=1&rsp=9&inputT=0&rsv_sug=1&bs=java%20%E8%AE%BE%E8%AE%A1%E6%A8%A1%E5%BC%8F";
			url = new URL(urlStr);
			URLConnection conn = url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestProperty("User-Agent",
					"Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1)");
			InputStream input = conn.getInputStream();
			result = TypeConversion.inputStream2String(input, "utf-8");
			// saveHtml("a", test);
			sb.append(result);
			// System.out.println(test);
		} catch (final MalformedURLException me) {
			System.out.println("the url that you input is wrong");
			me.getMessage();
		} catch (final IOException e) {
			e.printStackTrace();
		}
		// System.out.println(sb.toString());
		return result;
	}

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

}
