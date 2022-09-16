public class Player {
  static int playerCount;
  private String name;

  static {
    playerCount = 100;
    System.out.println("Init Player class. This method runs as soon as the class is loaded, before any static method is called and even before any static variable can be used. playerCount: " + playerCount);
  }

  public Player(String n) {
    name = n;
    playerCount++;
  }
}
