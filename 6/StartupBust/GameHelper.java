import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

class GameHelper {
  private static final String ALPHABET = "abcdefg";
  private static final int GRID_LENGTH = 7;
  private static final int GRID_SIZE = 49;
  private static final int MAX_ATTEMPTS = 200;
  
  static final int HORIZONTAL_INCREMENT = 1;            // A better way to represent these two
  static final int VERTICAL_INCREMENT = GRID_LENGTH;    // things is an enum (see Appendix B)

  private final int[] grid = new int[GRID_LENGTH];
  private final Random random = new Random();
  private int startupCount = 0;

  public String getUserInput(String prompt) {
    System.out.print(prompt + ": ");
    Scanner scanner = new Scanner(System.in);
    return scanner.nextLine().toLowerCase();
  }

  public ArrayList<String> placeStartup(int startupSize) {
    
  }
}
