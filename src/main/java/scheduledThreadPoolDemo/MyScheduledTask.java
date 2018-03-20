package scheduledThreadPoolDemo;

import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.Delayed;
import java.util.concurrent.FutureTask;
import java.util.concurrent.RunnableScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class MyScheduledTask<V> extends FutureTask<V> implements RunnableScheduledFuture<V> {
	private RunnableScheduledFuture<V> task;
	private ScheduledThreadPoolExecutor executor;
	private long period;
	private long startDate;
	public MyScheduledTask(Runnable runnable,V result,
		RunnableScheduledFuture<V> task,ScheduledThreadPoolExecutor executor) {
		super(runnable,result);
		this.task = task;
		this.executor = executor;
		// TODO Auto-generated constructor stub
	}
/**
 * 如果是周期性任务且startDate属性值不等于0，则计算
 * startDate属性和当前时间的时间差作为返回值。否则
 * 返回存放在task属性中的延迟值。
 */
	public long getDelay(TimeUnit unit) {
		if(!isPeriodic()) {
			return task.getDelay(unit);
		}else {
			if(startDate == 0) {
				return task.getDelay(unit);
			}else {
				Date now = new Date();
				long delay = startDate - now.getTime();
				return unit.convert(delay, TimeUnit.MICROSECONDS);
			}
		}
	}
	
	public int compareTo(Delayed o) {
		return task.compareTo(o);
	}



	public boolean isPeriodic() {
		// TODO Auto-generated method stub
		return task.isPeriodic();
	}
	/**
	 * 如果是周期性任务，则需要用任务下一次执行的开始时间更新
	 * 它的startDate属性，即用当前时间加上周期间隔作为下一次
	 * 执行的开始时间。然后，再次增加任务到ScheduledThreadPoolExecutor对象的队列中。
	 */
	public void run() {
		if(this.isPeriodic() && (!executor.isShutdown())) {
			Date now = new Date();
			startDate = now.getTime()+period;
			executor.getQueue().add(this);
		}
		System.out.println("Pre-MyScheduledTask:"+new Date());
		System.out.println("is Periodic:"+this.isPeriodic());
		super.runAndReset();
		System.out.println("Post-MyScheduledTask:"+new Date());
	}
	
	public void setPeriod(long period) {
		this.period = period;
	}
}
