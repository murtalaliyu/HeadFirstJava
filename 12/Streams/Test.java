import java.util.List;

public class Test {
  public static void main(String[] args) {
    List<String> allColors = List.of("Red", "Blue", "Yellow");
    // allColors.forEach(color -> System.out.println(color));

    allColors
    .stream()
    .limit(2)
    .sorted((a,b) -> a.compareTo(b))
    .forEach(color -> System.out.println(color));
  }
}
