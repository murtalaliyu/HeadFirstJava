import java.util.Set;
import java.util.TreeSet;

public class TestTree {
  public static void main(String[] args) {
    new TestTree().go();
  }

  public void go() {
    Book b1 = new Book("How Cats Work");
    Book b2 = new Book("Remix your Body");
    Book b3 = new Book("Finding Emo");

    // Set<Book> tree = new TreeSet<>((a,b) -> a.getTitle().compareTo(b.getTitle())); // do this when Book does not implement Comparable
    Set<Book> tree = new TreeSet<>();
    tree.add(b1);
    tree.add(b2);
    tree.add(b3);
    System.out.println(tree);
  }
}
