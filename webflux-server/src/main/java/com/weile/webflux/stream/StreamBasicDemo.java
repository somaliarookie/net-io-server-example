package com.weile.webflux.stream;

import com.weile.webflux.util.Utils;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.function.IntUnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @Auth weile
 * @Time 2019/12/3 12:08
 * @Description TODO
 **/
public class StreamBasicDemo {


	public static void main(String[] args) {


		IntUnaryOperator addFunction = x -> {
			System.out.println("add operation called");
			return x + 1;
		};

		//惰性求值
		IntStream.range(1, 5).map(addFunction).sum();//有终止操作才会调用过程操作
		IntStream.range(1, 5).map(addFunction);//无终止操作不会调用前面的中间操作


		Stream.of("a", "b", "c").forEach(elment -> System.out.println("element:" + elment));


		//map reduce
		IntStream.range(0, 10).map(elment -> elment + 10).filter(e -> e > 15).forEach(e -> System.out.println(e));
		System.out.println(IntStream.range(0, 10).map(elment -> elment + 10).filter(e -> e > 15).reduce((e1, e2) -> e1 + e2));


		//并行流

		System.out.println("stream parallel demo: ");
		IntUnaryOperator mapFunction= e -> {

			System.out.println("Thread:"+Thread.currentThread().getName()+" mapFunction:"+e);
			return e + 1;

		};

		//串行
		//IntStream.range(1, 100).map(mapFunction).sum();

		//并行
		IntStream.range(1, 100).parallel().map(mapFunction).sum();


		try {
			TimeUnit.MILLISECONDS.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		//收集器

		 List<Integer> list = new ArrayList<>();
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		list.add(5);


		List<Integer> collect = list.stream().collect(Collectors.toList());
		System.out.println(collect);


	}

}
