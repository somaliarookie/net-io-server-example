package com.weile.webflux.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * @Auth weile
 * @Time 2019/11/30 17:46
 * @Description TODO
 **/

@RestController
public class TestController {



	@RequestMapping("/test")
	public String getSth() {

		Mono.just("mono1");
		return "sth";

	}




}
