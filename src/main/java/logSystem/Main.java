package logSystem;

import java.util.logging.Level;
import java.util.logging.Logger;
/**
 * 日志系统（Log System）是将信息输出到一个或多个目标上的一种机制，它有以下几个组建：
 * 一个或多个处理器：Handler：处理器决定目标和日志消息的格式。可把消息输出到控制台，写入文件，保存到数据库。
 * 一个名称：Name：类中日志记录器的名称基于它的包名和类名；
 * 一个级别：Level：重要性，级别。日志记录器仅输出与它级别相同重要或更重要的消息。
 * @author soft01
 *
 */
public class Main {
	public static void main(String[] args) {
		Logger logger = MyLogger.getLogger("Core");
		//输出一条日志消息表示主程序开始执行
		logger.entering("Core", "main()",args);
		Thread[] threads = new Thread[5];
		for(int i=0;i<threads.length;i++) {
			logger.log(Level.INFO, "Launching thread:"+i);
			Task task = new Task();
			threads[i] = new Thread(task);
			logger.log(Level.INFO, "Thread created:"+threads[i].getName());
			threads[i].start();
		}
		logger.log(Level.INFO, "Ten Threads created."+"Waiting for its finalization");
		for(int i=0;i<threads.length;i++) {
			try {
				threads[i].join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			logger.log(Level.INFO, "Thread has finished its execution",threads[i]);
			logger.exiting("Core", "main()");
		}
	}
}
