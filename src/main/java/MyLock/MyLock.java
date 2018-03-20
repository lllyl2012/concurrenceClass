package MyLock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class MyLock implements Lock{
	private AbstractQueuedSynchronizer syn;
	public MyLock() {
		syn = new MyAbstractQueuedSynchronizer();
	}
	public void lock() {
		// TODO Auto-generated method stub
		syn.acquire(1);
	}

	public void lockInterruptibly() throws InterruptedException {

		syn.acquireInterruptibly(1);
	}

	public boolean tryLock() {
		try {
			return syn.tryAcquireNanos(1, 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
		return syn.tryAcquireNanos(1, TimeUnit.NANOSECONDS.convert(time, unit));
	}

	public void unlock() {
		syn.release(1);
	}

	public Condition newCondition() {
		return syn.new ConditionObject();
	}
	
}
