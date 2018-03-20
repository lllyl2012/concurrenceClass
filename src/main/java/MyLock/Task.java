package MyLock;

import java.util.concurrent.TimeUnit;

public class Task implements Runnable{
	private MyLock lock;
	private String name;
	public Task(String name,MyLock lock) {
		this.name = name;
		this.lock  = lock;
	}
	public void run() {
		lock.lock();
		System.out.println("Task:"+name+"take the lock");
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			lock.unlock();
		}
	}
}
