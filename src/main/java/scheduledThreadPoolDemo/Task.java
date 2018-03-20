package scheduledThreadPoolDemo;

import java.util.concurrent.TimeUnit;

public class Task implements Runnable{
	public void run() {
		System.out.println("Task:Begin");
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Task:end");
	}
}
