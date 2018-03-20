package MyPriorityTransferQueue;

public class Consumer implements Runnable{
	private MyPriorityTransferQueue<Event> buffer;
	
	public Consumer(MyPriorityTransferQueue<Event> buffer) {
		this.buffer = buffer;
	}

	public void run() {
		for(int i=0;i<1002;i++) {
			try {
				Event value = buffer.take();
				System.out.println("Consumer:"+"threadName"+value.getThread()+": priority-"+value.getPriority());
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
