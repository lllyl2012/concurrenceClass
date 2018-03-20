package logSystem;

import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;
/**
 * 为日志消息指定格式
 * @author soft01
 *
 */
public class MyFormatter extends Formatter{

	@Override
	/**
	 * 返回一个带有日志消息的String对象。
	 * @see java.util.logging.Formatter#format(java.util.logging.LogRecord)
	 */
	public String format(LogRecord record) {
		StringBuilder sb = new StringBuilder();
		sb.append("["+record.getLevel()+"]-");//返回消息级别
		sb.append(new Date(record.getMillis())+":");//返回发送消息到Logger对象时的时间
		sb.append(record.getSourceClassName()+"."+record.getSourceMethodName()+":");//返回发送消息到Logger的类和方法的名称
		sb.append(record.getMessage()+"\n");//返回日志消息
		return sb.toString();
	}

}
