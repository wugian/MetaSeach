package com.meta.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

public class IOUtil {
	void fileRead(File file) {
		InputStreamReader inputReader = null;
		BufferedReader bufferReader = null;
		OutputStream outputStream = null;
		try {
			InputStream inputStream = new FileInputStream(file);
			inputReader = new InputStreamReader(inputStream);
			bufferReader = new BufferedReader(inputReader);
			// 读取一行
			String line = null;
			StringBuffer strBuffer = new StringBuffer();
			while ((line = bufferReader.readLine()) != null) {
				strBuffer.append(line);
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
	}
}
