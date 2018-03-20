package myThreadFactoryForForkJoin;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinWorkerThread;

public class MyWorkerThread extends ForkJoinWorkerThread{
	private static ThreadLocal<Integer> taskCounter = new ThreadLocal<Integer>();
	protected MyWorkerThread(ForkJoinPool pool) {
		super(pool);
	}
	protected void onStart() {
		super.onStart();
		System.out.println("MyWorkerThread:start"+this.getId());
		taskCounter.set(0);
	}
	protected void onTermination(Throwable exception) {
		System.out.println("MyWorkerThread:end"+this.getId()+":"+taskCounter.get());
		super.onTermination(exception);
	}
	public void addTask() {
		int counter = taskCounter.get().intValue();
		counter++;
		taskCounter.set(counter);
	}
}
