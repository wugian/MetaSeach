package com.meta.business;

import java.util.List;

import com.meta.model.Result;

/**
 * 计算权值，整合到解析里面去了
 * 
 * @author tezuka-pc
 * 
 */
public class CalculateWeiht {

	private static final double GOOGLE_WEIGHT = 1.03;
	private static final double BAIDU_WEIGHT = 0.98;

	/**
	 * 可以用多线程否？？
	 * 
	 * @param all
	 * @return
	 */
	public List<Result> calculateWeight(List<Result> all) {
		List<Result> locationWeiht = calLocationWeight(all);
		System.out.println(locationWeiht.size());
		List<Result> relWeiht = calRelationWeight(locationWeiht);
		return relWeiht;
	}

	private List<Result> calLocationWeight(List<Result> all) {
		int size = all.size();
		for (int i = 1; i < size + 1; i++) {
			Result r = all.get(i - 1);
			double c = (double) (size - i + 1) / (double) size;// 保留小数
			r.setWeight(c);
		}
		return all;
	}

	private List<Result> calRelationWeight(List<Result> all) {
		List<Result> result = all;
		return result;
	}
}
