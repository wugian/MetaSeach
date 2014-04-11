package com.meta.business.factory;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.meta.model.Result;
import com.meta.netutil.HttpUtil;
import com.meta.util.HexUtil;
import com.meta.util.LOG;

public class BaiduParser extends BaseParser {

	// URL正则式 href = "http://www.baidu.com/link?url=PTj9F2r9Cyk9CCgxZduVtkxsIND
	// kk-eegC3kWMKJhcZzSwPKxob3m4O2QEbXWOaaS8fgda3ZYOSb0kKcRCTEi_"
	private static final String BAIDU_URL_REGEX = "http://www.baidu.com/.*?\"";// "(http://www.baidu.com/link?url=).*?";
	// Title正则式
	private static final String BAIDU_TITLE_REGEX = "<h3 class=\"t\">.*?</h3>";
	// 摘要正则式
	private static final String BAIDU_SUMARY_REGEX = "<div class=\"c-abstract\">.*?</div>";

	private String urlRegex;
	private String titleRegex;
	private String sumaryRegex;
	static HttpUtil httpUtil;

	public BaiduParser() {
		urlRegex = BAIDU_URL_REGEX;
		titleRegex = BAIDU_TITLE_REGEX;
		sumaryRegex = BAIDU_SUMARY_REGEX;
		httpUtil = new HttpUtil();
	}

	@Override
	public List<Result> parsePage(String searchCotent) {
		List<Result> results = new ArrayList<Result>();
		String pageContent = super.getSearchContent(searchCotent, BAIDU);

		Pattern sumaryPattern = Pattern.compile(sumaryRegex,
				Pattern.CASE_INSENSITIVE);
		Matcher sumaryMatcher = sumaryPattern.matcher(pageContent);

		Pattern titlePattern = Pattern.compile(titleRegex,
				Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
		Matcher titleMatcher = titlePattern.matcher(pageContent);
		// System.out.println(titleMatcher.find());
		// 在URL中得到RESULT的TITLE 和URL
		while (titleMatcher.find()) {
			Result result = new Result();
			String content = titleMatcher.group(0);
			result.setTitle(content.replaceAll("<.*?>", ""));
			result.setUrl(getBaiduUrl(content, urlRegex));
			results.add(result);
		}// end of while

		int i = 0;
		while (sumaryMatcher.find()) {
			String content = sumaryMatcher.group(0);
			results.get(i).setSumary(content.replaceAll("<.*?>", ""));
			i++;
		}
		// for test
		for (int j = 0; j < results.size(); j++) {
			LOG.debug(results.get(j).toString());
		}
	//	System.out.println(HexUtil.compare(results.get(0).getMd5(), results
	//			.get(1).getMd5()));
		return results;
	}

	/**
	 * 得到搜索结果URL
	 * 
	 * @param content
	 * @return
	 */
	private static String getBaiduUrl(String content, String urlRegex) {
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
