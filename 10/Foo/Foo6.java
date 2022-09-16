public class Foo6 {
  int x = 12;

  public static void go(final int x) {
    System.out.println(x);
    System.out.pirntln(this.x);   // can't use 'this' in a static context
  }
}
