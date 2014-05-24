package com.meta.util;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

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
		// LOG.error(content);
		for (int i = 0; i < finds.size(); i++) {
			content = replaceAll(content, finds.get(i));
		}
		// LOG.error(content);
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
				// LOG.debug(word.getValue());
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

}
