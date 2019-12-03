package com.weile.demo.reactor;

import reactor.core.publisher.Flux;

import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntUnaryOperator;

/**
 * @Auth weile
 * @Time 2019/12/3 13:05
 * @Description TODO
 **/
public class ReactorBasic {


	public static void main(String[] args) {

		Function<Integer,Integer> mapFunction = (e) -> {
			print(String.valueOf(e));
			return e + 1;
		};

		Consumer<Integer> consuerFunction = (e) -> {

			print("consumer:"+String.valueOf(e));
		};


		Flux.range(0, 10).map(mapFunction).subscribe(consuerFunction);
	}

	/**
	 * 打印日志并sleep 5 毫秒
	 *
	 * @param s
	 */
	public static void print(String s) {
		// System.out.println(s);
		// 带线程名(测试并行情况)
		System.out.println("Thread:"+Thread.currentThread().getName() + " > " + s);
		try {
			TimeUnit.MILLISECONDS.sleep(5);
		} catch (InterruptedException e) {
		}
	}
}
