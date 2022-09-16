public class ExTestDrive {
  
  public static void main(String[] args) {
    String test = args[0];
    System.out.print("t");

    try {

      callDoRisky(test);

    } catch (MyEx e) {
      System.out.print("a");
    } finally {
      System.out.print("w");
      System.out.println("s");
    }
  }

  static void callDoRisky(String t) throws MyEx {
    doRisky(t);
  }

  static void doRisky(String t) throws MyEx {
    System.out.print("h");
    
    if ("yes".equals(t)) {
      throw new MyEx();
    }

    System.out.print("r");
    System.out.print("o");
  }

}
