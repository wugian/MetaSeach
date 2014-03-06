package com.meta.utils;

import java.io.IOException;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;

public class NetUtil {
	public String getPageContent(String url) {
		String result = null;
		HttpClient client = new HttpClient();
		// 设置代理服务器地址和端口
		// client.getHostConfiguration().setProxy("proxy_host_addr",proxy_port);
		// 使用GET方法，如果服务器需要通过HTTPS连接，那只需要将下面URL中的http换成https
		DebugUtil.debug("request url:\t" + url);
		HttpMethod method = new GetMethod(url);
		// 使用POST方法
		// HttpMethod method = new PostMethod("http://java.sun.com";);
		try {
			// 执行
			client.executeMethod(method);
			// 打印服务器返回的状态
			// System.out.println("====status line:" + method.getStatusLine());
			DebugUtil.debug("status line:\t"
					+ method.getStatusLine().toString());
			// 打印返回的信息
			Header[] headers = method.getRequestHeaders();
			for (int i = 0; i < headers.length; i++) {
				DebugUtil.debug("header" + (i + 1) + ":\t"
						+ headers[i].getName() + ":" + headers[i].getValue());
			}

			result = method.getResponseBodyAsString();
			// 打印结果
			DebugUtil.htmlSave(method.getResponseBodyAsStream());
			// DebugUtil.debug(result);
			// 释放连接
			method.releaseConnection();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
}
