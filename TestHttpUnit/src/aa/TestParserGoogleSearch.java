package aa;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.beans.FilterBean;
import org.htmlparser.filters.AndFilter;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.filters.NodeClassFilter;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

/**
 * 
 * @author Michael
 * 
 */
public class TestParserGoogleSearch {
	public static void getUrlsByGoogle(String keywords) throws ParserException,
			UnsupportedEncodingException {// google
		TagNameFilter filter0 = new TagNameFilter();
		filter0.setName("A");
		HasAttributeFilter filter1 = new HasAttributeFilter();
		filter1.setAttributeName("class");
		filter1.setAttributeValue("l");
		NodeFilter[] array0 = new NodeFilter[2];
		array0[0] = filter0;
		array0[1] = filter1;
		AndFilter filter2 = new AndFilter();
		filter2.setPredicates(array0);
		NodeFilter[] array1 = new NodeFilter[1];
		array1[0] = filter2;
		FilterBean bean = new FilterBean();
		bean.setFilters(array1);
		bean.setURL("http://www.google.com/search?complete=1&amp;start=" + 1
				+ "&amp;q=" + URLEncoder.encode(keywords, "UTF-8"));
		NodeList nodes = bean.getNodes();
		List<String> result = new ArrayList<String>();
		for (int i = 0; i < nodes.size(); i++) {
			LinkTag link = (LinkTag) nodes.elementAt(i);
			System.out.println(link.getLink());
			result.add(link.getLink());
		}
		// result;
	}

	public static void main(String[] args) throws UnsupportedEncodingException {
		try {
			getUrlsByGoogle("angel");
		} catch (ParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
