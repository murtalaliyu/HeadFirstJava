import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class MockMoreSongs {
  public static List<SongV4> getSongs() {
    List<SongV4> songs = new ArrayList<>();
    songs.add(new SongV4("somersault", "zero 7", 147));
    songs.add(new SongV4("cassidy", "grateful dead", 158));
    songs.add(new SongV4("$10", "hitchhiker", 140));
    songs.add(new SongV4("havana", "cabello", 105));
    songs.add(new SongV4("$10", "hitchhiker", 140));
    songs.add(new SongV4("cassidy", "grateful dead", 158));
    songs.add(new SongV4("50 ways", "simon", 102));
    return Collections.unmodifiableList(songs);
    // return songs;
  }
}
