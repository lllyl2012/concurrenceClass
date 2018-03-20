package MypriorityExecutorDemo;

import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
/**
 * 实现基于优先级的Executor类。执行器内部使用一个阻塞式队列
 * 存放等待执行的任务，并按任务到达执行器时的顺序进行存放。
 * 另一个可行的代替方案是使用优先级队列存放新任务。这样，如果有
 * 高优先级的新任务到达执行器，则先被执行。
 * 只需要把PriorityBlockingQueue对象作为其中一个传入参数，
 * 并且要求它的泛型参数是Runnable接口。存放在队列中的所有对象
 * 都要实现Comparable接口.
 * @author soft01
 *
 */
public class Main {

	public static void main(String[] args) {

		ThreadPoolExecutor executor = new ThreadPoolExecutor(2,2,1,TimeUnit.SECONDS,new PriorityBlockingQueue<Runnable>());
		for(int a=0;a<4;a++) {
			MyPriorityTask t = new MyPriorityTask("task"+a,a+1);
			executor.execute(t);
		}
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(int a=4;a<8;a++) {
			MyPriorityTask t = new MyPriorityTask("task"+a,a);
			executor.execute(t);
		}
		executor.shutdown();
		try {
			executor.awaitTermination(1, TimeUnit.DAYS);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("end");
	}

}
