import java.util.Comparator;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;

public class Lambdas implements InterfaceTest {
  public static void main(String[] args) {
    // new Lambdas().go();
    
    InterfaceTest.a();
  }

  void go() {
    Comparator<String> comparator = (s1, s2) -> s1.compareToIgnoreCase(s2);
    Runnable runnable = () -> System.out.println("Hello");
    Consumer<String> consumer = str -> System.out.println(str);
    Function<String, Integer> f = (String s) -> s.length();
    BiPredicate<Double,Character> bp = (d,c) -> 1 == 2;

    System.out.println(comparator);
  }

  @Override
  public void hi() {
    // TODO Auto-generated method stub
    
  }

  @Override
  public int hello(String s) {
    // TODO Auto-generated method stub
    return 0;
  }
}
