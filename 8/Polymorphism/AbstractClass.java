public abstract class AbstractClass extends AnotherAbstractClass {
  private int x = 1;
  int getX() { return x; }
  void setX(int x) { this.x = x; }
  
  public void a() {System.out.println("AC impl of a");}
  // abstract void b();
  public void c() {System.out.println("AC impl of c");}
}
