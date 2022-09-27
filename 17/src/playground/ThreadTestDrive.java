package playground;

public class ThreadTestDrive {
	
	public static void main(String[] args) {
		for (int i = 0; i < 100; i++) {
			ThreadTestDrive.go(i);
		}
	}
	
	private static void go(int i) {
		Thread myThread = new Thread(() -> System.out.println("top o' the stack at " + i));
		myThread.start();
		System.out.println("back in main at " + i);
	}

}
