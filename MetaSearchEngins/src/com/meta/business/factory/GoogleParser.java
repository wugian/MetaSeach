package com.meta.business.factory;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.meta.model.Result;
import com.meta.util.HtmlUtil;
import com.meta.util.LOG;

/**
 * 解析GOOGLE搜索结果类，继承BASEPARSER(IPAESER),重写PARSEPAGE();
 * 
 * @author tezuka-pc
 * 
 */
public class GoogleParser extends BaseParser {
	// 摘要正则式
	String sumaryReg = "<span [^>]*class=\"st\"[^>]*>(<span[^>]*>(<span[^>]*>.*?</span>|.)*?</span>|.)*?</span>";// "<span class=\"st\">.*?</span>";

	// URL正则式包括标题
	String reg = "<h3 class=\"r\">.*?</h3>";

	static String urlRex1 = "<a[^>]*href=(\"([^\"]*)\"|\'([^\']*)\'|([^\\s>]*))[^>]*>(.*?)</a>";

	@Override
	public List<Result> parsePage(String searchContent) {
		HtmlUtil htmlUtil = new HtmlUtil(searchContent);
		List<Result> results = new ArrayList<Result>();
		String pageContent = super.getSearchContent(searchContent, GOOGLE);
		// 摘要正则式
		String sumaryReg = "<span class=\"st\">.*?</span>";
		Pattern pp = Pattern.compile(sumaryReg, Pattern.CASE_INSENSITIVE);
		Matcher mm = pp.matcher(pageContent);
		// URL正则式
		String reg = "<h3 class=\"r\">.*?</h3>";
		Pattern p = Pattern.compile(reg, Pattern.CASE_INSENSITIVE
				| Pattern.DOTALL);
		Matcher m = p.matcher(pageContent);
		// 在URL中得到RESULT的TITLE 和URL
		while (m.find()) {
			Result result = new Result();
			String content = m.group(0);
			result.setTitle(htmlUtil.getTheReplaced(content.replaceAll("<.*?>",
					"")));
			result.setGoogle(true);
			result.setUrl(getGoogleUrl(content));
			results.add(result);
		}// end of while
		int i = 0;
		int size = results.size();
		while (mm.find()) {

			try {
				Result r = results.get(i);
				double c = (double) (size - i) / (double) size;// 保留小数
				r.setWeight(c * GOOGLE_WEIGHT);
				String content = mm.group(0);
				r.setSumary(htmlUtil.getTheReplaced(content.replaceAll("<.*?>",
						"")));
			} catch (Exception e) {
			}
			i++;
		}
		LOG.error("google count:" + i);

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
	private static String getGoogleUrl(String content) {
		String url = "";
		Pattern pa = Pattern.compile(urlRex1, Pattern.CASE_INSENSITIVE
				| Pattern.DOTALL);
		Matcher ma = pa.matcher(content);
		if (ma.find()) {
			url = ma.group();
			// <a href="http://www.books.com.tw/products/0010509577"
			String urlRex = "<a href=\".*?\"";
			Pattern pCur = Pattern.compile(urlRex, Pattern.CASE_INSENSITIVE
					| Pattern.DOTALL);
			Matcher mCur = pCur.matcher(content);
			if (mCur.find()) {
				int s = url.indexOf("\"");
				int e = url.indexOf("\"", s + 1);
				url = url.substring(s + 1, e);
			}
		}
		return url;
	}

	/**
	 * 得到URL
	 * 
	 * @param content
	 * @return
	 */
	private static String getGoogleUrl1(String content) {
		String url = "";
		String urlRex = "<a[^>]*href=(\"([^\"]*)\"|\'([^\']*)\'|([^\\s>]*))[^>]*>(.*?)</a>";
		Pattern pa = Pattern.compile(urlRex, Pattern.CASE_INSENSITIVE
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
