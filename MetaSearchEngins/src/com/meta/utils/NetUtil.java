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
		// ���ô����������ַ�Ͷ˿�
		// client.getHostConfiguration().setProxy("proxy_host_addr",proxy_port);
		// ʹ��GET�����������������Ҫͨ��HTTPS���ӣ���ֻ��Ҫ������URL�е�http����https
		DebugUtil.debug("request url:\t" + url);
		HttpMethod method = new GetMethod(url);
		// ʹ��POST����
		// HttpMethod method = new PostMethod("http://java.sun.com";);
		try {
			// ִ��
			client.executeMethod(method);
			// ��ӡ���������ص�״̬
			// System.out.println("====status line:" + method.getStatusLine());
			DebugUtil.debug("status line:\t"
					+ method.getStatusLine().toString());
			// ��ӡ���ص���Ϣ
			Header[] headers = method.getRequestHeaders();
			for (int i = 0; i < headers.length; i++) {
				DebugUtil.debug("header" + (i + 1) + ":\t"
						+ headers[i].getName() + ":" + headers[i].getValue());
			}

			result = method.getResponseBodyAsString();
			// ��ӡ���
			DebugUtil.htmlSave(method.getResponseBodyAsStream());
			// DebugUtil.debug(result);
			// �ͷ�����
			method.releaseConnection();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
}
