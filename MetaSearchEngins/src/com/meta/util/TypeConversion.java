package com.meta.util;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

/**
 * 常用类型转化类
 * 
 * @author tezuka-pc
 * 
 */
public class TypeConversion {
	public static String inputStream2String(InputStream in_st, String charset)
			throws IOException {
		BufferedReader buff = new BufferedReader(new InputStreamReader(in_st,
				charset));
		StringBuffer res = new StringBuffer();
		String line = "";
		while ((line = buff.readLine()) != null) {
			res.append(line);
		}
		return res.toString();
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
