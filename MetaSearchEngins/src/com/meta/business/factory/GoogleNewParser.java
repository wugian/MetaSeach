package com.meta.business.factory;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.meta.model.Result;
import com.meta.util.HtmlUtil;

public class GoogleNewParser extends BaseParser {
	private static final String ALL_REGEX = "<li class=\"g\">.*?</li>";
	// 摘要正则式
	String SUMARY_REGEX_UP = "<div class=\"s\">.*?</div>";
	// 摘要正则式,google出现了span嵌套\
	// <span
	// [^>]*class=\"st\'[^>]*>(<span[^>]*>(<span[^>]*>.*?</span>|.)*?</span>|.)*?</span>
	// <span
	// [^>]*class=\"st\"[^>]*>(<span[^>]*>(<span[^>]*>.*?</span>|.)*?</span>|.)*?</span>
	String SUMARY_REGEX = "<span [^>]*class=\"st\"[^>]*>(<span[^>]*>(<span[^>]*>.*?</span>|.)*?</span>|.)*?</span>";// "<span class=\"st\">(<span.*?>.*?</span>)?</span>";
	// "<span class=\"st\">[(<span .*?>.*?</span>)]?</span>";
	// URL
	private static final String URL_REGEX = "<a[^>]*href=(\"([^\"]*)\"|\'([^\']*)\'|([^\\s>]*))[^>]*>(.*?)</a>";
	// URL正则式包括标题
	String REGEX_TITLE_URL = "<h3 class=\"r\">.*?</h3>";
	String urlRex = "<a[^>]*href=(\"([^\"]*)\"|\'([^\']*)\'|([^\\s>]*))[^>]*>(.*?)</a>";

	@Override
	public List<Result> parsePage(String searchContent) {
		HtmlUtil htmlUtil = new HtmlUtil(searchContent);
		List<Result> results = new ArrayList<Result>();
		String pageContent = super.getSearchContent(searchContent, GOOGLE);
		Pattern pDiv = Pattern.compile(ALL_REGEX, Pattern.CASE_INSENSITIVE);
		Matcher mDiv = pDiv.matcher(pageContent);
		while (mDiv.find()) {
			String liContent = mDiv.group();
			// URL title正则式
			Pattern pUT = Pattern.compile(REGEX_TITLE_URL,
					Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
			Matcher mUT = pUT.matcher(liContent);

			Result result = new Result();
			if (mUT.find()) {
				String urlTitle = mUT.group();
				result.setTitle(htmlUtil.getTheReplaced(urlTitle.replaceAll(
						"<.*?>", "")));
				result.setUrl(getGoogleUrl(urlTitle));
			}
			results.add(result);
		}

		int i = 0;
		Pattern pSumary = Pattern.compile(SUMARY_REGEX,
				Pattern.CASE_INSENSITIVE);
		Matcher mSumary = pSumary.matcher(pageContent);
		// System.out.println(pageContent);
		while (mSumary.find()) {
			String sumaryContent = mSumary.group();
			System.out.println("***********sumaryContent*****************下面:"
					+ i);
			System.out.println(sumaryContent);
			results.get(i).setSumary(
					htmlUtil.getTheReplaced(sumaryContent.replaceAll("<.*?>",
							"")));
			i++;
		}

		for (Result s : results) {
			System.out.println(s.toString());
		}
		return null;
	}

	/**
	 * 得到URL
	 * 
	 * @param content
	 * @return
	 */
	private static String getGoogleUrl(String content) {
		String url = "";
		Pattern pa = Pattern.compile(URL_REGEX, Pattern.CASE_INSENSITIVE
				| Pattern.DOTALL);
		Matcher ma = pa.matcher(content);
		if (ma.find()) {
			url = ma.group();
			// <a href="http://www.books.com.tw/products/0010509577"
			String urlRex = "<a href=\".*?\"";
			Pattern pCur = Pattern.compile(URL_REGEX, Pattern.CASE_INSENSITIVE
					| Pattern.DOTALL);
			Matcher mCur = pa.matcher(content);
			if (mCur.find()) {
				int s = url.indexOf("\"");
				int e = url.indexOf("\"", s + 1);
				url = url.substring(s + 1, e);
			}
		}
		return url;
	}
}
