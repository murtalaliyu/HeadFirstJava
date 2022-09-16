import java.util.ArrayList;

class Startup {
  private String name;
  private ArrayList<String> locationCells;

  public void setName(String n) {
    name = n;
  }

  public void setLocationCells(ArrayList<String> l) {
    locationCells = l;
  }

  public String checkYourself(String userInput) {
    String result = "miss";
    int index = locationCells.indexOf(userInput);

    if (index >= 0) {
      locationCells.remove(index);
      if (locationCells.isEmpty()) {
        result = "kill";
      } else {
        result = "hit";
      }
    }
    return result;
  }
}
