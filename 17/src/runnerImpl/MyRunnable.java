package runnerImpl;

import java.util.concurrent.TimeUnit;

public class MyRunnable implements Runnable {

	@Override
	public void run() {
		go();
	}
	
	public void go() {
		doMore();
	}
	
	public void doMore() {
		System.out.println(Thread.currentThread().getName() + 
				": top o' the stack");
		Thread.dumpStack();
	}
	
	public static void sleep(int timeUnit) {
		try {
//			Thread.sleep(timeUnit); this or that
			TimeUnit.SECONDS.sleep(timeUnit);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
