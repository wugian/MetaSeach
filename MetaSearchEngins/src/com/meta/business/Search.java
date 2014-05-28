package com.meta.business;

import java.util.List;

import com.meta.business.factory.CopyOfBaiduFactory;
import com.meta.business.factory.GoogleFactory;
import com.meta.business.intertace.IParser;
import com.meta.model.Result;

public class Search {
	public DuplicateRemoval getResult(String search) {
		DuplicateRemoval dr = new DuplicateRemoval();
		CalculateWeiht cw = new CalculateWeiht();
		ResultSort rs = new ResultSort();

		IParser baiduParser = new CopyOfBaiduFactory().produce();
		List<Result> br = null;
		br = baiduParser.parsePage(search);
		IParser googleParser = new GoogleFactory().produce();
		List<Result> gr = null;
		gr = googleParser.parsePage(search);

		List<Result> grw = cw.calculateWeight(gr);
		List<Result> brw = cw.calculateWeight(br);

		dr.insert(grw);// 先加google后加百度
		dr.insert(brw);// 因为设计缺陷

		List<Result> all = rs.sort(dr.getResults());
		dr.setResults(all);
		return dr;
	}
}
