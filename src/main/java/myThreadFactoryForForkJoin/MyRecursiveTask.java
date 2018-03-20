package myThreadFactoryForForkJoin;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.TimeUnit;

public class MyRecursiveTask extends RecursiveTask<Integer>{
	private int array[];
	private int start,end;
	public MyRecursiveTask(int array[],int start,int end) {
		this.array = array;
		this.start = start;
		this.end = end;
	}
	
	protected Integer compute() {
		Integer ret = 0;
		MyWorkerThread thread = (MyWorkerThread)Thread.currentThread();
		thread.addTask();
		if(array.length<100) {
			for(int a=start;a<end;a++) {
				ret += array[a];
			}
		}else {
			int middle  = (start + end)/2;
			MyRecursiveTask t = new MyRecursiveTask(array,start,middle);
			MyRecursiveTask t2 = new MyRecursiveTask(array,middle,end);
			t.fork();
			t2.fork();
			ret = addResults(t,t2);
		}
		return ret;
	}
	
	private Integer addResults(MyRecursiveTask t1,MyRecursiveTask t2 ) {
		int value;
		try {
			System.out.println(t1.get().getClass().getName());
			value = t1.get().intValue()+t2.get().intValue();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			value=0;
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			value = 0;
		}
		try {
			TimeUnit.MILLISECONDS.sleep(10);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return value;
	}
}
