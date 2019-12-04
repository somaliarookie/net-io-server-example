package com.weile.webflux.future;

import com.weile.webflux.util.Utils;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

import static java.util.concurrent.Executors.*;

/**
 * @Auth weile
 * @Time 2019/12/3 16:40
 * @Description TODO
 **/
public class FutureDemo {


	public static void main(String[] args) {

		ExecutorService executorService = newSingleThreadExecutor();

		//future+callable
		Future<?> future = executorService.submit(() -> {
			Utils.printThreadName("callable");
			return "callable result";
		});

		try {
			System.out.println(future.get());
		} catch (InterruptedException e) {
		} catch (ExecutionException e) {
			e.printStackTrace();
		}


		//futuretask


		FutureTask<String> futureTask = new FutureTask<String>(() -> {
			Utils.printThreadName("futureTask");
			return "futureTask result";
		});

		Future<?> futureResult = executorService.submit(futureTask);

		try {
			System.out.println(futureResult.get());
		} catch (InterruptedException e) {
		} catch (ExecutionException e) {
			e.printStackTrace();
		}

	}

}

