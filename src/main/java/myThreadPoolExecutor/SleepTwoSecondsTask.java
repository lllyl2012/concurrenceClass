package myThreadPoolExecutor;

import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class SleepTwoSecondsTask implements Callable<String>{
	
	public String call() throws Exception {
		TimeUnit.SECONDS.sleep(1);
		return new Date().toString();
	}

}
