package lostUpdate;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MethodSync {
	public static void main(String[] args) {
		for (int i = 0; i < 100; i++) go();
	}
	
	private static void go() {
		ExecutorService pool = Executors.newFixedThreadPool(6);
		
		BalanceMS balance = new BalanceMS();
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

class BalanceMS {
	private int balance;
	public int getBalance() { return balance; }
	public synchronized void increment() { balance++; } // balance++ is not atomic, therefore we synchronize its method
}
