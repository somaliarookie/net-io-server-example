package com.weile.mvc.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

/**
 * @Auth weile
 * @Time 2019/11/30 17:46
 * @Description TODO
 **/

@RestController
public class TestController {



	@RequestMapping("/test")
	public String getSth() {






		return "sth";

	}

	@RequestMapping(value = "/mvc/lantency/{lantency}")
	public String getWithLantency(@PathVariable Long lantency) {

		System.out.println("lantency:"+lantency);

		try {
			TimeUnit.MILLISECONDS.sleep(lantency);   // 1
		} catch (InterruptedException e) {
			return "Error during thread sleep";
		}

		return "mvc response";
	}



}
