package com.weile.demo.reactor;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.Duration;

/**
 * @Auth weile
 * @Time 2019/12/3 18:41
 * @Description TODO
 **/

@RestController

public class ReactorController {


	@RequestMapping(value = "/flux",produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
	public Flux<Long> getFlux() {


		Flux.interval(Duration.ofSeconds(1L)).subscribe(System.out::println);

		return Flux.interval(Duration.ofSeconds(1L));

	}
}
