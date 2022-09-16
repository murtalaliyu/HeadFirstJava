import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LimitWithStream {
  public static void main(String[] args) {
    List<String> strings = List.of("I", "am", "a", "list", "of", "Strings");
    Stream<String> stream = strings.stream();

    List<String> result = stream
                          .sorted((a,b) -> a.compareToIgnoreCase(b))
                          .skip(2)
                          .limit(4)
                          .collect(Collectors.toList());
    System.out.println("result = " + result);

    // List<String> r2 = stream.collect(Collectors.toList());
    // System.out.println("r2: " + r2);  // Exception! Cannot reuse a stream

    System.out.println("strings: " + strings);
  }
}
