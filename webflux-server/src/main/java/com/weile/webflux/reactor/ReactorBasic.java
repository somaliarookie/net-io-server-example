package com.weile.webflux.reactor;

import com.weile.webflux.util.Utils;
import reactor.core.Disposable;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * @Auth weile
 * @Time 2019/12/3 13:05
 * @Description TODO
 **/
public class ReactorBasic {


	public static void main(String[] args) throws InterruptedException {

		Function<Integer,Integer> mapFunction = (e) -> {
			Utils.printThreadName(String.valueOf(e));
			return e + 1;
		};

		Consumer<Integer> consuerFunction = (e) -> {

			Utils.printThreadName("consumer:"+String.valueOf(e));
		};


		Flux.range(0, 10)
				.map(mapFunction)
				.subscribe(consuerFunction);



		//flux 无限流

		Disposable subscribe = Flux.interval(Duration.ofSeconds(1L))
				.subscribe(e -> Utils.printThreadName(String.valueOf(e)));

		try {
			Thread.sleep(10000L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		subscribe.dispose();



		//defer 惰性求值
		System.out.println("defer 惰性求值");
		Flux.defer(() -> Flux.just("just", "just1", "just2"))
				.subscribe(e->Utils.printThreadName(e));




		customSchedulers();


	}


	 public static void customSchedulers()
	 {

		 //定制执行器-
		 System.out.println("定制执行器 Schedulers ");
		 Flux.interval(Duration.ofSeconds(1L))
				 .subscribeOn(Schedulers.elastic())
				 .map(e -> {
				 	Utils.printThreadName("map:"+e);
				 	return String.valueOf(e);
				 })
				 .subscribe(e->Utils.printThreadName("subscribe:"+e));
		 try {
			 Thread.sleep(10000L);
		 } catch (InterruptedException e) {
			 e.printStackTrace();
		 }

	 }
}
