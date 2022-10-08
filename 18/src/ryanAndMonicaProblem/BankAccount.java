package ryanAndMonicaProblem;

public class BankAccount {
	
	private int balance;
	
	BankAccount(int balance) { this.balance = balance; }
	
	int getBalance() { return balance; }
	
	// Locks the BankAccount instance the two threads are using
	public synchronized void spend(String name, int amount) {
		System.out.println(name + " is about to spend $" + amount);
		if (balance >= amount) {
			balance -= amount;
			if (balance < 0) System.out.println("Overdrawn!");
			System.out.println(name + " finishes spending. Account balance is now $" + balance);
		} else System.out.println("Sorry, $" + balance + " not enough money for " + name + " to spend $" + amount);
	}

}
