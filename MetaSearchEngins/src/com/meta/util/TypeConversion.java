package com.meta.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

/**
 * 常用类型转化类
 * 
 * @author tezuka-pc
 * 
 */
public class TypeConversion {
	public static String inputStream2String(InputStream in) {
		StringBuffer out = new StringBuffer();
		byte[] b = new byte[4 * 1024];
		try {
			for (int n; (n = in.read(b)) != -1;) {
				out.append(new String(b, 0, n));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return out.toString();
	}

	public static InputStream string2Stream(String string) {
		return new ByteArrayInputStream(string.getBytes());
	}

	public static String formatConvert(String seachContent, String charset)
			throws UnsupportedEncodingException {
		return new String(seachContent.getBytes("utf-8"), charset);
	}

	public static void main(String[] args) {
		try {
			System.out.println(formatConvert("中国", "utf-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
