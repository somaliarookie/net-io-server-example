package com.weile.webflux.thread;

import com.weile.webflux.util.Utils;
import lombok.extern.slf4j.Slf4j;

import java.io.UTFDataFormatException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * @Auth weile
 * @Time 2019/12/10 20:06
 * @Description TODO
 **/
@Slf4j
public class CompletableFutureDemo {

	public static void main(String[] args) throws InterruptedException {


		TimeUnit.MILLISECONDS.sleep(5000);
		//异步获取
//		log.info("main-thenAccept");
//		CompletableFuture<String> work = getWork();
//		work.thenAccept(handler());

		log.info("main-thenAccept");
		CompletableFuture<String> work1 = getWork();
		work1.thenAcceptAsync(handler(),Utils.getThreadPoolExecutorWithName("handler"));

		TimeUnit.MILLISECONDS.sleep(500);


	}


	public static Consumer<String> handler() {

		Consumer<String> consumer = s -> {

			log.info("handle strat");
			try {
				TimeUnit.MILLISECONDS.sleep(4000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			log.info("handle end");


		};

		return consumer;

	}


	public static void getResultSynchronous() {

		CompletableFuture<String> work = getWork();


		try {
			long start = System.currentTimeMillis();

			log.info("wait result start:{}", start);
			String result = work.get();
			long end = System.currentTimeMillis();
			log.info("wait result end:{} ,cost {}", end, end - start);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
	}

	public static CompletableFuture<String> getWork() {

		CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {

			log.info("work start");
			try {
				TimeUnit.MILLISECONDS.sleep(4000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			log.info("work end");
			return "result";
		}, Utils.getThreadPoolExecutorWithName("work"));

		return future;

	}


}
