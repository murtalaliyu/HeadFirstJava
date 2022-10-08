package ryanAndMonicaProblem;

import java.util.concurrent.atomic.AtomicInteger;

public class BankAccountAtomic {
	
	private final AtomicInteger balance = new AtomicInteger(100);	// make an object immutable if you're going to share it between threads and you don't want the threads to change its data
	
	public int getBalance() { return balance.get(); }
	
	public void spend(String name, int amount) {
		System.out.println(name + " is about to spend $" + amount);
		int initialBalance = balance.get();
		if (initialBalance >= amount) {
			boolean success = balance.compareAndSet(initialBalance, initialBalance-amount);
			if (!success) System.out.println("Sorry " + name + ", you haven't spent the money.");
			if (balance.get() < 0) System.out.println("Overdrawn!");
			System.out.println(name + " finishes spending. Account balance is now $" + balance);
		} else {
			System.out.println("Sorry, not enough for " + name);
		}
	}

}
