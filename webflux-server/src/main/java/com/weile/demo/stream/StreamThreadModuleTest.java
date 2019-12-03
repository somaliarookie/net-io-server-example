package com.weile.demo.stream;

import java.util.Random;
import java.util.function.IntUnaryOperator;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static com.weile.demo.reactor.util.Utils.printThreadName;

/**
 * @Auth weile
 * @Time 2019/12/2 18:07
 * @Description TODO
 **/
public class StreamThreadModuleTest {

	public static void main(String[] args) {

		IntUnaryOperator mapFunction = (e) -> {
			printThreadName(String.valueOf(e));
			return e + 1;
		};

		IntStream.range(1, 5).map(mapFunction).forEach((e)-> System.out.println(e));




		Random random = new Random();
		// 随机产生数据
		Stream<Integer> stream = Stream.generate(() -> random.nextInt())
				// 产生500个 ( 无限流需要短路操作. )
				.limit(5)
				// 第1个无状态操作
				.peek(s -> printThreadName("peek: " + s))
				// 第2个无状态操作
				.filter(s -> {
					printThreadName("filter: " + s);
					return s > 1000000;
				})
				// 有状态操作
				.sorted((i1, i2) -> {
					printThreadName("排序: " + i1 + ", " + i2);
					return i1.compareTo(i2);
				})
				// 又一个无状态操作
				.peek(s -> {
					printThreadName("peek2: " + s);
				}).parallel();

		// 终止操作
		System.out.println("stream.elementcount:"+stream.count());
	}




}
