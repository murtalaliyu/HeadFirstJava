package playground;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class ExecutorTestDrive {
	
	public static void main(String[] args) {
		for (int i = 0; i < 100; i++) {
			ExecutorTestDrive.go(i);
		}
	}
	
	private static void go(int i) {
		ExecutorService executor = 
				Executors.newSingleThreadExecutor();
		executor.execute(() -> System.out.println(Thread.currentThread().getName() 
				+ ": top o' the stack at " + i));
		System.out.println("back in main at " + i);
		executor.shutdown();
	}

}
