package com.meta.model;

import com.meta.util.MD5Util;

public class Result {
	private String url;
	private String title;
	private String sumary;
	private String md5;
	private double weight;// 计算本条搜索结果的权值
	// 应该设置一个字符串记录有哪些搜索引擎有当前结果，利用append方法添加，格式可以为利用,分开
	private boolean google = false;
	private boolean baidu = false;

	public boolean isGoogle() {
		return google;
	}

	public void setGoogle(boolean google) {
		this.google = google;
	}

	public boolean isBaidu() {
		return baidu;
	}

	public void setBaidu(boolean baidu) {
		this.baidu = baidu;
	}

	public String getMd5() {
		return md5;
	}

	public void setMd5(String md5) {
		this.md5 = md5;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double score) {
		this.weight = score;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
		// 在设置URL的时候设置MD5
		setMd5(MD5Util.getMD5String(url).toLowerCase());
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSumary() {
		return sumary;
	}

	public void setSumary(String sumary) {
		this.sumary = sumary;
	}

	private static int count;
	static {
		count = 0;
	}

	@Override
	public String toString() {
		count++;
		return "Result " + count + ":\n title =" + title + "\n url = " + url
				+ "\n sumary=" + sumary + "\n md5 = " + md5 + "\n score = "
				+ weight;

	}
}
