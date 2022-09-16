public class Test {
  public static void main(String[] args) {
    Test.go();
  }

  public static void go() {
    SomeClass sc = new SomeClass();
    sc.call((x) -> Integer.parseInt(x));
  }
}
