package com.meta.model;

import com.meta.util.MD5Util;

public class Result {
  private String url;
  private String title;
  private String sumary;
  private String md5;
  private double score;//计算本条搜索结果的权值

  public String getMd5() {
    return md5;
  }

  public void setMd5(String md5) {
    this.md5 = md5;
  }
  
  public doubl getScore() {
    return score;
  }

  public void setScore(double score) {
    this.score = score;
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
        + "\n sumary=" + sumary + "\n md5 = " + md5 + "\n score = " + score;

  }
}
