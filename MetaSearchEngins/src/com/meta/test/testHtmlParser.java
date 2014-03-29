package com.meta.test;

import java.io.IOException;
import java.net.MalformedURLException;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.ScriptResult;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class testHtmlParser {

	public void testHomePage() throws FailingHttpStatusCodeException,
			MalformedURLException, IOException {
		final WebClient webClient = new WebClient();
		final HtmlPage startPage = webClient.getPage("http://www.baidu.com");
		System.out.println("标题:" + startPage.getTitleText());
	}

	public void testHomePage_Firefox() throws Exception {
		String TargetURL = "http://person.sac.net.cn/pages/registration/sac-publicity.html";
		// String TargetURL="http://www.baidu.com";
		// 模拟一个浏览器
		final WebClient webClient = new WebClient(BrowserVersion.FIREFOX_3_6);
		// final WebClient webClient=new
		// WebClient(BrowserVersion.FIREFOX_10,"http://myproxyserver",8000);
		// //使用代理
		final WebClient webClient2 = new WebClient(
				BrowserVersion.INTERNET_EXPLORER_7);
		// 设置webClient的相关参数
//		webClient.getOptions().setJavaScriptEnabled(true);
//		webClient.getOptions().setActiveXNative(false);
//		webClient.getOptions().setCssEnabled(false);
//		webClient.getOptions().setThrowExceptionOnScriptError(false);
//		webClient.setAjaxController(new NicelyResynchronizingAjaxController());
		// 模拟浏览器打开一个目标网址
		final HtmlPage page = webClient.getPage(TargetURL);
		System.out.println(page.asText());
		System.out.println(page.getUrl());
		// page.executeJavaScript("javascript:searchFinishPerson('6655',2);");
		System.out.println("------------------");
		ScriptResult sr = page
				.executeJavaScript("javascript:searchFinishPerson('6655',2);");
		HtmlPage newPage = (HtmlPage) sr.getNewPage();
		System.out.println(newPage.asText());
		System.out.println(newPage.getUrl());
	}

	public static void main(String[] args) {
		testHtmlParser test = new testHtmlParser();
		try {
			test.testHomePage_Firefox();
		}
		// catch(FailingHttpStatusCodeException e){
		// e.printStackTrace();
		// }catch(MalformedURLException e){
		// e.printStackTrace();
		// }catch(IOException e){
		// e.printStackTrace();
		// }
		catch (Exception e) {
			e.printStackTrace();
		}

	}
}