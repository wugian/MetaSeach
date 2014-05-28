package com.meta.business;

import java.util.Collections;
import java.util.List;

import com.meta.model.Result;

/**
 * 排序神马的都是浮动啊
 * @author tezuka-pc
 *
 */
public class ResultSort {
	public List<Result> sort(List<Result> results) {
		Collections.sort(results, new ComparatorResult());
		return results;
	}
}
