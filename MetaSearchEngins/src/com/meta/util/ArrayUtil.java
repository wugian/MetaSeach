package com.meta.util;

/**
 * 数组常用工具类,包括二分排序,查找,插入,扩展等
 * @author tezuka-pc
 *
 */
public class ArrayUtil {
	private final static int NOT_FOUND = -1;

	/**
	 * 折半查找排序
	 * 
	 * @param data
	 * @return
	 */
	public static int[] binaryRanking(int[] data) {
		long cu = System.currentTimeMillis();
		int length = data.length;
		for (int i = 1; i < length; i++) {
			int tempVal = data[i];
			int low = 0;
			int high = i - 1;
			while (low <= high) {
				int middle = (low + high) / 2;
				if (tempVal < data[middle])
					high = middle - 1;
				else
					low = middle + 1;
			}
			for (int j = i; j > high + 1; j--)
				data[j] = data[j - 1];
			data[high + 1] = tempVal;
		}
		System.out.println(System.currentTimeMillis() - cu);
		return data;
	}

	/**
	 * 非递归法二分查找
	 * 
	 * @param data
	 *            待查数组
	 * @param x
	 *            待查元素
	 * @return
	 */

	public static int bisearch(int data[], int x) {
		int low = 0;// 开始位置
		int high = data.length - 1;// 中间位置
		int mid;// 中间位置
		if (low > high) {
			LOG.debug("the array is null");
			return NOT_FOUND;
		}
		while (low <= high) {
			mid = (low + high) / 2;
			if (x == data[mid]) {
				return mid;
			} else if (data[mid] < x) {
				low = mid + 1;
			} else if (data[mid] > x) {
				high = mid - 1;
			}
		}// end while
		LOG.debug("not found");
		return NOT_FOUND;
	}

	/**
	 * 递归法二分查找
	 * 
	 * @param data
	 *            查询元素数组
	 * @param x
	 *            待查元素
	 * @param high
	 * @param low
	 * @return
	 */
	public int iterBisearch(int data[], int x, int high, int low) {
		int mid = -1;
		mid = (high + low) / 2;
		if (x == data[mid]) {
			return mid;
		} else if (x < data[mid]) {
			return iterBisearch(data, x, high, mid - 1);
		} else if (x > data[mid]) {
			return iterBisearch(data, x, mid + 1, low);
		}
		return -1;
	}

	public static int[] insert(int[] data, int value) {
		int position = bisearch(data, value);
		if (position != NOT_FOUND)
			return data;
		// method1
		int[] copy = expandArray(data, 1);
		copy = insertValue(copy, value);
		// method2
		// int[] copy1 = expandArray(data, 1);
		// copy1[copy1.length - 1] = value;
		// copy1 = bInsertSort(copy1);
		return copy;
	}

	public static int[] expandArray(int[] data, int size) {
		int length = data.length;
		int[] copy = new int[length + size];
		System.arraycopy(data, 0, copy, 0, Math.min(length, copy.length));
		return copy;
	}

	/**
	 * 将元素有序插入数组
	 * 
	 * @param copy
	 * @param value
	 * @return
	 */
	private static int[] insertValue(int copy[], int value) {
		long cu = System.currentTimeMillis();
		boolean valueNotUse = false;
		for (int i = copy.length - 1; i > 1; i--) {
			if (copy[i - 1] < value) {
				copy[i] = value;
				valueNotUse = true;
				System.out.println("time:" + (System.currentTimeMillis() - cu));
				return copy;
			} else if (copy[i - 1] >= value) {
				copy[i] = copy[i - 1];
			}
			if (valueNotUse)
				copy[0] = value;
		}// end for
		System.out.println("time:" + (System.currentTimeMillis() - cu));
		return copy;
	}

	public static void main(String[] args) {
		int[] a = { 1, 2, 4, 5, 22, 36, 62, 76, 84, 90 };
		// bInsertSort(a);
		System.out.println("排序前:");
		for (int i = 0; i < a.length; i++)
			System.out.print(a[i] + ", ");
		System.out.println("insert");
		int[] newData = insert(a, 100);
		System.out.println("排序后:" + newData.length);
		for (int i = 0; i < newData.length; i++)
			System.out.print(newData[i] + ", ");
	}
}