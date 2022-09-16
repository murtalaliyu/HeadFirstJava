import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class Jukebox10 {
  public static void main(String[] args) {
    new Jukebox10().go();
  }

  public void go() {
    List<SongV4> songList = MockMoreSongs.getSongs();
    System.out.println("songList as entered:\n" + songList);

    songList.sort((one,two) -> one.getTitle().compareTo(two.getTitle()));
    System.out.println("songList sorted by title asc:\n" + songList);

    songList.sort((one,two) -> two.getBpm() - one.getBpm());
    System.out.println("songList sorted by bpm desc:\n" + songList);

    Set<SongV4> songSet = new TreeSet<>(songList);
    System.out.println("songSet sorted by object's comparable compareTo:\n" + songSet);

    Set<SongV4> songSet2 = new TreeSet<>((a,b) -> a.getBpm() - b.getBpm());
    songSet2.addAll(songList);
    System.out.println("songSet2 sorted by comparator:\n" + songSet2);

    Map<Integer,String> m = Map.of(2, "v1");
    Map<String,Integer> stores = Map.ofEntries(Map.entry("k", 4),
    Map.entry("f", 5));
  }
}
