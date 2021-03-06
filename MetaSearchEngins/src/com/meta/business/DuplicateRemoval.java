package com.meta.business;

import java.util.ArrayList;
import java.util.List;

import com.meta.model.Result;
import com.meta.util.LOG;

public class DuplicateRemoval {
	private List<String> tags = new ArrayList<String>();
	private List<Result> results = new ArrayList<Result>();
	private int repeatCount = 0;

	public void setResults(List<Result> all) {
		this.results = all;
	}

	public void initial(List<Result> all) {
		results.addAll(all);
		for (Result r : all) {
			tags.add(r.getMd5());
		}
	}

	/**
	 * 将结果插入总的结果列表 TODO这里的去重是否要重新优化,后期在修改的时候去看哪种时间更加短 利用 tags 换成一个数组 利用中序插值算法
	 * ARRAYUTIL里面的东西要修改成泛型后才可以用全HEX比较
	 * 
	 * @param all
	 */
	public void insert(List<Result> all) {
		if (results == null) {
			initial(all);
		} else {
			for (int i = 0; i < all.size(); i++) {
				Result r = all.get(i);
				if (!tags.contains(r.getMd5())) {// if not exist add it to list
					tags.add(r.getMd5());
					results.add(r);
				} else {// else repeatCount++ and add the weight
					repeatCount++;
					int location = tags.indexOf(r.getMd5());
					Result rInAll = results.get(location);
					rInAll.setWeight(rInAll.getWeight() + r.getWeight());
					rInAll.setBaidu(true);
				}
			}
		}
	}

	public List<Result> getResults() {
		return results;
	}

	public int getResultRepeat() {
		return repeatCount;
	}

	public void show() {
		LOG.debug("----------------duplicateRemoval show-----------------");
		LOG.debug("重复条数:" + repeatCount);
		for (Result r : results) {
			LOG.debug(r.toString());
			LOG.error("***************************************");
		}
	}
}
