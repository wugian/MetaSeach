package com.meta.model;

public class Result {
	private String url;
	private String title;
	private String sumary;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
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

	@Override
	public String toString() {
		return "Result /n  url=" + url + "  /n title=" + title + " /n sumary="
				+ sumary;
	}

}
