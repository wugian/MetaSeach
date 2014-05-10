package com.meta.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HtmlUtil {
	public static String getTheReplaced(String content) {
		List<String> finds = new ArrayList<String>();
		finds.add("java");
		finds.add("设计模式");

		for (int i = 0; i < finds.size(); i++) {
			content = replaceAll(content, finds.get(i));
		}
		return content;
	}

	private static String replaceAll(String content, String find) {
		String font_start = "<font color=\"red\">";
		String font_end = "</font>";
		return content.replaceAll("(?i)" + find, font_start + find + font_end);
	}

	public static String getResult(String content) {
		System.out.println("****************************************start");
		System.out.println(content);
		StringBuffer sb = new StringBuffer(content);
		ArrayList<Integer> stats = new ArrayList<Integer>();
		ArrayList<Integer> ends = new ArrayList<Integer>();
		String font_start = "<font color=\"red\">";
		int font_start_count = font_start.length();
		String font_end = "</font>";
		int font_end_count = font_end.length();
		int offset = 0;

		Pattern sumaryPattern = Pattern.compile("java",
				Pattern.CASE_INSENSITIVE);
		Matcher sumaryMatcher = sumaryPattern.matcher(content);
		// content.replaceAll("", replacement)
		while (sumaryMatcher.find()) {
			int start = sumaryMatcher.start();
			int end = sumaryMatcher.end();
			System.out.println("start:end is " + start + ":" + end);
			// insert the position
			stats.add(start + offset);
			ends.add((start + font_start.length() + end + offset));
			offset += font_start_count + font_end_count + 4;
		}// end of while

		for (int i = 0; i < stats.size(); i++) {
			sb.insert(stats.get(i), font_start);
			System.out.println(sb.toString());
			sb.insert(ends.get(i), font_end);
			System.out.println(sb.toString());
			System.out.println("stats.get(i):ends.get(i) " + stats.get(i) + ","
					+ ends.get(i));
		}

		System.out.println("end****************************************");
		return sb.toString();
	}
}
