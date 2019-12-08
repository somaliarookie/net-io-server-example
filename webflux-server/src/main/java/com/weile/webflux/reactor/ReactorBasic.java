package com.weile.webflux.reactor;

import com.weile.webflux.util.Utils;

import reactor.core.Disposable;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;
import java.util.concurrent.Flow;
import java.util.concurrent.SubmissionPublisher;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * @Auth weile
 * @Time 2019/12/3 13:05
 * @Description TODO
 **/
public class ReactorBasic {


	public static void main(String[] args) throws InterruptedException {

		pubSubDemo();

//		Function<Integer, Integer> mapFunction = (e) -> {
//			Utils.printThreadName(String.valueOf(e));
//			return e + 1;
//		};
//
//		Consumer<Integer> consuerFunction = (e) -> {
//
//			Utils.printThreadName("consumer:" + String.valueOf(e));
//		};
//
//
//		Flux.range(0, 10)
//				.map(mapFunction)
//				.subscribe(consuerFunction);
//
//
//		//flux 无限流
//
//		Disposable subscribe = Flux.interval(Duration.ofSeconds(1L))
//				.subscribe(e -> Utils.printThreadName(String.valueOf(e)));
//
//		try {
//			Thread.sleep(10000L);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//
//		subscribe.dispose();
//
//
//		//defer 惰性求值
//		System.out.println("defer 惰性求值");
//		Flux.defer(() -> Flux.just("just", "just1", "just2"))
//				.subscribe(e -> Utils.printThreadName(e));
//
//
//		customSchedulers();


	}


	public static void customSchedulers() {

		//定制执行器-
		System.out.println("定制执行器 Schedulers ");
		Flux.interval(Duration.ofSeconds(1L))
				.subscribeOn(Schedulers.elastic())
				.map(e -> {
					Utils.printThreadName("map:" + e);
					return String.valueOf(e);
				})
				.subscribe(e -> Utils.printThreadName("subscribe:" + e));
		try {
			Thread.sleep(10000L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}


	public static void pubSubDemo() throws InterruptedException {

		// 1. 定义 String 类型的数据发布者，JDK 9自带的
		// SubmissionPublisher 实现了 Publisher
		SubmissionPublisher<String> publisher = new SubmissionPublisher<>();

		// 2. 创建一个订阅者，用于接收发布者的消息
		Flow.Subscriber<String> subscriber = new Flow.Subscriber<>() {
			private Flow.Subscription subscription;

			@Override
			public void onSubscribe(Flow.Subscription subscription) {
				// 通过 Subscription 和发布者保持订阅关系，并用它来给发布者反馈
				this.subscription = subscription;
				// 请求一个数据
				this.subscription.request(1);
			}

			@Override
			public void onNext(String item) {
				// 接收发布者发布的消息
				System.out.println("【订阅者】接收消息 <------ " + item);
				// 模拟接收数据缓慢，让缓冲池填满

				this.subscription.request(1);
				try {
					TimeUnit.SECONDS.sleep(2);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				// 接收后再次请求一个数据

				// 如果不想再接收数据，也可以直接调用 cancel，表示不再接收了
				// this.subscription.cancel();
			}

			@Override
			public void onError(Throwable throwable) {
				// 过程中出现异常会回调这个方法
				System.out.println("【订阅者】数据接收出现异常，" + throwable);

				// 出现异常，取消订阅，告诉发布者我不再接收数据了
				// 实际测试发现，只要订阅者接收消息出现异常，进入了这个回调
				// 订阅者就不会再继续接收消息了
				this.subscription.cancel();
			}

			@Override
			public void onComplete() {
				// 当发布者发出的数据都被接收了，
				// 并且发布者关闭后，会回调这个方法
				System.out.println("【订阅者】数据接收完毕");
			}
		};

		// 3. 发布者和订阅者需要建立关系
		publisher.subscribe(subscriber);

		// 4. 发布者开始发布数据
		for (int i = 0; i < 500; i++) {
			String message = "hello flow api " + i;
			System.out.println("【发布者】发布消息 ------> " + message);
			publisher.submit(message);
		}

		// 5. 发布结束后，关闭发布者
		publisher.close();

		// main线程延迟关闭，不然订阅者还没接收完消息，线程就被关闭了
		Thread.currentThread().join(2000);
	}

}
