package com.meta.business.factory;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import com.meta.business.intertace.IParser;
import com.meta.model.Result;
import com.meta.netutil.HttpUtil;

public class BaseParser implements IParser {
	public static final int GOOGLE = 0x00;
	public static final int YAHOO = 0x01;
	public static final int BAIDU = 0x02;

	// lr=lang_zh-CN只搜索简体中文网页
	// lr=lang_zh-TW只搜索繁体中文网页
	// lr=lang_zh-CN|lang_zh-TW搜索所有中文网页
	// lr=lang_en只搜索英文网页
	// ct 语言限制。0-所有语言，1-简体中文网页，2-繁体中文网页；其它不确定或者无效或。默认值为0.ie=utf-8&lr=lang_zh-CN
	private static final String GOOGLE_BASE_URL = "https://www.google.com.hk/search?num=30";
	// 在百度搜索中,浏览器URL有点问题,根URL后面应该是S?而不是#
	private static final String BAIDU_BASE_URL = "http://www.baidu.com/s?ie=utf-8&rn=30";
	// + "newwindow=1&" + "safe=strict&" + "espv=210&es_sm=93&" +
	// "q=java+%E8%8E%B7%E5%8F%96google%E6%90%9C%E7%B4%A2%E7%BB%93%E6%9E%9C&"
	// +
	// "oq=java+&" +
	// "gs_l=serp.3.0.35i39l2j0l8.986529.988072.0.989910.2.2.0.0.0.0.281.419.0j1j1.2.0....0...1c..38.serp..0.2.414.xcR79jmSmlE";

	HttpUtil netUtil;

	public BaseParser() {
		netUtil = new HttpUtil();
	}

	/**
	 * get the basic result
	 * 
	 * @param pageContent
	 * @return
	 */
	public List<Result> parsePage(String searchCotent) {
		return null;
	}

	/**
	 * get the search web page content
	 * 
	 * @param seachContent
	 * @param type
	 * @return the string of page
	 */
	public String getSearchContent(String seachContent, int type) {
		String url = this.searchUrlConstruct(seachContent, type);
		return netUtil.getPageContent(url);
	}

	/**
	 * not necessary convert the format of the search content
	 * 
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public String formatConvert(String seachContent, String charset)
			throws UnsupportedEncodingException {
		return new String(seachContent.getBytes("UTF-8"), charset);
	}

	/**
	 * get the basic search url,baidu,yahoo,google....
	 * 
	 * @return
	 */
	public String searchUrlConstruct(String seachContent, int type) {

		StringBuffer url = new StringBuffer();
		switch (type) {
		case GOOGLE:
			url.append(GOOGLE_BASE_URL);
			url.append("&q=");
			break;
		case BAIDU:
			url.append(BAIDU_BASE_URL);
			url.append("&wd=");
			break;
		default:
			break;
		}
		try {
			url.append(URLEncoder.encode(seachContent, "utf-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return url.toString();
	}

}
