public class SimpleStartupTestDrive {

  public static void main(String[] args) {

    int numOfGuesses = 0;
    GameHelper helper = new GameHelper();
    Startup theStartup = new Startup();

    int random = (int) (Math.random() * 5);
    int[] locations = {random, random+1, random+2};

    theStartup.setLocationCells(locations);
    boolean isAlive = true;

    while (isAlive) {
      int guess = helper.getUserInput("enter a number");
      String result = theStartup.checkYourself(guess);
      numOfGuesses++;

      if (result.equals("kill")) {
        isAlive = false;
        System.out.println("You took " + numOfGuesses + " guesses");
      }
    }





    // SimpleStartup dot = new SimpleStartup();
    // int[] locations = {2, 3, 4, 5, 6};
    // dot.setLocationCells(locations);
    
    // int userGuess = 5;
    // String result = dot.checkYourself(userGuess);
    
    // String testResult = "failed";
    // if (result.equals("hit")) {
    //   testResult = "passed";
    // }

    // System.out.println(testResult);
  }

}
