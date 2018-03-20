package MyPriorityTransferQueue;
/**
 * 开发一个生存者/消费者程序，数据是按某种优先级被消费。
 * MyPriorityTransferQueue继承了PriorityBlockingQueue类，
 * 实现了TransferQueue接口。
 * 由于不能实现多继承，所以用TransferQueue接口代替了LinkedTransferQueue类，
 * LinkedTransferQueue类适用与生产-消费者结构的程序。这是一个阻塞无界队列。
 * @author soft01
 *
 */
public class Main {

	public static void main(String[] args) throws InterruptedException {
		MyPriorityTransferQueue<Event> buffer = new MyPriorityTransferQueue<Event>();
		
		Producer producer = new Producer(buffer);
		Thread[] threads = new Thread[10];
		for(int i =0;i<threads.length;i++) {
			threads[i] = new Thread(producer);
			threads[i].start();
		}
		Consumer consumer = new Consumer(buffer);
		Thread consumerThread = new Thread(consumer);
		consumerThread.start();
		System.out.println("Main:Consumer count"+buffer.getWaitingConsumerCount());
		Event myEvent = new Event("Core Event",0);
		buffer.transfer(myEvent);
		System.out.println("Main:My Event has been transfered");
		
		for(int i=0;i<threads.length;i++) {
			threads[i].join();
		}
		
		Thread.sleep(1000);
		
		System.out.println("Main:Consumer count"+buffer.getWaitingConsumerCount());;
	
		myEvent = new Event("Core event 2",0);
		
		buffer.transfer(myEvent);
		consumerThread.join();
		System.out.println("end");
	}

}
