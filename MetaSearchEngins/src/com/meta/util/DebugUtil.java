package com.meta.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
/**
 * 调试工具类，在正式发布时会通过DEGUG关闭
 * @author tezuka-pc
 *
 */
public class DebugUtil {
	
	private static final boolean DEBUG = true;

	/**
	 * use this you will see a red error in console
	 * if you debug message is 'this is wrong'
	 * then the result text in red
	 * @param error
	 */
	public static void error(String error) {
		if (DEBUG) {
			System.err.println("=====>" + error);
		}
	}

	public static void debug(String debug) {
		if (DEBUG) {
			System.out.println("=====>" + debug);
		}
	}

	public static void htmlSave(InputStream inputStream) {
		boolean save = true;
		if (save) {
			String title = inputStream.toString();
			System.out.println(title);
			File file = new File(title + ".html");
			if (!file.exists()) {
				try {
					file.createNewFile();
					debug("create file " + file.getAbsolutePath());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					error("create new file to save page ERROR" + ":" + title);
					e.printStackTrace();
				}
			}
			OutputStream os = null;
			try {
				if (!file.isFile()) {
					return;
				}
				os = new FileOutputStream(file);
				byte buffer[] = new byte[4 * 1024];
				while ((inputStream.read(buffer)) != -1) {
					os.write(buffer);
				}
				os.flush();
			} catch (Exception e) {
				error("write file ERROR");
				e.printStackTrace();
			} finally {
				try {
					os.close();
					debug("write success");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
}
