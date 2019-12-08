package com.weile.webclientdemo.client;

import com.weile.webclientdemo.httpapi.User;
import org.springframework.http.HttpMethod;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

/**
 * @Auth weile
 * @Time 2019/12/8 20:41
 * @Description TODO
 **/
public class WebClientDemo {


	public static void main(String[] args) {
		webClientTest();
		feignClientTest();

	}

	private static void feignClientTest() {


	}

	private static void webClientTest() {
		WebClient webClient = WebClient.create("http://localhost:8080");
		Flux<User> userFlux = webClient.method(HttpMethod.GET).uri("/user/all")
				.retrieve().bodyToFlux(User.class);
		userFlux.subscribe(System.out::println);
	}


}
