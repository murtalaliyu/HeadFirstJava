import java.util.List;

public class Jukebox8 {
  public static void main(String[] args) {
    new Jukebox8().go();
  }

  public void go() {
    List<SongV4> songList = MockMoreSongs.getSongs();
    System.out.println("as entered:\n" + songList);

    
  }
}
