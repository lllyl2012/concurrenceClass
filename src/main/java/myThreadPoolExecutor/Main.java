package myThreadPoolExecutor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;
/**
 * 覆盖ThreadPoolExecutor类，通过范例来计算任务在
 * 执行器中执行的时间，执行结束后在控制台输出有关执行器的
 * 统计信息。
 * @author soft01
 *
 */
public class Main {
	public static void main(String[] args) {
		MyExecutor myExecutor = new MyExecutor(2,4,1000,TimeUnit.MICROSECONDS,new LinkedBlockingDeque<Runnable>());
		List<Future<String>> results = new ArrayList<Future<String>>();
		for(int a=0;a<10;a++) {
			SleepTwoSecondsTask task = new SleepTwoSecondsTask();
			Future<String> result = myExecutor.submit(task);
			results.add(result);
		}
		
		for(int a=0;a<5;a++) {
			String result = null;
			try {
				result = results.get(a).get();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Main:Result for Task "+a+":"+result);
		}
		myExecutor.shutdown();
		
		for(int a=5;a<10;a++) {
			try {
				String result=results.get(a).get();
				System.out.println("Main:Result for Task"+a+":"+result);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		try {
			myExecutor.awaitTermination(1, TimeUnit.DAYS);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Main:End of the program");
	}
	
}
