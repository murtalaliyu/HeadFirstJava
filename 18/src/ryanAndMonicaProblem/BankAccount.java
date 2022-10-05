package ryanAndMonicaProblem;

public class BankAccount {
	
	private int balance;
	
	BankAccount(int balance) { this.balance = balance; }
	
	int getBalance() { return balance; }
	
	void spend(int amount) {
		balance -= amount;
		if (balance < 0) {
			System.out.println("Overdrawn!");
		}
	}

}
