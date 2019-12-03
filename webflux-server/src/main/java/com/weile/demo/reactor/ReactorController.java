package com.weile.demo.reactor;

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


	@RequestMapping("/flux")
	public Flux getFlux() {


		return Flux.just("1", "2").interval(Duration.ofMinutes(1L));

	}
}
