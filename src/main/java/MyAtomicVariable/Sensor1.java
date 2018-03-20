package MyAtomicVariable;

public class Sensor1 implements Runnable{
	private ParkingCounter counter;
	public Sensor1(ParkingCounter c) {
		this.counter = c;
	}
	public void run() {
		counter.carIn();
		counter.carIn();
		counter.carOut();counter.carOut();counter.carOut();
		counter.carIn();
		counter.carIn();counter.carOut();
		counter.carOut();
		counter.carIn();counter.carOut();
		counter.carIn();
		counter.carIn();counter.carOut();
		counter.carIn();
		counter.carIn();
		counter.carIn();counter.carOut();counter.carOut();counter.carOut();counter.carOut();
		counter.carIn();counter.carOut();counter.carOut();
		counter.carIn();
	}
}
