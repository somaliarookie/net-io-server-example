package com.weile.webflux.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.awt.PageAttributes;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @Auth weile
 * @Time 2019/11/30 17:46
 * @Description 基本测试类
 **/

@RestController
@Slf4j
public class DemoTestController {


	@RequestMapping("/non-flux/test")
	public String nonFluxTest() throws InterruptedException {

		log.info("process started");
		String result = doSomething();
		log.info("process end");
		return result;

	}

	@RequestMapping("/mono/test")
	public Mono<String> monoTest() {

		log.info("mono process started");

		Mono<String> result = Mono.fromSupplier(()-> doSomething());

		log.info("mono process end");

		return result;
	}

	@RequestMapping(value = "/flux/test",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flux<String> fluxTest() {

		log.info("flux process started");

		Flux<String> result = Flux.fromStream(IntStream.range(0, 10).mapToObj((e) -> {

			String s = doSomething();

			return s + e;
		}));

		log.info("flux process end");

		return result;
	}

	private String doSomething()  {
		try {
			TimeUnit.SECONDS.sleep(1L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return "done";
	}


}
