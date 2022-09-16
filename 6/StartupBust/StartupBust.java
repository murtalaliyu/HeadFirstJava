import java.util.ArrayList;

public class StartupBust {
  private GameHelper helper = new GameHelper();
  private ArrayList<Startup> startups = new ArrayList<>();
  private int numOfGuesses = 0;

  private void setUpGame() {
    // first make some Startups and give them locations
    Startup s1 = new Startup();
    s1.setName = "poniez";
    startups.add(s1);

    Startup s2 = new Startup();
    s2.setName = "hacqi";
    startups.add(s2);

    Startup s3 = new Startup();
    s3.setName = "cabista";
    startups.add(s3);

    System.out.println("Your goal is to sink three Startups.");
    System.out.println("poniez, hacqi, cabista");
    System.out.println("Try to sink them all in the fewest number of guesses");

    for (Startup s : startups) {
      ArrayList<String> location = helper.placeStartup(3);
      s.setLocationCells(location);
    }
  }

  private void startPlaying() {
    while (!startups.isEmpty()) {
      String userGuess = helper.getUserInput("Enter a guess");
      checkUserGuess(userGuess);
    }
    finishGame();
  }

  private void checkUserGuess(String userGuess) {
    numOfGuesses++;
    String result = "miss";

    for (Startup s : startups) {
      result = s.checkYourself(userGuess);
      if (result.equals("hit")) {
        break;
      }
      if (result.equals("kill")) {
        startups.remove(s);
        break;
      }
    }
    System.out.println(result);
  }

  private void finishGame() {
    System.out.println("All Startups are dead! Your stock is now worthless");
    if (numOfGuesses <= 18) {
      System.out.println("It only took you " + numOfGuesses + " guesses.");
      System.out.println("You got out before your options sank.");
    } else {
      System.out.println("Took you long enough. " + numOfGuesses + " guesses");
      System.out.println("Fish are dancing with your options");
    }
  }

  public static void main(String[] args) {
    StartupBust game = new StartupBust();
    game.setUpGame();
    game.startPlaying();
  }
}
