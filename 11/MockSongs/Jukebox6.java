import java.util.List;

public class Jukebox6 {
  public static void main(String[] args) {
    new Jukebox6().go();
  }

  public void go() {
    // unsorted
    List<Songv2> songList = MockSongs.getSongsV2();
    System.out.println("unsorted:\n" + songList);

    // sort by title
    songList.sort((a,b) -> a.getTitle().compareTo(b.getTitle()));
    System.out.println("sort by title:\n" + songList);

    // sort by artist
    songList.sort((a,b) -> a.getArtist().compareTo(b.getArtist()));
    System.out.println("sort by artist:\n" + songList);

    // sort by bpm asc
    songList.sort((a,b) -> a.getBpm() - b.getBpm());
    System.out.println("sort by bpm:\n" + songList);

    // sort title desc
    songList.sort((a,b) -> b.getTitle().compareTo(a.getTitle()));
    System.out.println("sort by title desc:\n" + songList);
  }
}
