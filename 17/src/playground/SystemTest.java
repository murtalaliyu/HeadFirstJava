package playground;

public class SystemTest {
	
	public static void main(String[] args) {
		new SystemTest().go();
	}
	
	private void go() {
		System.out.println("system env: " + System.getenv() + "\n");
		System.out.println("system security manager: " + System.getSecurityManager() + "\n");
		System.out.println("runtime: " + Runtime.getRuntime());
	}

}
