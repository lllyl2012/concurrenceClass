package myForkJoinTaskForForkJoin;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;
/**
 * 定制运行在Fork/Join框架中的任务。
 * MyWorkerTask继承了ForkJoinTask类。它能在ForkJoinPool中
 * 运行，能使用执行器的所有优点，比如工作窃取算法.这与RecursiveAction和
 * RecursiveTask相同.
 * 
 * 必须实现下面这三种方法:
 * setRawResult()；被用来设置任务的结果，如果不返回任务结果，则方法为空。
 * getRawResult()；被用来返回任务的结果，如果不返回结果，方法必须返回null。
 * exec（）：实现任务逻辑。本例将逻辑委托到了抽象方法compute()中（这点和RecursiveAction和
 * RecursiveTask一样），在本方法中测量该方法的执行时间。
 * 
 * @author soft01
 *
 */
public class Main {

	public static void main(String[] args) {
		int[] array = new int[10000];
		ForkJoinPool pool = new ForkJoinPool();
		Task task = new Task("Task",array,0,array.length);
		pool.execute(task);
		pool.shutdown();
		try {
			pool.awaitTermination(1, TimeUnit.DAYS);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("end");
	}

}
