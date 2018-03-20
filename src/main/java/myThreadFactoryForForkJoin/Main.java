package myThreadFactoryForForkJoin;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;
/**
 * Java提供了ForkJoinWorkerThread类，它继承了Thread类
 * 并实现了可在Fork/Join框架里使用的工作线程。
 * 本例MyWorkerThread类继承了ForkJoinWorkerT,覆盖了
 * 类的两个方法。在每个工作线程中实现了一个计数器，统计一个
 * 工作线程执行了多少任务，我们使用了ThreadLoacl类型的计数器。
 * @author soft01
 *
 */
public class Main {

	public static void main(String[] args) {
		MyWorkerThreadFactory factory = new MyWorkerThreadFactory();
		ForkJoinPool pool = new ForkJoinPool(4,factory,null,false);
		int[] array = new int[1000];
		for(int i=0;i<array.length;i++) {
			array[i] = 1;
		}
		MyRecursiveTask task = new MyRecursiveTask(array,0,array.length);
		pool.execute(task);
		task.join();
		pool.shutdown();
		try {
			pool.awaitTermination(1, TimeUnit.DAYS);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			System.out.println("Main:Result:"+task.get());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("end");
	}

}
