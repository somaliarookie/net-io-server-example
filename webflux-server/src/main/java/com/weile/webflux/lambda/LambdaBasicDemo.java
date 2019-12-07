package com.weile.webflux.lambda;

import java.sql.SQLOutput;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.Supplier;

/**
 * @Auth weile
 * @Time 2019/12/7 10:58
 * @Description TODO
 **/



interface Interface1{
	public  void doSth();
}

class Demo{


	String name;
	public Demo(String name) {

		this.name=name;
	}

	public Demo() {

	}

	public static  void staticMethod(String msg) {
		System.out.println("staticMethod");
	}

	public   void nonStaticMethod(String msg) {
		System.out.println("not staticMethod");
	}

}

public class LambdaBasicDemo {


	public static void main(String[] args) {

		//使用lambda代替接口实现类
		new Thread(()-> System.out.println("lambda test")).start();

		Interface1 interface1 = () -> System.out.println("do sth");
		interface1.doSth();


		Consumer consumer = System.out::println;
		consumer.accept("consumer");

		//方法引用-静态方法

		Consumer<String> consumer1 =Demo::staticMethod;
		consumer1.accept("static");

		//方法引用-非静态方法
		Demo demoObj = new Demo();

		Consumer<String> consumer2 =demoObj::nonStaticMethod;
		consumer2.accept("nonstatic");

		BiConsumer<Demo,String> biFunction= Demo::nonStaticMethod;
		biFunction.accept(new Demo(),"nonstatic");

		//构造函数
		Supplier<Demo>  newDemo=Demo::new;
		newDemo.get().nonStaticMethod("method called");

		//构造函数-带参数
		Function<String, Demo> newDemoFunction = Demo::new;
		newDemoFunction.apply("").nonStaticMethod("method called");







	}
}
