package MyAtomicVariable;
/**
 * ParkingCounter继承自AtomicInteger类，它带有两个原子操作carIn()和
 * carOut（）；
 * 使用compareAndSet()方法尝试使用新值替换旧值，所以计数器值将发生改变，如果
 * 是原子操作，则返回true。不是原子操作的话就不断循环，直至原子操作。
 * @author soft01
 *
 */
public class Main {

	public static void main(String[] args) {

		ParkingCounter counter = new ParkingCounter(5);
		Sensor1 s1 = new Sensor1(counter);
		Sensor2 s2 = new Sensor2(counter);
		Thread thread1 = new Thread(s1);
		Thread thread2 = new Thread(s2);
		
		thread1.start();
		thread2.start();
		
		try {
			thread1.join();
			thread2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Main:the number of car"+counter.get());
	}

}
