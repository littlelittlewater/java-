package current.design014;

import java.util.concurrent.Callable;

/*
 *  利用代理模式实现future
 */
public class Main {

	public static void main(String[] args) throws Exception {
		
		FutureClient fc = new FutureClient();
		Data data = fc.request("请求参数");
		System.out.println("请求发送成功!");
		System.out.println("做其他的事情...");
		String result = data.getRequest();
		System.out.println(result);

		//另一个future
		Future<String> future = new Future<>(new Callable<String>() {
			@Override
			public String call() throws Exception {
				Thread.sleep(3000);
				return "我做好了";
			}
		});
		System.out.println("请求发送成功!");
		System.out.println("做其他的事情...");
		System.out.println(future.get());
	}
}
