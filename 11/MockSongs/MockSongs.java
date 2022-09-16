import java.util.ArrayList;
import java.util.List;

class MockSongs {
  public static List<String> getSongStrings() {
    List<String> songs = new ArrayList<>();
    songs.add("somersault");
    songs.add("cassidy");
    songs.add("$10");
    songs.add("havana");
    songs.add("cassidy");
    songs.add("50 ways");
    return songs;
  }

  public static List<Songv2> getSongsV2() {
    List<Songv2> songs = new ArrayList<>();
    songs.add(new Songv2("somersault", "zero 7", 147));
    songs.add(new Songv2("cassidy", "grateful dead", 158));
    songs.add(new Songv2("$10", "hitchhiker", 140));
    
    songs.add(new Songv2("havana", "cabello", 105));
    songs.add(new Songv2("cassidy", "grateful dead", 158));
    songs.add(new Songv2("50 ways", "simon", 102));
    return songs;
  }
}
