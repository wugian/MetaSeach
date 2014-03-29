package aa;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlDivision;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;

public class Test {
	static String testUrl = "http://www.baidu.com/#wd=gdp&rsv_bp=0&tn=baidu&rsv_spt=3&ie=utf-8&rsv_sug3=2&rsv_sug4=194&rsv_sug1=2&oq=gd&rsv_sug2=0&f=3&rsp=0&inputT=6";

	public static String getPageAsXml(String url) {
		final WebClient webClient = new WebClient(BrowserVersion.FIREFOX_24);
		HtmlPage page = null;
		try {
			page = webClient.getPage(url);
		} catch (FailingHttpStatusCodeException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String pageAsXml = page.asXml();
		webClient.closeAllWindows();
		return pageAsXml;
	}

	private static void go() throws Exception {
		/* turn off annoying htmlunit warnings */
		java.util.logging.Logger.getLogger("com.gargoylesoftware").setLevel(
				java.util.logging.Level.OFF);

		HtmlPage nextPage;
		String url = "http://media.ethics.ga.gov/search/Campaign/Campaign_Name.aspx?NameID=5751&FilerID=C2009000085&Type=candidate";

		final WebClient webclient = new WebClient(BrowserVersion.CHROME);
		final HtmlPage page = webclient.getPage(url);

		System.out.println("PULLING LINKS:");

		List<HtmlAnchor> articles = (List<HtmlAnchor>) page
				.getByXPath("//a[@class='lblentrylink']");
		// List<HtmlAnchor> articles = (List<HtmlAnchor>)
		// page.getByXPath("//div[@class='hform1']/a[@class='lblentrylink']");

		for (int x = 0; x < articles.size(); x++) {
			System.out.println("Clicking " + articles.get(x).asText());
			// nextPage = articles.get(x).click();
			// System.out.println(nextPage.getBody());
		}
	}

	static void testa() {
		final WebClient webClient = new WebClient();
		webClient.getOptions().setCssEnabled(false);
		webClient.getOptions().setJavaScriptEnabled(false);
		HtmlPage page = null;
		try {
			page = webClient.getPage(testUrl);// http://www.yanyulin.info");
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
		HtmlPage page2 = page.getPage();
		if (page2 != null) {
			System.out.println(page2.asXml());
		} else {
			System.out.println("page 2 is null");
		}
		webClient.closeAllWindows();
	}

	static void testb() {
		final WebClient webClient = new WebClient(BrowserVersion.CHROME);
		webClient.getOptions().setRedirectEnabled(true);
		webClient.getOptions().setThrowExceptionOnScriptError(false);
		webClient.getOptions().setCssEnabled(false);
		webClient.getOptions().setUseInsecureSSL(true);
		webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
		webClient.getCookieManager().setCookiesEnabled(true);
		webClient.setAjaxController(new NicelyResynchronizingAjaxController());
		HtmlPage page;
		try {
			page = (HtmlPage) webClient
					.getPage("http://www.baidu.com/#wd=gdp&rsv_bp=0&tn=baidu&rsv_spt=3&ie=utf-8&rsv_sug3=2&rsv_sug4=194&rsv_sug1=2&oq=gd&rsv_sug2=0&f=3&rsp=0&inputT=6");
			java.util.List<HtmlAnchor> achList = page.getAnchors();
			for (HtmlAnchor ach : achList) {
				// System.out.println(ach.getHrefAttribute());
			}
			// 从烟雨林个个博客上获取标签hed的内容
			// HtmlDivision div = (HtmlDivision) page.getElementById("title");
			// System.out.println(div.asXml());
			// 同样可以打印出hed的内容,//div中//表示搜索整个文档中的div,并将这些div
			// 放入list中，然后获取第一个div
			final HtmlDivision div = (HtmlDivision) page.getByXPath("//div")
					.get(0);
			// System.out.println(div.asXml());
			webClient.closeAllWindows();

			// System.out.println("Title=" + page.asText());
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

	}

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		// System.out.println(getPageAsXml(testUrl));
		// testa();testb();
		testCommit();

	}

	static void testCommit() {
		final WebClient webClient = new WebClient();

		String searchUrl = "http://www.baidu.com/";
		// Get the first page
		HtmlPage page1 = null;
		try {
			page1 = webClient.getPage(searchUrl);
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

		// Get the form that we are dealing with and within that form,
		// find the submit button and the field that we want to change.
		final HtmlForm form = page1.getFormByName("f");

		final HtmlSubmitInput button = form.getInputByValue("百度一下");
		final HtmlTextInput textField = form.getInputByName("wd");

		// Change the value of the text field
		textField.setValueAttribute("wugian");

		// Now submit the form by clicking the button and get back the second
		// page.
		try {
			final HtmlPage page2 = button.click();
			System.out.println(page2.getUrl());
			System.out.println(page2.asText());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		webClient.closeAllWindows();
	}
}
