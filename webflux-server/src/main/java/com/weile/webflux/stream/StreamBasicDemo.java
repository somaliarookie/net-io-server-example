package com.weile.webflux.stream;

import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @Auth weile
 * @Time 2019/12/3 12:08
 * @Description TODO
 **/
public class StreamBasicDemo {


	public static void main(String[] args) {

		Stream.of("a", "b", "c").forEach(elment -> System.out.println("element:"+elment));


		//map reduce
		IntStream.range(0, 10).map(elment -> elment + 10).filter(e ->  e>15 ).forEach(e-> System.out.println(e));
		System.out.println(IntStream.range(0, 10).map(elment -> elment + 10).filter(e ->  e>15 ).reduce( (e1,e2)  -> e1+e2));


	}
}
