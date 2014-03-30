package com.meta.util;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

public class IOUtil {
	public String fileRead(File file) {
		InputStreamReader inputReader = null;
		BufferedReader bufferReader = null;
		OutputStream outputStream = null;
		StringBuffer strBuffer = new StringBuffer();
		try {
			InputStream inputStream = new FileInputStream(file);
			inputReader = new InputStreamReader(inputStream);
			bufferReader = new BufferedReader(inputReader);
			// 读取一行
			String line = null;
			while ((line = bufferReader.readLine()) != null) {
				strBuffer.append(line);
				System.out.println(line);
			}
		} catch (IOException e) {
			LOG.error(e.getMessage());
		} finally {
			try {
				if (outputStream != null)
					outputStream.close();
				if (bufferReader != null)
					bufferReader.close();
				if (inputReader != null)
					inputReader.close();

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return strBuffer.toString();
	}

	public String fileRead(String fileName) {
		try {
			// FileInputStream 用于读取诸如图像数据之类的原始字节流。要读取字符流，请考虑使用 FileReader。
			File file = new File(fileName);
			FileInputStream inStream = new FileInputStream(file);
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			byte[] buffer = new byte[1024];
			int length = -1;
			while ((length = inStream.read(buffer)) != -1) {
				bos.write(buffer, 0, length);
				// .write方法 SDK 的解释是 Writes count bytes from the byte array
				// buffer starting at offset index to this stream.
				// 当流关闭以后内容依然存在
			}
			bos.close();
			inStream.close();
			return bos.toString();
			// 为什么不一次性把buffer得大小取出来呢？为什么还要写入到bos中呢？ return new(buffer,"UTF-8")
			// 不更好么?
			// return new String(bos.toByteArray(),"UTF-8");
		} catch (Exception e) {
		}
		return null;
	}

}
