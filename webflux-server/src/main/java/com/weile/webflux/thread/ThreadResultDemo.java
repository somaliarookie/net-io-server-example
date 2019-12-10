package com.weile.webflux.thread;

import com.weile.webflux.util.Utils;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Auth weile
 * @Time 2019/12/10 12:51
 * @Description TODO
 **/

@Slf4j
public class ThreadResultDemo {


	public static void main(String[] args) {


		nonResult();
		synchronousResult();

	}


	/**
	 * 无返回结果
	 */
	public static void nonResult() {


		new Thread(() -> System.out.println(" thread-runnable without result"));

	}

	/**
	 * 同步等待结果返回
	 */
	public static void synchronousResult() {

		Callable<String> work = () -> {

			log.info("work");

			TimeUnit.MILLISECONDS.sleep(400);
			return "result";
		};


		ThreadPoolExecutor threadPoolExecutor = Utils.getThreadPoolExecutorWithName("work");


		//使用线程池执行任务
		Future<String> future = threadPoolExecutor.submit(work);


		//获取任务结果
		try {

			long start = System.currentTimeMillis();

			log.info("wait result start:{}", start);
			String result = future.get();
			long end = System.currentTimeMillis();
			log.info("wait result end:{} ,cost {}", end, end - start);


		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		log.info("do sth else ");



	}

}
