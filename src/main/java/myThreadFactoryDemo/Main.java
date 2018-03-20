package myThreadFactoryDemo;
/**
 * 本例实现了一个定制的MyThread类，继承了Thread类。
 * 实现了一个定制的MyThreadFactory类，要实现ThreadFactory接口。
 * 实现定制工厂，方便我们获得创建的统计数据。
 * @author soft01
 *
 */
public class Main {

	public static void main(String[] args) {

		MyThreadFactory factory = new MyThreadFactory("myThreadFactory");
		MyTask task = new MyTask();
		Thread thread = factory.newThread(task);
		thread.start();
		try {
			thread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("end:"+thread);
	}

}
