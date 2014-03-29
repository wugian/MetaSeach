package aa;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SaveURL {

	public static String savepath_SaveURL = "d:\\";// 默认路径为D盘根目录
	static String title = null;
	static String link = null;
	static String localFile = null;// 保存为本地文件后的路径
	static String res = null;

	public static List<String> getLink(String s) {
		String regex;
		List<String> list = new ArrayList<String>();
		regex = "<a[^>]*href=(\"([^\"]*)\"|\'([^\']*)\'|([^\\s>]*))[^>]*>(.*?)</a>";
		Pattern pa = Pattern.compile(regex, Pattern.DOTALL);
		Matcher ma = pa.matcher(s);
		while (ma.find()) {
			list.add(ma.group());
		}
		return list;
	}

	public static void go() {
		// String url= "http://www.baidu.com/s?wd=accenture&rn=10";
		String url = "https://www.google.com.hk/search?num=30&newwindow=1&safe=strict&hl=en&q=hello&oq=hello&gs_l=serp.12...0.0.0.173743.0.0.0.0.0.0.0.0..0.0....0...1c..37.serp..0.0.0.mrtm0N2Ikck";
		String content = getPage(url);// 得到url所对应的网页的内容
		System.out.println("*****");
		System.out.println(getTitle(content));

		List<String> links = getLink(content);
		for (int i = 0; i < links.size(); i++) {
			System.out.println(links.get(i));
		}
		System.out.println("*****");
		// 对应百度 <table border="0" cellpadding="0" cellspacing="0">等的正则表达式
		// 为什么快照的链接没有被下载下来？

		// String reg =
		// " <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\">.*?"+
		// " <a.*? href=\"(.*?)\".*?>(.*?) </a>.*? </table>";

		// Google对应的正则式
		// <a[^>]*href=(\"([^\"]*)\"|\'([^\']*)\'|([^\\s>]*))[^>]*>(.*?)</a>
		// String reg =
		// "<a onclick=\"return c[^>]+href=\"(.+?)\"[^>]*>(.*?)</a>";
		String reg = "<div class=g[^>]*><a href=\"(.+?)\"[^>]*>(.+?)</a>";
		// String reg = "<h3 class=r>"
		// +
		// "<a[^>]*href=(\"([^\"]*)\"|\'([^\']*)\'|([^\\s>]*))[^>]*>(.*?)</a>";//
		// "<a href=\"(.*?)\".*?>(.*?)</a></h3>";
		Pattern p = Pattern.compile(reg, Pattern.CASE_INSENSITIVE
				| Pattern.DOTALL);
		Matcher m = p.matcher(content);

		Pattern pa = Pattern.compile(reg, Pattern.DOTALL);
		Matcher ma = pa.matcher(content);

		while (ma.find()) {
			System.out.println(ma.group());
		}

		int i = 1;

		while (m.find()) {
			title = m.group(2).replaceAll(" <.*?>", "");// 正则表达式
			link = m.group(1);
			System.out
					.println("----------------------------------------------");
			System.out.println("第" + i + "个标题：" + title);
			System.out.println("第" + i + "个链接：" + link);
			i++;
		}// end of while

	}

	public static String getTitle(String s) {
		String regex;
		String title = "";
		List<String> list = new ArrayList<String>();
		regex = "<title>.*?</title>";
		Pattern pa = Pattern.compile(regex, Pattern.CANON_EQ);
		Matcher ma = pa.matcher(s);
		while (ma.find()) {
			list.add(ma.group());
		}
		for (int i = 0; i < list.size(); i++) {
			title = title + list.get(i);
		}
		return title.replaceAll("<.*?>", "");
	}

	public static String getPage(String page) {
		try {
			URL url = new URL(page);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			// 以下是修正Server returned HTTP response code: 403 for URL的代码
			// 通常是因为服务器的安全设置不接受Java程序作为客户端访问，解决方案是设置客户端的User Agent
			con.setRequestProperty("User-Agent",
					"Mozilla/4.0 (compatible; MSIE 5.0;Windows NT; DigExt)");
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					con.getInputStream()));
			StringBuilder b = new StringBuilder();
			String line;
			while ((line = reader.readLine()) != null) {
				b.append(line);
				b.append("\r\n");
			}
			System.out.println(b.toString());
			return b.toString();
		} catch (FileNotFoundException ex) {
			System.out.println("NOT FOUND:" + page);
			return null;
		} catch (ConnectException ex) {
			System.out.println("Timeout:" + page);
			return null;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public static void main(String[] args) {
		go();
	}

}