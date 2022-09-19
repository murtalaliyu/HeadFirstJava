import java.io.Serializable;

public class TestSerialver implements Serializable {
	private int a;
	private transient int b;

	public TestSerialver(int a, int b) {
		this.a = a;
		this.b = b;
	}

	public int getA() { return a; }
	public int getB() { return b; }

}
