package myForkJoinTaskForForkJoin;

import java.util.Date;
import java.util.concurrent.ForkJoinTask;

public abstract class MyWorkerTask extends ForkJoinTask<Void>{
	private String name;
	public MyWorkerTask(String name) {
		this.name = name;
	}
	@Override
	public Void getRawResult() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void setRawResult(Void value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected boolean exec() {
		Date startDate = new Date();
		compute();
		Date finishDate = new Date();
		long diff = finishDate.getTime() - startDate.getTime();
		System.out.println("MyWorkerTask:"+name+":"+diff);
		return true;
	}
	public String getName() {
		return name;
	}
	protected abstract void compute();
}
