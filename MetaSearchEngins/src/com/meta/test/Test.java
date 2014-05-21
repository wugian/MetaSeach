package com.meta.test;

import java.io.IOException;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.htmlparser.beans.StringBean;
import org.htmlparser.util.ParserException;
import org.lionsoul.jcseg.ASegment;
import org.lionsoul.jcseg.core.ADictionary;
import org.lionsoul.jcseg.core.DictionaryFactory;
import org.lionsoul.jcseg.core.IWord;
import org.lionsoul.jcseg.core.JcsegException;
import org.lionsoul.jcseg.core.JcsegTaskConfig;
import org.lionsoul.jcseg.core.SegmentFactory;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.meta.business.factory.BaiduFactory;
import com.meta.business.factory.CopyOfBaiduFactory;
import com.meta.business.factory.CopyOfBaiduParser;
import com.meta.business.intertace.IParser;
import com.meta.model.Result;
import com.meta.netutil.HttpUtil;
import com.meta.util.HtmlUtil;

public class Test {

	/**
	 * @param args
	 * @throws UnsupportedEncodingException
	 */
	public static void main(String[] args) throws UnsupportedEncodingException {

		IParser baiduParser = new CopyOfBaiduFactory().produce();
		ArrayList<Result> br = null;
		br = (ArrayList<Result>) baiduParser.parsePage("java 设计模式");

		// String s = "this is Java i love this java";
		// System.out.println(s);
		// /String result = HtmlUtil.getResult(s);
		// System.out.println(result);
		// String cnTest = "我爱中国一个人的生活，i love you";
		//
		// String result = HtmlUtil.getTheReplaced(cnTest, "生活中国一个");
		// System.out.println(result);
		// String testStr =
		// "3fXOhYbi_v5KhB1OSL_fPAhd2D84iTzLNInmYI41oEXd7vNvZQ6tBwKCmHbO4Qnzf842tXKm6xXmQzecZ-6pVK";
		// byte[] asBytes = Base64
		// .decode("3fXOhYbi_v5KhB1OSL_fPAhd2D84iTzLNInmYI41oEXd7vNvZQ6tBwKCmHbO4Qnzf842tXKm6xXmQzecZ-6pVK");
		// if (asBytes.length != 0)
		// System.out.println(new String(asBytes, "utf-8")); // 输出为: some
		// else
		// System.out.println("null");

		// BASE64Decoder decoder = new BASE64Decoder();
		// try {
		// byte[] bytes = decoder.decodeBuffer(testStr);
		// System.out.println(new String(bytes, "utf-8"));
		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// String tests = "_ylt=A0LEV1vEfDtTFHsA5QVXNyoA";
		// String rvs = "RV=2/RE=1396436293/RO=10/"
		// +
		// "RU=http%3a%2f%2f98.139.21.31%2fsearch%2fsrpcache%3fei%3dUTF-8%26p%3djava%2b%25E8%25AE%25BE%25E8%25AE%25A1%25E6%25A8%25A1%25E5%25BC%258F%26fr%3dyfp-t-234%26u%3dhttp%3a%2f%2fcc.bingj.com%2fcache.aspx%3fq%3djava%2b%25e8%25ae%25be%25e8%25ae%25a1%25e6%25a8%25a1%25e5%25bc%258f%26d%3d4753005382140894%26mkt%3den-US%26setlang%3den-US%26w%3d2wAl4Yv9HZpxau_Sslwu-BzFc-oEhnaH%26icp%3d1%26.intl%3dus%26sig%3dLdxhGrPogqR4XhoXFXTfgQ--/RK=0/RS=mv7F.gWAi89qWGDyXNnGl7rzaeI-";

		// IParser baiduParser = new BaiduFactory().produce();
		// ArrayList<Result> br = (ArrayList<Result>) baiduParser
		// .parsePage(URLEncoder.encode("java 设计模式", "utf-8"));
		//
		// IParser googleParser = new GoogleFactory().produce();
		// ArrayList<Result> gr = (ArrayList<Result>) googleParser
		// .parsePage(URLEncoder.encode("java 设计模式", "utf-8"));
		//
		// DuplicateRemoval dr = new DuplicateRemoval();
		// dr.insert(gr);
		// dr.insert(br);
		// dr.show();
	}

	void testGoogle() {
		String testUrl = "https://www.google.com.hk/search?newwindow=1&safe=strict&espv=210&es_sm=93&q=java+++%E8%AE%BE%E8%AE%A1%E6%A8%A1%E5%BC%8F&oq=java+++%E8%AE%BE%E8%AE%A1%E6%A8%A1%E5%BC%8F&gs_l=serp.12..0i12i2l10.22208.22208.0.24730.1.1.0.0.0.0.143.143.0j1.1.0....0...1c.1.38.serp..0.1.136.P21GGt3DHms";
		HttpUtil netUtil = new HttpUtil();
		String content = netUtil.getPageContent(testUrl);
		System.out.println("content:" + content);
		String reg = "<h3 class=\"r\">.*?</h3>";
		Pattern p = Pattern.compile(reg, Pattern.CASE_INSENSITIVE
				| Pattern.DOTALL);
		Matcher m = p.matcher(content);

		int i = 0;
		while (m.find()) {
			// String link = m.group(1);
			i++;
			String title = m.group(0);
			// String contetn = m.group(1);
			System.out
					.println("----------------------------------------------");
			System.out.println(title.replace("<h3.*?>", ""));
			System.out
					.println("第" + i + "个链接：" + title.replaceAll("<.*?>", ""));
			// System.out.println("第" + i + "个内容：" + contetn);
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
