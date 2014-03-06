package com.meta.utils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

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
}
