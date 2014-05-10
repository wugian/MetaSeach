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

		try {
			br = (ArrayList<Result>) baiduParser.parsePage(URLEncoder.encode(
					search, "utf-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		IParser googleParser = new GoogleFactory().produce();
		ArrayList<Result> gr = null;

		try {
			gr = (ArrayList<Result>) googleParser.parsePage(URLEncoder.encode(
					search, "utf-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		DuplicateRemoval dr = new DuplicateRemoval();
		dr.insert(gr);
		dr.insert(br);
		dr.show();
		return dr.getResults();
	}
}
