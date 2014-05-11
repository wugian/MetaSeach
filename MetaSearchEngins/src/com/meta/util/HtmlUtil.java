package com.meta.util;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.lionsoul.jcseg.ASegment;
import org.lionsoul.jcseg.core.ADictionary;
import org.lionsoul.jcseg.core.DictionaryFactory;
import org.lionsoul.jcseg.core.IWord;
import org.lionsoul.jcseg.core.JcsegException;
import org.lionsoul.jcseg.core.JcsegTaskConfig;
import org.lionsoul.jcseg.core.SegmentFactory;

public class HtmlUtil {

	private static String FONT_START = "<font color=\"red\">";
	private static String FONT_END = "</font>";
	List<String> finds = null;

	public HtmlUtil(String searchContent) {
		finds = segmentWord(searchContent.trim());
	}

	public String getTheReplaced(String content) {
		LOG.error(content);
		for (int i = 0; i < finds.size(); i++) {
			content = replaceAll(content, finds.get(i));
		}
		LOG.error(content);
		return content;
	}

	private static String replaceAll(String content, String find) {
		String content1 = content.replaceAll("(?i)" + find, FONT_START + find
				+ FONT_END);
		return replaceExtra(content1, find);
	}

	private static String replaceExtra(String content, String find) {
		return content.replaceAll(FONT_END + FONT_START, "");
	}

	private static List<String> segmentWord(String cnTest) {
		List<String> result = new ArrayList<String>();
		JcsegTaskConfig config = new JcsegTaskConfig(
				"D:\\jcseg-1.9.2\\jcseg.properties");
		// 创建默认词库(即: com.webssky.jcseg.Dictionary 对象)
		// 并且依据给定的 JcsegTaskConfig 配置实例自主完成词库的加载
		ADictionary dic = DictionaryFactory.createDefaultDictionary(config);
		ASegment seg;
		try {
			seg = (ASegment) SegmentFactory.createJcseg(
					JcsegTaskConfig.COMPLEX_MODE, new Object[] { config, dic });
			seg.reset(new StringReader(cnTest));
			// 获取分词结果
			IWord word = null;
			while ((word = seg.next()) != null) {
				result.add(word.getValue());
				LOG.debug(word.getValue());
				// LOG.debug(word.getPinyin());
			}
		} catch (JcsegException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
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
