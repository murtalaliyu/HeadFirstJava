package runnerImpl;

/**
 * How we used to launch a new thread
 * @author murta
 *
 */
public class ThreadTester {
	
	public static void main(String[] args) {
		Runnable threadJob = new MyRunnable();
		
		Thread myThread = new Thread(threadJob);
		myThread.start();
		
		System.out.println(Thread.currentThread().getName() +
				": back in main");
		Thread.dumpStack();
	}

}
