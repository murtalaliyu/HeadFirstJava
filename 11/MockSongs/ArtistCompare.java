import java.util.Comparator;

public class ArtistCompare implements Comparator<Songv2> {
  public int compare(Songv2 a, Songv2 b) {
    return a.getArtist().compareTo(b.getArtist());
  }
}
