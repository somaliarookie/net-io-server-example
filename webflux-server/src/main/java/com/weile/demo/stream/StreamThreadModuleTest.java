package com.weile.demo.stream;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.function.IntUnaryOperator;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @Auth weile
 * @Time 2019/12/2 18:07
 * @Description TODO
 **/
public class StreamThreadModuleTest {

	public static void main(String[] args) {

		IntUnaryOperator mapFunction = (e) -> {
			print(String.valueOf(e));
			return e + 1;
		};

		IntStream.range(1, 5).map(mapFunction).forEach((e)-> System.out.println(e));




		Random random = new Random();
		// 随机产生数据
		Stream<Integer> stream = Stream.generate(() -> random.nextInt())
				// 产生500个 ( 无限流需要短路操作. )
				.limit(5)
				// 第1个无状态操作
				.peek(s -> print("peek: " + s))
				// 第2个无状态操作
				.filter(s -> {
					print("filter: " + s);
					return s > 1000000;
				})
				// 有状态操作
				.sorted((i1, i2) -> {
					print("排序: " + i1 + ", " + i2);
					return i1.compareTo(i2);
				})
				// 又一个无状态操作
				.peek(s -> {
					print("peek2: " + s);
				}).parallel();

		// 终止操作
		System.out.println("stream.elementcount:"+stream.count());
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
