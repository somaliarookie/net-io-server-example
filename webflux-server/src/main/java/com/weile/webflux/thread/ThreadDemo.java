package com.weile.webflux.thread;

import com.weile.webflux.util.Utils;

import java.util.concurrent.TimeUnit;

/**
 * @Auth weile
 * @Time 2019/12/5 17:19
 * @Description TODO
 **/
public class ThreadDemo {


	public static void main(String[] args) {


		//running
		new Thread(() -> {
			Thread.currentThread().setName("running");
			while (true) {
				int i = 1 + 1;
			}
		}).start();


		//sleep
		new Thread(() -> {
			Thread.currentThread().setName("sleep");
			try {
				TimeUnit.MILLISECONDS.sleep(100000L);   // 1
			} catch (InterruptedException e) {
			}

		}).start();


		//wait
		ThreadA ta = new ThreadA();
		ta.start();


	}
}

	 class ThreadA extends Thread {

		 ThreadB b = new ThreadB();

		 @Override
		 public void run() {
		 	this.setName("wait");
			 synchronized (b) {
				 try {
					 System.out.println("Waiting for b to complete...");
					 b.start();
					 b.wait();
//					 b.wait(10000L);
				 } catch (InterruptedException e) {
					 e.printStackTrace();
				 }
			 }


		 }
	 }

		 class ThreadB extends Thread {
			 int total;

			 @Override
			 public void run() {
				 this.setName("notify");
				 synchronized (this) {
					 for (int i = 0; i < 100000; i++) {
						 total += i;
					 }
					 try {
						 TimeUnit.MILLISECONDS.sleep(10000L);   // 1
					 } catch (InterruptedException e) {
					 }
					 System.out.println("notify ");
					 notify();
				 }
			 }
		 }





