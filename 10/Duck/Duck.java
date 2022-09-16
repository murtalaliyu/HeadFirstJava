public class Duck {
  private int size;               // instance variables are unique for every instance of the class
  private static int duckCount;   // static variables are the same for every instance of the class

  // Often (although not always), a class with static methods is not meant to be instantiated. 
  // Static methods are called using Class.method()
  // Static methods are used when the method has no use for instance variables (these methods don't need to know anything about the state of its Object).

  // non-static methods must have their class instantiated before being called using refVar.method()

  public Duck() {
    duckCount++;
  }

  public static void main(String[] args) {
    System.out.println("Size of duck is " + size);    // static methods only work with static (non-instance) variables
    getSize();   // static methods only work with other static methods

    System.out.println("hi");
    // Duck d = new Duck();
    // String[] s = {};
    // d.main(s);         // not a very nice thing to do
  }

  public void setSize(int s) {
    size = s;
  }

  public int getSize() {
    return size;
  }
}
