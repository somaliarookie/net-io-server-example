package com.weile.webflux.util;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Auth weile
 * @Time 2019/12/3 16:47
 * @Description TODO
 **/
@Slf4j
public class Utils {
	/**
	 * 打印日志并sleep 5 毫秒
	 *
	 * @param s
	 */
	public static void printThreadName(String s) {
		// System.out.println(s);
		// 带线程名(测试并行情况)

		log.info("thread:{} content:{}",Thread.currentThread().getName(),s);
		try {
			TimeUnit.MILLISECONDS.sleep(5);
		} catch (InterruptedException e) {
		}
	}

	public static ThreadPoolExecutor getThreadPoolExecutorWithName(String name)
	{
		//创建线程池供使用
		BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<Runnable>(2);
		DefaultThreadFactory  threadFactory= new DefaultThreadFactory(name);

		ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(1, 1, 100, TimeUnit.MILLISECONDS, workQueue,threadFactory);
		return poolExecutor;



	}


}

class DefaultThreadFactory implements ThreadFactory {
	private static final AtomicInteger poolNumber = new AtomicInteger(1);
	private final ThreadGroup group;
	private final AtomicInteger threadNumber = new AtomicInteger(1);
	private final String namePrefix;

	DefaultThreadFactory(String customName) {
		SecurityManager s = System.getSecurityManager();
		group = (s != null) ? s.getThreadGroup() :
				Thread.currentThread().getThreadGroup();
		namePrefix = customName+"-" +
				poolNumber.getAndIncrement() +
				"-thread-";
	}

	@Override
	public Thread newThread(Runnable r) {
		Thread t = new Thread(group, r,
				namePrefix + threadNumber.getAndIncrement(),
				0);
		if (t.isDaemon()){
			t.setDaemon(false);
		}
		if (t.getPriority() != Thread.NORM_PRIORITY){

			t.setPriority(Thread.NORM_PRIORITY);
		}
		return t;
	}
}
