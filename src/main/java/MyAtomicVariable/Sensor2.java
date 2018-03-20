package MyAtomicVariable;

public class Sensor2 implements Runnable{
	private ParkingCounter counter;
	public Sensor2(ParkingCounter c) {
		this.counter = c;
	}
	public void run() {

		counter.carIn();
		counter.carIn();
		counter.carIn();counter.carOut();counter.carOut();counter.carOut();counter.carOut();
		counter.carIn();
		counter.carIn();
		counter.carIn();
		counter.carIn();counter.carOut();counter.carOut();
		counter.carIn();counter.carOut();
		counter.carIn();
		counter.carIn();counter.carOut();counter.carOut();
		counter.carIn();
		counter.carIn();
		counter.carIn();
		counter.carIn();counter.carOut();counter.carOut();counter.carOut();
		counter.carIn();
		counter.carIn();counter.carOut();
		counter.carIn();
		counter.carOut();
	}
}
