package MyPriorityTransferQueue;

public class Producer implements Runnable{
	private MyPriorityTransferQueue<Event> buffer;
	
	public Producer(MyPriorityTransferQueue<Event> buffer) {
		this.buffer = buffer;
	}
	
	public void run() {
		for(int i=0;i<100;i++) {
			Event event =  new Event(Thread.currentThread().getName(),i);
			buffer.put(event);
		}
	}

}
