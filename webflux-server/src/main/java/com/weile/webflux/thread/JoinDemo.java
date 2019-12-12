package com.weile.webflux.thread;

import lombok.extern.slf4j.Slf4j;

/**
 * @Auth weile
 * @Time 2019/12/12 21:15
 * @Description TODO
 *  join means " Waits for this thread to die."
 **/
@Slf4j
public class JoinDemo {

	public static void main(String[] args) {
		Worker worker1 = new Worker(50);
		Worker worker2 = new Worker(100);
		Worker worker3 = new Worker(100);

		try {
			worker1.start();
			worker2.start();
			worker1.join();//main thread wait for worker1 to die
			log.info("waitting workder1 to die ");
			worker2.join();
			log.info("waitting workder2 to die ");
			worker3.start();
			worker3.join();
		} catch (InterruptedException IE) {
			IE.printStackTrace();
		}
	}
}

@Slf4j
class Worker extends Thread {

	private long workTime;

	public Worker(long workTime) {
		this.workTime = workTime;
	}

	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {

			log.info("do sth:{}",i);
			try {
				Thread.sleep(workTime);

			} catch (Exception ex) {
			}
		}
	}
}

