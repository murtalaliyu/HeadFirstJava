interface InterfaceTest {
  void hi();
  int hello(String s);

  static void a() {
    System.out.println(InterfaceTest.class.getName() + "'s static a()");
  }

  default void b() {
    System.out.println(InterfaceTest.class.getName() + "'s default b()");
  }
}
