package com.meta.business;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import com.meta.business.factory.BaiduFactory;
import com.meta.business.factory.GoogleFactory;
import com.meta.business.intertace.IParser;
import com.meta.model.Result;

public class Search {
	public List<Result> getResult(String search) {
		IParser baiduParser = new BaiduFactory().produce();
		ArrayList<Result> br = null;

		br = (ArrayList<Result>) baiduParser.parsePage(search);

		IParser googleParser = new GoogleFactory().produce();
		ArrayList<Result> gr = null;

		gr = (ArrayList<Result>) googleParser.parsePage(search);

		DuplicateRemoval dr = new DuplicateRemoval();
		dr.insert(gr);
		dr.insert(br);
		dr.show();
		return dr.getResults();
	}
}
