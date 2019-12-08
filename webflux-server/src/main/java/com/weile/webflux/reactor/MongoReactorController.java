package com.weile.webflux.reactor;

import com.weile.webflux.reactor.domain.User;
import com.weile.webflux.reactor.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @Auth weile
 * @Time 2019/12/8 17:00
 * @Description TODO
 **/

@RestController
public class MongoReactorController {

	@Autowired
	private UserRepository userRepository;


	/**
	 * 一次性返回数据
	 *
	 * @return
	 */
	@GetMapping("/user/all")
	public Flux<User> getAll() {

		return userRepository.findAll();

	}

	/**
	 * 流方式返回数据
	 *
	 * @return
	 */
	@GetMapping(value = "/user/all/stream", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
	public Flux<User> getAllWithStream() {

		return userRepository.findAll();

	}

	@PostMapping(value = "/user/add")
	public Mono<User> addUser(@RequestBody User user) {

		return userRepository.save(user);

	}

}
