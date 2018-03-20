package MypriorityExecutorDemo;

import java.util.concurrent.TimeUnit;

public class MyPriorityTask implements Runnable,Comparable<MyPriorityTask>{
	private int priority;
	private String name;
	public MyPriorityTask(String n,int p) {
		this.priority = p;
		this.name = n;
	}
	public int getPriority() {
		return this.priority;
	}
	public int compareTo(MyPriorityTask o) {
		if(this.priority < o.getPriority()) {
			return 1;
		}
		if(this.priority > o.getPriority()) {
			return -1;
		}
		return 0;
	}

	public void run() {

		System.out.println("MyPriority:"+name+"+"+this.priority);
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
