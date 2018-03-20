package MyLock;

import java.util.concurrent.TimeUnit;
/**
 * AbstractQueuedSynchronizer类提供操作来对临界区
 * 代码的访问进行控制。并对等待访问临界区代码的阻塞线程队列
 * 进行管理，它有两个方法：
 * tryAcquire（）：当访问临界区代码时调用这个方法。如果访问成功，返回true值，否则返回false:
 * tryRelease():当释放对临界区代码的访问时调用这个方法。如果释放成功，返回true;
 * 
 * @author soft01
 *
 */
public class Main {
	public static void main(String[] args) {
		MyLock lock = new MyLock();
		for(int i=0;i<10;i++) {
			Task task = new Task("Task"+i,lock);
			Thread t = new Thread(task);
			t.start();
		}
		boolean value;
		do {
			try {
				value = lock.tryLock(1,TimeUnit.SECONDS);
				if(!value) {
					System.out.println("Main:Trying to get the Lock");
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				value = false;
			}
		}while(!value);
		System.out.println("Main:got the lock");
		lock.unlock();
		System.out.println("end");
	}
	
}
