package lostUpdate;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicVariable {
	public static void main(String[] args) {
		for (int i = 0; i < 100; i++) go();
	}
	
	private static void go() {
		ExecutorService pool = Executors.newFixedThreadPool(6);
		
		BalanceAV balance = new BalanceAV();
		for (int i = 0; i < 1000; i++) {
			pool.execute(() -> balance.increment()); 
//			balance.increment();	// this will run in only one thread so is always correct
		}
		pool.shutdown();
		try {
			if (pool.awaitTermination(1, TimeUnit.MINUTES)) System.out.println("balance = " + balance.getBalance());
		} catch (InterruptedException e) { e.printStackTrace(); }
	}
}

class BalanceAV {
	private AtomicInteger balance = new AtomicInteger(0);
	public AtomicInteger getBalance() { return balance; }
	public void increment() { balance.incrementAndGet(); }
}
