package com.weile.demo.function;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * @Auth weile
 * @Time 2019/12/3 11:41
 * @Description TODO
 **/
public class JavaBasicFunctionDemo {

	public static void main(String[] args) {

		// 消费型函数
		Consumer<String> consumerFunction = str -> System.out.println(str);
		consumerFunction.accept("demostr");

		//提供型函数
		Supplier<String> supplierFunction=  () ->  "demostr";
		System.out.println("supplier called:"+supplierFunction.get());


		//自定义函数
		CustomFunction<String,Integer>  customFunction = str -> new Integer(1);
		System.out.println(customFunction.doSomething("demostr"));




	}


	@FunctionalInterface
	public interface CustomFunction<T,R> {


		R doSomething(T t);
	}



}
