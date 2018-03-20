package logSystem;

import java.util.logging.Logger;

public class Task implements Runnable{
	public void run() {
		Logger logger = MyLogger.getLogger(this.getClass().getName());
		logger.entering(Thread.currentThread().getName(),"run()");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.exiting(Thread.currentThread().getName(),"run()", Thread.currentThread());
	}
	
}
