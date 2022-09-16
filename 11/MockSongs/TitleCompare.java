import java.util.Comparator;

public class TitleCompare implements Comparator<Songv2> {

  @Override
  public int compare(Songv2 o1, Songv2 o2) {
    return o1.getTitle().compareTo(o2.getTitle());
  }

} 
