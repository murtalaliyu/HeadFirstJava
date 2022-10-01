package playground;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PredictableLatch {
	
	private static int i = 0;
	
	public static void main(String[] args) { 
		go();
	}
	
	private static void go() {
		ExecutorService executor = Executors.newSingleThreadExecutor();
		CountDownLatch latch = new CountDownLatch(1);
		
		executor.execute(() -> waitForLatchThenPrint(latch));
		
		System.out.println("back in main at " + i);
		latch.countDown();
		
		executor.shutdown();
	}
	
	private static void waitForLatchThenPrint(CountDownLatch latch) {
		await(latch);
		System.out.println("top o' the stack at " + i);
	}
	
	private static void await(CountDownLatch latch) {
		try {
			latch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
