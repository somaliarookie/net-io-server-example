package com.weile.webclientdemo.httpapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

/**
 * @Auth weile
 * @Time 2019/12/8 19:58
 * @Description TODO
 **/
@RestController
public class TestClientController {

	@Autowired
	IUserApi userApi;

	@GetMapping("/client/user/all")
	public void getAllUser(){

		Flux<User> allUser = userApi.getAllUser();
		allUser.subscribe(System.out::println);


	}
}
