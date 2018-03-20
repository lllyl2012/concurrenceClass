package MyAtomicVariable;

import java.util.concurrent.atomic.AtomicInteger;

public class ParkingCounter extends AtomicInteger{
	private int maxNumber;

	public ParkingCounter(int maxNumber) {
		set(0);
		this.maxNumber = maxNumber;
	}
	public boolean carIn() {
		for(;;) {
			int value = get();
			if(value == maxNumber) {
				System.out.println("ParkingCounter:The parking lot is full");
				return false;
			}else {
				int newValue = value + 1;
				boolean changed =compareAndSet(value,newValue);
				if(changed) {
					System.out.println("ParkingCounter:A car has entered");
					return true;
				}
			}
		}
	}
	public boolean carOut() {
		for(;;) {
			int value = get();
			if(value == 0) {
				System.out.println("ParkingCounter:THe parking lot is empty");
				return false;
			}else {
				int newValue = value - 1;
				boolean changed = compareAndSet(value,newValue);
				if(changed) {
					System.out.println("ParkingCounter:A car has gone out");
					return true;
				}
			}
		}
	}
}
