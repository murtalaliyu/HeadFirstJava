package playground;

import java.util.concurrent.ExecutorService;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class PredictableSleep {
	
	public static void main(String[] args) {
		PredictableSleep.go();
	}
	
	private static void go() {
		ExecutorService executor = Executors.newSingleThreadExecutor();
		executor.execute(() -> sleepThenPrint(2));
		System.out.println("back in main");
		executor.shutdown();
	}
	
	private static void sleepThenPrint(int timeUnit) {
		try {
			TimeUnit.SECONDS.sleep(timeUnit);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("top o' the stack");
	}

}
