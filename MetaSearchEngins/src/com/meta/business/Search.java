package com.meta.business;

import java.util.ArrayList;

import com.meta.business.factory.CopyOfBaiduFactory;
import com.meta.business.factory.GoogleFactory;
import com.meta.business.intertace.IParser;
import com.meta.model.Result;

public class Search {
	public DuplicateRemoval getResult(String search) {
		IParser baiduParser = new CopyOfBaiduFactory().produce();
		ArrayList<Result> br = null;
		br = (ArrayList<Result>) baiduParser.parsePage(search);
		IParser googleParser = new GoogleFactory().produce();
		ArrayList<Result> gr = null;
		gr = (ArrayList<Result>) googleParser.parsePage(search);

		DuplicateRemoval dr = new DuplicateRemoval();
		dr.insert(gr);
		dr.insert(br);
		// dr.show();
		return dr;
	}
}
