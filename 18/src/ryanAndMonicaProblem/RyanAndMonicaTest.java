package ryanAndMonicaProblem;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RyanAndMonicaTest {
	
	public static void main(String[] args) {
		RyanAndMonicaTest start = new RyanAndMonicaTest();
		for (int i = 0; i < 10; i++) {
			start.go();
			System.out.println("-----------------\n");
		}
	}
	
	private void go () {
//		BankAccount account = new BankAccount(100);
		BankAccountAtomic account = new BankAccountAtomic();
		RyanAndMonicaJob ryan = new RyanAndMonicaJob("Ryan", account, 50);
		RyanAndMonicaJob monica = new RyanAndMonicaJob("Monica", account, 100);
		System.out.println("Starting account balance: $" + account.getBalance());
		
		ExecutorService executor = Executors.newFixedThreadPool(2);
		executor.execute(ryan);
		executor.execute(monica);
		executor.shutdown();
		
		try {
			Thread.currentThread();
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Ending account balance: $" + account.getBalance());
	}

}
