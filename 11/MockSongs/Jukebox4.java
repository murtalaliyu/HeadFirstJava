import java.util.Collections;
import java.util.List;

public class Jukebox4 {
  public static void main(String[] args) {
    new Jukebox4().go();
  }

  public void go() {
    List<Songv2> songList = MockSongs.getSongsV2();
    System.out.println("unsorted:\n" + songList + "\n");

    Collections.sort(songList);
    System.out.println("sort by title:\n" + songList + "\n");

    ArtistCompare ac = new ArtistCompare();
    songList.sort(ac);
    System.out.println("sort by artist:\n" + songList + "\n");
  }
}
