package com.meta.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.meta.model.Result;

public class Parse2ResultUtil {

	/**
	 * 将pageContext(特指搜索引擎结果页面的内容)转化成Result列表返回
	 * result详见 com.meta.model.Result
	 * @param pageContent 页面结果内容
	 * @param titleRegex  结果的页面标题正则式
	 * @param urlRegex	  结果的URL正则式
	 * @param sumaryRegex 结果的摘要正则式
	 * @return
	 */
	public List<Result> parsePage(String pageContent,String titleRegex,String urlRegex,String sumaryRegex ) {
		List<Result> results = new ArrayList<Result>();
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
			result.setTitle(content.replaceAll("<.*?>", ""));
			result.setUrl(getGoogleUrl(content));
			results.add(result);
		}// end of while

		int i = 0;
		while (mm.find()) {
			String content = mm.group(0);
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
	private static String getGoogleUrl(String content) {
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
