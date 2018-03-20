package MyPriorityTransferQueue;

public class Event implements Comparable<Event>{
	private String thread;//储存线程名称
	private int priority;//储存优先级
	
	public Event(String thread, int priority) {
		this.thread = thread;
		this.priority = priority;
	}
	public String getThread() {
		return thread;
	}
	
	public int getPriority() {
		return priority;
	}
	public int compareTo(Event o) {
		if(o.getPriority()>this.priority) {
			return 1;
		}else if(o.getPriority() < this.priority){
			return -1;
		}
		return 0;
	}

}
