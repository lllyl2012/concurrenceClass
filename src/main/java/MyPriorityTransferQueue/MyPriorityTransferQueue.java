package MyPriorityTransferQueue;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TransferQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public class MyPriorityTransferQueue<E> extends PriorityBlockingQueue<E> implements TransferQueue<E>{
	/**
	 * 用来储存等待消费元素的消费者的数量
	 */
	private AtomicInteger counter;
	private LinkedBlockingQueue<E> transfered;
	private ReentrantLock lock;
	
	public MyPriorityTransferQueue() {
		this.counter = new AtomicInteger(0);
		this.transfered = new LinkedBlockingQueue<E>();
		this.lock = new ReentrantLock();
	}
	/**
	 * 这个方法尝试立即将元素发送到一个正在等待的消费者，
	 * 如果没有等待中的消费者，该方法返回false。
	 */
	public boolean tryTransfer(E e) {
		lock.lock();
		boolean value;
		if(counter.get() == 0) {
			value = false;
		}else {
			put(e);
			value = true;
		}
		lock.unlock();
		return value;
	}
/**
 * 该方法尝试立即将元素发送到一个正在等待的消费者。
 * 如果没有等待中的消费者，该方法将元素储存到transfered队列，
 * 并等待出现试图获得元素的第一个消费者。在这之前，线程将被阻塞。
 */
	public void transfer(E e) throws InterruptedException {

		lock.lock();
		if(counter.get()!=0) {
			put(e);
			lock.unlock();
		}else {
			transfered.add(e);
			lock.unlock();
			synchronized(e) {
				e.wait();
			}
		}
	}
/**
 * 它接受3个参数：第一个参数用与表示生产和消费的元素，第二个参数表示如果没有消费者
 * 则等待一个消费者的时间，第三个参数表示等待时间的单位。如果有消费者在等待，它就立即发送
 * 元素。否则，将参数指定的时间转换为毫秒并使用wait()方法让线程休眠。当消费者
 * 取元素时，如果线程仍在wait()方法中休眠，将使用notify()方法去唤醒。
 */
	public boolean tryTransfer(E e, long timeout, TimeUnit unit) throws InterruptedException {
		lock.lock();
		if(counter.get()!=0) {
			put(e);
			lock.unlock();
			return true;
		}else {
			transfered.add(e);
			long newTimeout =TimeUnit.MICROSECONDS.convert(timeout, unit);
			lock.unlock();
			if(transfered.contains(e)) {
				transfered.remove(e);
				lock.unlock();
				return false;
			}else {
				lock.unlock();
				return true;
			}
		}
	}

	public boolean hasWaitingConsumer() {
		return (counter.get()!= 0);
	}

	public int getWaitingConsumerCount() {
		return counter.get();
	}

	/**
	 * 该方法将被准备消费元素的消费者调用。首先，获得
	 * 之前定义的锁，然后增加正在等待的消费者的数量。
	 * @throws InterruptedException 
	 */
	public E take() throws InterruptedException {
		lock.lock();
		counter.incrementAndGet();
		/*
		 * 如果在transfered队列中没有元素，则释放锁并、
		 * 尝试使用take()方法从队列中取出一个元素并再次
		 * 获取锁。如果队列中没有元素，该方法将让线程休眠直至有
		 * 元素可被消费。
		 */
		E value = transfered.poll();
		if(value == null) {
			lock.unlock();
			value = super.take();
			lock.lock();
			//否则，从transfered队列中取出value元素，并唤醒可能在等待元素被消费的线程。
		}else {
			synchronized(value) {
				value.notify();
			}
		}
		//减少等待的消费者数量，并释放锁
		counter.decrementAndGet();
		lock.unlock();
		return value;
	}
}
