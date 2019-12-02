package com.weile.demo.lambda;

/**
 * @Auth weile
 * @Time 2019/11/30 18:17
 * @Description TODO
 **/
public class LambdaThis {


	private String name = "LambdaThis";

	public void test() {
		// 匿名类实现
		new Thread(new Runnable() {

			private String name = "Runnable";

			@Override
			public void run() {
				System.out.println("name:" + this.name);
			}
		}).start();

		// lambda实现
		new Thread(() -> {
			System.out.println("name:" + this.name);
		}).start();

		new Thread(() -> {
			System.out.println("这里的this指向当前的ThisDemo类:" + this.name);
		}).start();

		// lambda实现
		// 下面会自动生成lambda$1方法,由于使用了this,所以是static方法
		new Thread(() -> {
			System.out.println("这里没有引用this,生成的lambda1方法是static的");
		}).start();
	}

	public static void main(String[] args) {
		LambdaThis demo = new LambdaThis();
		demo.test();
	}




}
