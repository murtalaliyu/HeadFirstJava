package playground;

public class PlayImpl implements PlayTest {
	
	private static int one;

	@Override
	public synchronized void hey() {
		// Auto-generated method stub
	}

	// The method staticSync() of type PlayImpl must override or implement a supertype method
	@Override
	public /*static*/ synchronized int staticSync() {
		// Auto-generated method stub
		return one;
	}
	
	public static void me() {
	}

}
