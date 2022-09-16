import java.util.List;
import java.util.ArrayList;

public class SortMountains {
  public static void main(String[] args) {
    new SortMountains().go();
  }

  public void go() {
    List<Mountain> mountains = new ArrayList<>();
    mountains.add(new Mountain("Longs", 14255));
    mountains.add(new Mountain("Elbert", 14433));
    mountains.add(new Mountain("Maroon", 14156));
    mountains.add(new Mountain("Castle", 14265));
    System.out.println("as entered:\n" + mountains);

    // sort by name asc
    mountains.sort((a,b) -> a.name.compareTo(b.name));
    System.out.println("by name asc:\n" + mountains);

    // sort by height desc
    mountains.sort((a,b) -> b.height - a.height);
    System.out.println("by height desc:\n" + mountains);
  }  
}
