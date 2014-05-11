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

	private static final String GOOGLE_BASE_URL = "https://www.google.com.hk/search?";
	// 在百度搜索中,浏览器URL有点问题,根URL后面应该是S?而不是#
	private static final String BAIDU_BASE_URL = "http://www.baidu.com/s?";
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
		return netUtil.getPageContentT(url);
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
