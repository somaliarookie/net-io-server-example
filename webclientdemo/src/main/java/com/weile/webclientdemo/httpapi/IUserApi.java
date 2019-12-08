package com.weile.webclientdemo.httpapi;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import reactor.core.publisher.Flux;

/**
 * @Auth weile
 * @Time 2019/12/8 19:52
 * @Description TODO
 **/
@ApiServer("http://localhost:8080/user")
@Service
public interface IUserApi {

	@GetMapping("/user/all")
	Flux<User> getAllUser();
}
