package com.meta.test;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.htmlparser.beans.StringBean;
import org.htmlparser.util.ParserException;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.meta.netutil.NetUtil;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String testUrl = "https://www.google.com.hk/search?newwindow=1&safe=strict&espv=210&es_sm=93&q=java+++%E8%AE%BE%E8%AE%A1%E6%A8%A1%E5%BC%8F&oq=java+++%E8%AE%BE%E8%AE%A1%E6%A8%A1%E5%BC%8F&gs_l=serp.12..0i12i2l10.22208.22208.0.24730.1.1.0.0.0.0.143.143.0j1.1.0....0...1c.1.38.serp..0.1.136.P21GGt3DHms";
		NetUtil netUtil = new NetUtil();
		String content = netUtil.getPageContentT(testUrl);
		System.out.println("content:" + content);
		String reg = " <li class=\"g\"> .* </li>";
		Pattern p = Pattern.compile(reg, Pattern.CASE_INSENSITIVE
				| Pattern.DOTALL);
		Matcher m = p.matcher(content);

		int i = 1;

		System.out.println(m.find());
		while (m.find()) {
			String title = m.group(2).replaceAll(" <.*?>", "");// 正则表达式
			String link = m.group(1);

			System.out
					.println("----------------------------------------------");
			System.out.println("第" + i + "个标题：" + title);
			System.out.println("第" + i + "个链接：" + link);

			i++;
		}// end of while

	}

	// System.out.println(System.getProperty("file.encoding"));
	// HttpUtil.getCotentFromUrl(testUrl);

	public static String getText(String url) throws ParserException {
		StringBean sb = new StringBean();

		// ���ò���Ҫ�õ�ҳ������������Ϣ
		sb.setLinks(true);
		// ���ý�����Ͽո������ո������
		sb.setReplaceNonBreakingSpaces(true);
		// ���ý�һ���пո���һ����һ�ո������
		sb.setCollapse(true);
		// ����Ҫ������URL
		sb.setURL(url);
		// ���ؽ��������ҳ���ı���Ϣ
		return sb.getStrings();
	}

	static void test() {
		String url = "http://www.baidu.com/s?tn=baiduhome_pg&ie=utf-8&bs=WebClient+%E6%B2%A1%E6%9C%89getoption&f=8&rsv_bp=1&rsv_spt=1&wd=webClient.getOptions%28%29.setJavaScriptEnabled%28true%29%3B&rsv_sug3=2&rsv_sug4=111&rsv_n=2&rsv_sug2=0&inputT=4";
		// ģ��һ�������
		WebClient webClient = new WebClient();
		// ����webClient����ز���
		webClient.setAjaxController(new NicelyResynchronizingAjaxController());
		// webClient.getOptions().setJavaScriptEnabled(true);
		// webClient.getOptions().setCssEnabled(false);
		// webClient.getOptions().setTimeout(35000);
		// webClient.getOptions().setThrowExceptionOnScriptError(false);

		// ģ���������һ��Ŀ����ַ
		HtmlPage rootPage = (HtmlPage) webClient.getCurrentWindow()
				.getEnclosedPage();
		try {
			rootPage = webClient.getPage(url);
		} catch (FailingHttpStatusCodeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(rootPage.getUrl());

		// System.out.println(rootPage.getHead().asXml());
		// System.out.println(rootPage.getBody().asXml());
		System.out
				.println("==================== all info ====================");
		System.out.println(rootPage.asXml());
	}
}
