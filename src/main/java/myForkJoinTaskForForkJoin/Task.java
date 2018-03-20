package myForkJoinTaskForForkJoin;

public class Task extends MyWorkerTask{
	private int[] array;
	private int start;
	private int end;
	public Task(String name,int[] array,int start,int end) {
		super(name);
		this.array = array;
		this.start = start;
		this.end = end;
	}
	protected void compute() {
		if(end - start>100) {
			int middle = (end+start)/2;
			Task task1 = new Task(this.getName()+"1",array,start,middle);
			Task task2 = new Task(this.getName()+"2",array,middle,end);
			invokeAll(task1,task2);
		}else {
			for(int a =start;a<end;a++) {
				array[a]++;
			}
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
