package useThreadFactoryInExecutor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
/**
 * 在Executor中使用定制的线程工厂
 * @author soft01
 *
 */
public class Main {

	public static void main(String[] args) throws InterruptedException {

		MyThreadFactory threadFactory = new MyThreadFactory("myThreadFactory");
		ExecutorService executor = Executors.newCachedThreadPool(threadFactory);
		MyTask task = new MyTask();
		executor.submit(task);
		executor.shutdown();
		executor.awaitTermination(1, TimeUnit.DAYS);
		System.out.println("end");
	}

}
