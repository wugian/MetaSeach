package com.meta.business;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import com.meta.business.factory.GoogleFactory;
import com.meta.business.intertace.IParser;
import com.meta.model.Result;

public class SearchPlan {
	public List<Result> getResult(String search, String seSelected, String sePn) {
		// br = (ArrayList<Result>) baiduParser.parsePage(search);
		IParser googleParser = new GoogleFactory().produce();
		ArrayList<Result> gr = null;
		gr = (ArrayList<Result>) googleParser.parsePage(search);

		DuplicateRemoval dr = new DuplicateRemoval();
		dr.insert(gr);
		// dr.insert(br);
		dr.show();
		return null;
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