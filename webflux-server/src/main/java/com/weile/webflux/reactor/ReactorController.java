package com.weile.webflux.reactor;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
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


	@RequestMapping(value = "/flux-stream",produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
	public Flux<Long> getFlux() {


		Flux.interval(Duration.ofSeconds(1L)).subscribe(System.out::println);

		return Flux.interval(Duration.ofSeconds(1L));

	}


	@RequestMapping(value = "/flux/lantency/{lantency}")
	public Flux<String> getFluxWithLantency(@PathVariable Long lantency) {


		System.out.println("lantency:"+lantency);


		return Flux.just("data1","data2","data3")
				.delayElements(Duration.ofMillis(lantency));

	}
}
