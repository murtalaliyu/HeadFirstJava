import java.util.List;

public class Jukebox5 {
  public static void main(String[] args) {
    new Jukebox5().go();
  }

  public void go() {
    // make a list of Songs
    List<Songv2> songList = MockSongs.getSongsV2();
    System.out.println("unsorted:\n" + songList);

    // compare by title
    TitleCompare titleComparator = new TitleCompare();
    songList.sort(titleComparator);
    System.out.println("sort by title:\n" + songList);

    // compare by artist
    ArtistCompare artistComparator = new ArtistCompare();
    songList.sort(new ArtistCompare() {
      public int compare(Songv2 a, Songv2 b) {
        return a.getArtist().compareTo(b.getArtist());
      }
    });
    System.out.println("sort by artist:\n" + songList);

    // compare by title (again)
    songList.sort(((a, b) -> a.getTitle().compareTo(b.getTitle())));
    System.out.println("sort by title:\n" + songList);
  }
}
