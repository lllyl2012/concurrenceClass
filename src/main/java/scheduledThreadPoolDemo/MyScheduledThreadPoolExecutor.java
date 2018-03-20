package scheduledThreadPoolDemo;

import java.util.concurrent.RunnableScheduledFuture;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class MyScheduledThreadPoolExecutor extends ScheduledThreadPoolExecutor{
	public MyScheduledThreadPoolExecutor(int corePoolSize) {
		super(corePoolSize);
	}
	/**
	 * 它接收将要被执行的Runnable对象作为参数，另一个参数
	 * 是RunnableScheduledFuture任务，用来执行这个Runnable对象。
	 * 这个方法使用这些对象构造并返回MyScheduledTask任务
	 */
	protected <V> RunnableScheduledFuture<V> decorateTask(Runnable runnable,RunnableScheduledFuture<V> task){
		MyScheduledTask<V> myTask = new MyScheduledTask<V>(runnable,null,task,this);
		return myTask;
	}
	/**
	 * 调用父类的这个方法，把返回对象强制类型转换位一个MyScheduledTask对象，并使用setPeriod()方法设置任务的周期。
	 */
	public ScheduledFuture<?> scheduleAtFixedRate(Runnable command,long initialDelay,long period,TimeUnit unit){
		ScheduledFuture<?> task = super.scheduleAtFixedRate(command, initialDelay, period, unit);
		MyScheduledTask<?> myTask = (MyScheduledTask<?>)task;
		myTask.setPeriod(TimeUnit.MICROSECONDS.convert(period, unit));;
		return task;
	}
}
