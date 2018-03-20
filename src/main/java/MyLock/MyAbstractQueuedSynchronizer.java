package MyLock;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;

public class MyAbstractQueuedSynchronizer extends AbstractQueuedSynchronizer{
	private AtomicInteger state;
	public MyAbstractQueuedSynchronizer() {
		state = new AtomicInteger(0);
	}
	protected boolean tryAcquire(int arg) {
		return state.compareAndSet(0, 1);//把0换乘1，如果失败返回false
	}
	
	protected boolean tryRelease(int arg) {
		return state.compareAndSet(1, 0);
	}
}
