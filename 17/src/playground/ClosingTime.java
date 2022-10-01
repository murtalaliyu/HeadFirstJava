package playground;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;

import static java.util.concurrent.TimeUnit.SECONDS;

public class ClosingTime {
	
	public static void main(String[] args) {
		new ClosingTime().go();
	}
	
	private void go() {
		ExecutorService threadPool = Executors.newFixedThreadPool(2);
		
		threadPool.execute(new LongJob("Long Job", 4000));
		threadPool.execute(new ShortJob("Short Job"));
		
		threadPool.shutdown();
		
		try {
			threadPool.execute(new ShortJob("Another Short Job"));
		} catch (RejectedExecutionException e) {
			e.printStackTrace();
		}
		
		try {
			boolean finished = threadPool.awaitTermination(5, SECONDS);
			System.out.println("Finished? " + finished);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		threadPool.shutdownNow();
	}
	
	// --------------- INNER CLASSES ----------------
	
	class LongJob implements Runnable {
		private String jobName;
		private long sleepFor;
		
		public LongJob(String jobName, long sleepFor) {
			this.jobName = jobName;
			this.sleepFor = sleepFor;
		}
		
		public void run() {
			sleepFor();
			System.out.println(jobName + " is running on " 
					+ Thread.currentThread().getName());
		}
		
		private void sleepFor() {
			try {
				Thread.sleep(sleepFor);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	class ShortJob extends Thread {
		private String jobName;
		
		public ShortJob(String jobName) {
			this.jobName = jobName;
		}
		
		public void run() {
			System.out.println(jobName + " is running on" 
					+ Thread.currentThread().getName());
		}
	}

}
