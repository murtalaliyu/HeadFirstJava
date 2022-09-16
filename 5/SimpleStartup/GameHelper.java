import java.util.Scanner;

class GameHelper {
  public int getUserInput(String prompt) {
    System.out.println(prompt + ": ");
    Scanner sc = new Scanner(System.in);
    return sc.nextInt();
  }  
}
