public class Format {
  public static void main(String[] args) {
    long myBillion = 1_000_000_000;
    String myBillionStr = String.format("%,d,", myBillion);
    System.out.println(myBillionStr);

    System.out.println(String.format("%,6.1f", 42.000));
    System.out.println(String.format("The rank is %,d out of %,.2f", 20456654, 100567890.239));
  }
}
