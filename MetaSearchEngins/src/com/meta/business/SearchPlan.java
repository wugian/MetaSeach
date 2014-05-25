package com.meta.business;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import com.meta.business.factory.BaseParser;
import com.meta.business.factory.CopyOfBaiduFactory;
import com.meta.business.factory.GoogleFactory;
import com.meta.business.intertace.IParser;
import com.meta.model.Result;
import com.meta.util.LOG;

public class SearchPlan {
	private static final int SELECTED = 1;

	public List<Result> getResult(String search, String seSelected, String sePn) {
		LOG.error("SearchPlan getResult");
		int size = seSelected.length();
		int[] selected = new int[size];// 选择的搜索引擎
		int[] pns = new int[size];// 对应搜索引擎的关心页码
		LOG.error("SearchPlan getResult:" + size);
		// initial the selected and pns
		for (int i = 0; i < size; i++) {
			selected[i] = Integer.parseInt(seSelected.substring(i, i + 1));
			LOG.error("SearchPlan getResult:" + selected[i]);
		}
		for (int j = 0; j < size; j++) {
			pns[j] = Integer.parseInt(sePn.substring(j, j + 1));
			LOG.error("SearchPlan getResult:" + pns[j]);
		}

		for (int i = 0; i < size; i++) {
			// for google
			if (selected[0] == SELECTED) {
				LOG.error("google selected");
				startSearch(search, BaseParser.GOOGLE, pns[i]);
			}
			// for baidu
			if (selected[1] == SELECTED) {
				LOG.error("baidu selected");
				startSearch(search, BaseParser.BAIDU, pns[i]);
			}

		}
		// br = (ArrayList<Result>) baiduParser.parsePage(search);
		// IParser googleParser = new GoogleFactory().produce();
		// ArrayList<Result> gr = null;
		// gr = (ArrayList<Result>) googleParser.parsePage(search);
		//
		// DuplicateRemoval dr = new DuplicateRemoval();
		// dr.insert(gr);
		// // dr.insert(br);
		// dr.show();
		return null;
	}

	/**
	 * 开始搜索，利用多线程
	 * 
	 * @param search
	 *            搜索内容
	 * @param pn
	 *            用户所关心的页数，比如google前2页
	 * @param searchType
	 *            搜索引擎类型
	 */
	private void startSearch(String search, int searchType, int pn) {
		if (searchType == BaseParser.GOOGLE)
			LOG.error("this is google search");
		else if (searchType == BaseParser.BAIDU)
			LOG.error("this is baidu search");

		Thread threads[] = new Thread[2 * pn];
		for (int i = 0; i < 2 * pn; i++) {
			LOG.error("i:" + i);
			threads[i] = new JoinThread(search, searchType, i);
		}
		for (int i = 0; i < 2 * pn; i++) {
			// 运行刚才建立的100个线程
			threads[i].start();
		}
		for (int i = 0; i < 2 * pn; i++) {
			// 100个线程都执行完后继续
			try {
				threads[i].join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		// return JoinThread.results;
	}

	public class JoinThread extends Thread {
		public List<Result> results = new ArrayList<Result>();
		// public static volatile int s = 0;
		int pn;
		int searchType;
		String search;
		IParser iPaser;
		DuplicateRemoval dr = new DuplicateRemoval();

		public JoinThread(String search, int searchType, int pn) {
			super();
			this.pn = pn;
			this.searchType = searchType;
			this.search = search;
			LOG.error("pn" + pn);
		}

		@Override
		public void run() {
			super.run();
			switch (searchType) {
			case BaseParser.GOOGLE:
				iPaser = new GoogleFactory().produce();
			case BaseParser.BAIDU:
				iPaser = new CopyOfBaiduFactory().produce();
			}
			try {
				Thread.sleep(30);
			} catch (Exception e) {
				e.printStackTrace();
			}

			List<Result> cur = iPaser.parsePage(search, pn);

			synchronized (results) {
				results.addAll(cur);
				try {
					Thread.sleep(1);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 存款线程类
	 */
	class SaveThread extends Thread {
		private String name; // 操作人
		private MyCount myCount; // 账户
		private int x; // 存款金额

		SaveThread(String name, MyCount myCount, int x) {
			this.name = name;
			this.myCount = myCount;
			this.x = x;
		}

		public void run() {
			myCount.saving(x, name);
		}
	}

	/**
	 * 取款线程类
	 */
	class DrawThread extends Thread {
		private String name; // 操作人
		private MyCount myCount; // 账户
		private int x; // 存款金额

		DrawThread(String name, MyCount myCount, int x) {
			this.name = name;
			this.myCount = myCount;
			this.x = x;
		}

		public void run() {
			myCount.drawing(x, name);
		}
	}

	/**
	 * 普通银行账户，不可透支
	 */
	class MyCount {
		private String oid; // 账号
		private int cash; // 账户余额
		private Lock lock = new ReentrantLock(); // 账户锁
		private Condition _save = lock.newCondition(); // 存款条件
		private Condition _draw = lock.newCondition(); // 取款条件

		MyCount(String oid, int cash) {
			this.oid = oid;
			this.cash = cash;
		}

		/**
		 * 存款
		 * 
		 * @param x
		 *            操作金额
		 * @param name
		 *            操作人
		 */
		public void saving(int x, String name) {
			lock.lock(); // 获取锁
			if (x > 0) {
				cash += x; // 存款
				System.out.println(name + "存款" + x + "，当前余额为" + cash);
			}
			_draw.signalAll(); // 唤醒所有等待线程。
			lock.unlock(); // 释放锁
		}

		/**
		 * 取款
		 * 
		 * @param x
		 *            操作金额
		 * @param name
		 *            操作人
		 */
		public void drawing(int x, String name) {
			lock.lock(); // 获取锁
			try {
				if (cash - x < 0) {
					_draw.await(); // 阻塞取款操作
				} else {
					cash -= x; // 取款
					System.out.println(name + "取款" + x + "，当前余额为" + cash);
				}
				_save.signalAll(); // 唤醒所有存款操作
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				lock.unlock(); // 释放锁
			}
		}
	}
}