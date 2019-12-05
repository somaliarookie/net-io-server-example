package com.weile.webflux.reactor;

import com.weile.webflux.util.Utils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

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


		return Flux.just("data1")
				.delayElements(Duration.ofMillis(lantency));

	}


	/**
	 * 无定制执行器
	 * @return
	 */
	@RequestMapping(value = "/thread/subscribe/default")
	public Flux<String> subcsribeThreadDefaultDefer() {




		return Flux.defer(() ->  {
			Utils.printThreadName("defer default");
			return Flux.just("33");
		});

	}

	/**
	 * 无定制执行器-改变执行器
	 * @return
	 */
	@RequestMapping(value = "/thread/subscribe/custom")
	public Flux<String> subcsribeThread() {
		return Flux.defer(() ->  {
			Utils.printThreadName("defer default");
			return Flux.just("11","22");
		}).subscribeOn(Schedulers.newElastic("subscribeOn"));

	}

}
