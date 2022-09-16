public class TestBox {
  private Integer i;
  private int j;

  public static void main(String[] args) {
    TestBox t = new TestBox();
    t.go();

    // Construct c1 = new Construct(7);
    // System.out.println("c1: " + c1.getI());

    // Construct c2 = new Construct(3);
    // System.out.println("c2: " + c2.getI());
  }

  public void go() {
    j = i;  // NullPointerException (wrappers must be initialized)
    System.out.println("j: " + j);
    System.out.println("i: " + i);
  }
}
