package com.weile.demo.util;

import java.util.concurrent.TimeUnit;

/**
 * @Auth weile
 * @Time 2019/12/3 16:47
 * @Description TODO
 **/
public class Utils {
	/**
	 * 打印日志并sleep 5 毫秒
	 *
	 * @param s
	 */
	public static void printThreadName(String s) {
		// System.out.println(s);
		// 带线程名(测试并行情况)
		System.out.println("Thread:"+Thread.currentThread().getName() + " > " + s);
		try {
			TimeUnit.MILLISECONDS.sleep(5);
		} catch (InterruptedException e) {
		}
	}
}
