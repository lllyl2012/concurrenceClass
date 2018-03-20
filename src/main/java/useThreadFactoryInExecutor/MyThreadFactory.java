package useThreadFactoryInExecutor;

import java.util.concurrent.ThreadFactory;

public class MyThreadFactory implements ThreadFactory{
	private int counter;
	private String prefix;
	public MyThreadFactory(String p) {
		this.prefix = p;
		counter = 1;
	}
	public Thread newThread(Runnable r) {
		MyThread myThread = new MyThread(r,prefix+"-"+counter);
		counter++;
		return myThread;
	}

}
