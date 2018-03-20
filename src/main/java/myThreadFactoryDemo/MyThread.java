package myThreadFactoryDemo;

import java.util.Date;

public class MyThread extends Thread{
	private Date creationDate;
	private Date startDate;
	private Date finishDate;
	public MyThread(Runnable target,String name) {
		super(target,name);
		creationDate = new Date();
	}
	public void run() {
		startDate = new Date();
		super.run();
		finishDate = new Date();
	}
	public long getExecutionTime() {
		return finishDate.getTime() - startDate.getTime();
	}
	public String toString() {
		return this.getName()+":"+this.getExecutionTime();
	}
}
