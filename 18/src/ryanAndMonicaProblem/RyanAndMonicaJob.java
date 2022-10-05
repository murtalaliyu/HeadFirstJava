package ryanAndMonicaProblem;

public class RyanAndMonicaJob implements Runnable {
	
	private final BankAccount account;
	private final String name;
	private final int amountToSpend;
	
	RyanAndMonicaJob(String name, BankAccount account, int amountToSpend) {
		this.account = account;
		this.name = name;
		this.amountToSpend = amountToSpend;
	}

	@Override
	public void run() {
		// Auto-generated method stub
		goShopping(amountToSpend);
	}
	
	private void goShopping(int amount) {
		/**
		 * The "synchronized" keyword means that a thread needs a key in order to access the synchronized code.
		 * To protect your data (like the bank account), synchronize the code that acts on that data.
		 * 
		 * Use the "synchronized" keyword on a method, or with an object, to lock an object so only one thread
		 * can use it at a time.
		 * 
		 * That's how you protect the bank account. We can put a lock on the bank account inside the method that
		 * does the banking transaction. That way, one thread gets to complete the whole transaction, start to finish,
		 * even if that thread is taken out of the "running" state by the thread scheduler or another thread is trying
		 * to make changes at exactly the same time.
		 */
		synchronized(account) {
			if (account.getBalance() >= amount) {
				System.out.println(name + " is about to spend $" + amount);
				account.spend(amount);
				System.out.println(name + " finishes spending. Account balance is now $" + account.getBalance());
			} else {
				System.out.println("Sorry, $" + account.getBalance() + " not enough money for " + name + " to spend $" + amount);
			}
		}
	}

}
