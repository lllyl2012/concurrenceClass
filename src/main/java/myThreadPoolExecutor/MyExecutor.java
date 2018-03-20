package myThreadPoolExecutor;

import java.util.Date;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class MyExecutor extends ThreadPoolExecutor{
	private ConcurrentHashMap<String,Date> startTimes;
	public MyExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit,
			BlockingQueue<Runnable> workQueue) {
		super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
		startTimes = new ConcurrentHashMap<String,Date>();
	}
	public void shutdown() {
		System.out.println("MyExecutor:shutdown");
		System.out.println("MyExecutor:Executed tasks:"+getCompletedTaskCount());
		System.out.println("MyExecutor:Running tasks:"+this.getActiveCount());
		System.out.println("MyExecutor:Pending tasks:"+this.getQueue().size());
		super.shutdown();
	}
	public List<Runnable> shutdownNow(){
		System.out.println("MyExecutor:Going to immediately shutdown");
		System.out.println("MyExecutor:Running tasks:"+this.getActiveCount());
		System.out.println("MyExecutor:Executed tasks:"+this.getCompletedTaskCount());
		System.out.println("MyExecutor:Pending tasks:"+this.getQueue().size());
		return super.shutdownNow();
	}
	protected void beforeExecute(Thread t,Runnable r) {
		System.out.println("MyExecutor:A task is begin:"+t.getName()+":"+r.hashCode());
		startTimes.put(String.valueOf(r.hashCode()), new Date());
	}
	protected void afterExecute(Runnable r,Throwable t) {
		Future<?> result = (Future<?>)r;
		System.out.println("*****************************");
		System.out.println("MyExecutor:A task if finishing");
		try {
			System.out.println("MyExecutor:Result:"+result.get());
			Date startDate = startTimes.remove(String.valueOf(r.hashCode()));
			Date finishDate = new Date();
			long diff = finishDate.getTime() - startDate.getTime();
			System.out.println("MyExecutor:Duration:"+diff);
			System.out.println("***************************");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
