package com.weile.demo.reactor;

import com.weile.demo.util.Utils;
import reactor.core.publisher.Flux;

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


		Flux.range(0, 10).map(mapFunction).subscribe(consuerFunction);



		//flux 无限流

		Flux.interval(Duration.ofSeconds(1L)).subscribe(e ->Utils.printThreadName(String.valueOf(e)));

		try {
			Thread.sleep(10000L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

}
