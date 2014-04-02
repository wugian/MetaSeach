package com.meta.business.factory;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.meta.model.Result;
import com.meta.util.LOG;

public class BaiduParser extends BaseParser {

	// URL正则式
	private static final String BAIDU_URL_REGEX = "<a[^>]*href=(\"([^\"]*)\"|\'([^\']*)\'|([^\\s>]*))[^>]*>(.*?)</a>";
	private static final String BAIDU_TITLE_REGEX = "<h3 class=\"t\">.*?</h3>";
	// 摘要正则式
	private static final String BAIDU_SUMARY_REGEX = "<div class=\"c-abstract\">.*?</div>";
	 
	private String urlRegex;
	private String titleRegex;
	private String sumaryRegex;

	public BaiduParser() {
		urlRegex = BAIDU_URL_REGEX;
		titleRegex = BAIDU_TITLE_REGEX;
		sumaryRegex = BAIDU_SUMARY_REGEX;
	}

	@Override
	public List<Result> parsePage(String searchCotent) {
		List<Result> results = new ArrayList<Result>();
		String pageContent = super.getSearchContent(searchCotent, BAIDU);

		Pattern sumaryPattern = Pattern.compile(sumaryRegex, Pattern.CASE_INSENSITIVE);
		Matcher sumaryMatcher = sumaryPattern.matcher(pageContent);

		Pattern titlePattern = Pattern.compile(titleRegex, Pattern.CASE_INSENSITIVE
				| Pattern.DOTALL);
		Matcher titleMatcher = titlePattern.matcher(pageContent);
	//	System.out.println(titleMatcher.find());
		// 在URL中得到RESULT的TITLE 和URL
		while (titleMatcher.find()) {
			Result result = new Result();
			String content = titleMatcher.group(0);
			result.setTitle(content.replaceAll("<.*?>", ""));
			result.setUrl(getGoogleUrl(content,urlRegex));
			results.add(result);
		}// end of while

		int i = 0;
		while (sumaryMatcher.find()) {
			String content = sumaryMatcher.group(0);
			results.get(i).setSumary(content.replaceAll("<.*?>", ""));
			i++;
		}

		for (int j = 0; j < results.size(); j++) {
			LOG.debug(results.get(j).toString());
		}
		return results;
	}

	/**
	 * 得到URL
	 * 
	 * @param content
	 * @return
	 */
	private static String getGoogleUrl(String content,String urlRegex) {
		String url = "";
		Pattern pa = Pattern.compile(urlRegex, Pattern.CASE_INSENSITIVE
				| Pattern.DOTALL);
		Matcher ma = pa.matcher(content);
		if (ma.find()) {
			url = (ma.group());
			String subUrl = content.replace("<a href=\"/url?q=", "");
			subUrl = subUrl.replace("<a href=\"/url?q=", "");
			int index = subUrl.indexOf("&amp;");
			url = subUrl.substring(0, index).replace("<h3 class=\"r\">", "");
		}
		return url;
	}
}
