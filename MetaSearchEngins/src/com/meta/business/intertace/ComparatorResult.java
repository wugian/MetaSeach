package com.meta.business.intertace;

import java.util.Comparator;

import com.meta.model.Result;

public class ComparatorResult implements Comparator<Result> {

	public int compare(Result obj1, Result obj2) {
		Result result1 = (Result) obj1;
		Result result2 = (Result) obj2;

		// 首先比较weight，如果weight相同，则比较MD5

		int flag = 0;
		flag = result1.getWeight() > result2.getWeight() ? -1 : 1;
		if (flag == 0) {
			return -1;
		} else {
			return flag;
		}
	}
}