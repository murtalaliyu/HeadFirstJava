package playground;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PredictableLatch {
	
	public static void main(String[] args) { 
		new PredictableLatch().go();
	}
	
	private void go() {
		ExecutorService executor = Executors.newSingleThreadExecutor();
		CountDownLatch latch = new CountDownLatch(1);
		
		executor.execute(() -> waitForLatchThenPrint(latch));
		
		System.out.println("back in main");
		latch.countDown();
		
		executor.shutdown();
	}
	
	private void waitForLatchThenPrint(CountDownLatch latch) {
		await(latch);
		System.out.println("top o' the stack");
	}
	
	private void await(CountDownLatch latch) {
		try {
			latch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
