package com.meta.business.factory;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.meta.model.Result;
import com.meta.netutil.HttpUtil;
import com.meta.util.HtmlUtil;
import com.meta.util.LOG;

public class BaiduParser extends BaseParser {

	// URL正则式
	private static final String BAIDU_URL_REGEX = "http://www.baidu.com/.*?\"";// "(http://www.baidu.com/link?url=).*?";
	// Title正则式
	private static final String BAIDU_TITLE_REGEX = "<h3 class=\"t\">.*?</h3>";
	// 摘要正则式
	private static final String BAIDU_SUMARY_REGEX = "<div class=\"c-abstract\">.*?</div>";
	private static final String BAIDU_WENKU_SUMARY_REGEX = "<div class=\"c-abstract op_tieba2_container\">.*?</div>";

	private static final String BAIDU_RESULT_REGEX = "<div*.?id=*.?>.*?</div>";

	private String urlRegex;
	private String titleRegex;
	private String sumaryRegex;
	private String resultRegex;

	private HttpUtil httpUtil;

	public BaiduParser() {
		urlRegex = BAIDU_URL_REGEX;
		titleRegex = BAIDU_TITLE_REGEX;
		sumaryRegex = BAIDU_SUMARY_REGEX;
		resultRegex = BAIDU_RESULT_REGEX;
		httpUtil = new HttpUtil();
	}

	@Override
	public List<Result> parsePage(String searchContent) {
		HtmlUtil htmlUtil = new HtmlUtil(searchContent);
		List<Result> results = new ArrayList<Result>();
		String pageContent = super.getSearchContent(searchContent, BAIDU);
		System.out.println(pageContent);
		Pattern resultPattern = Pattern.compile(resultRegex,
				Pattern.CASE_INSENSITIVE);
		Matcher resultMatcher = resultPattern.matcher(pageContent);
		// System.out.println(titleMatcher.find());
		// 在URL中得到RESULT的TITLE 和URL
		System.out.println(resultMatcher.find());
		while (resultMatcher.find()) {
			String content = resultMatcher.group(0);
			System.out.println(content);
		}// end of while

		// String pageContent = super.getSearchContent(searchContent, BAIDU);
		//
		// Pattern sumaryPattern = Pattern.compile(sumaryRegex,
		// Pattern.CASE_INSENSITIVE);
		// Matcher sumaryMatcher = sumaryPattern.matcher(pageContent);
		//
		// Pattern titlePattern = Pattern.compile(titleRegex,
		// Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
		// Matcher titleMatcher = titlePattern.matcher(pageContent);
		// // System.out.println(titleMatcher.find());
		// // 在URL中得到RESULT的TITLE 和URL
		// while (titleMatcher.find()) {
		// Result result = new Result();
		// String content = titleMatcher.group(0);
		// result.setTitle(htmlUtil.getTheReplaced(content.replaceAll("<.*?>",
		// "")));
		// result.setUrl(getBaiduUrl(content, urlRegex));
		// results.add(result);
		// }// end of while
		//
		// int i = 0;
		// LOG.error("*********************" + sumaryMatcher.groupCount());
		// while (sumaryMatcher.find()) {
		// if (results.get(i).getUrl()
		// .startsWith("http://wenku.baidu.com/search?word=")) {
		// String content = sumaryMatcher.group(0);
		// LOG.error(content);
		// results.get(i)
		// .setSumary(
		// htmlUtil.getTheReplaced(content.replaceAll(
		// "<.*?>", "")));
		// } else {
		// String content = sumaryMatcher.group(0);
		// LOG.error(content);
		// results.get(i)
		// .setSumary(
		// htmlUtil.getTheReplaced(content.replaceAll(
		// "<.*?>", "")));
		// }
		// i++;
		// }
		// // for test
		// for (int j = 0; j < results.size(); j++) {
		// LOG.debug(results.get(j).toString());
		// }
		return results;
	}

	/**
	 * 得到搜索结果URL
	 * 
	 * @param content
	 * @return
	 */
	private String getBaiduUrl(String content, String urlRegex) {
		String url = "";
		Pattern pa = Pattern.compile(urlRegex, Pattern.CASE_INSENSITIVE
				| Pattern.DOTALL);
		Matcher ma = pa.matcher(content);
		if (ma.find()) {
			url = (ma.group());
			url = url.substring(0, url.length() - 1);
			url = httpUtil.getRealUrl(url);
		}
		return url;
	}
}
