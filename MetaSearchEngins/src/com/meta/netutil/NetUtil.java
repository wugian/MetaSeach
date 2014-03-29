package com.meta.netutil;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;

import com.meta.util.DebugUtil;
import com.meta.util.TypeConversion;

/**
 * ��������࣬��Ҫ���ڵõ�������ʽ������־�鿴
 * 
 * @author tezuka-pc
 * 
 */
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

			//result = method.getResponseBodyAsString();
			InputStream s = method.getResponseBodyAsStream();
			System.out.println("=====>"+TypeConversion.inputStream2String(s));
			// ��ӡ���
			// DebugUtil.htmlSave(method.getResponseBodyAsStream());
			// DebugUtil.debug(result);
			// �ͷ�����
			method.releaseConnection();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(result);
		return result;
	}
}
