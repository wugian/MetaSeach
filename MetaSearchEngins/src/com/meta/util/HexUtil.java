package com.meta.util;

/**
 * 16进制数比较类
 * 
 * @author tezuka-pc
 * 
 */
public class HexUtil {

	/**
	 * 比较两个16进制数的大小如果前大则返回true
	 * 
	 * 如 compare(0x23,0x22) returns true;
	 * 
	 * @param hex1
	 * @param hex2
	 * @return
	 */
	public static boolean compare(String hex1, String hex2) {
		for (int i = 0; i < hex1.length(); i += 2) {
			if (Integer.parseInt(hex1.substring(0 + i, 2 + i), 16) > Integer
					.parseInt(hex2.substring(0 + i, 2 + i), 16))
				return true;
			else if (Integer.parseInt(hex1.substring(0 + i, 2 + i), 16) < Integer
					.parseInt(hex2.substring(0 + i, 2 + i), 16))
				return false;
		}
		return false;
	}
}
