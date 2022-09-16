import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class JukeboxStreams {

  public static void main(String[] args) {
    JukeboxStreams m = new JukeboxStreams();
    List<Song> songs = new Songs().getSongs();

    List<Song> songsOfRockGenre = m.getSongsOfRockGenre(songs);
    System.out.println("\nSongs of the 'Rock' genre (" + songsOfRockGenre.size() + "):\n" + songsOfRockGenre);

    List<Song> songsContainingRockGenre = m.getSongsContainingRockGenre(songs);
    System.out.println("\nSongs containing the 'Rock' genre (" + songsContainingRockGenre.size() + "):\n" + songsContainingRockGenre);

    List<Song> songsByTheBeatles = m.getSongsByTheBeatles(songs);
    System.out.println("\nSongs by The Beatles (" + songsByTheBeatles.size() + "):\n" + songsByTheBeatles);

    List<Song> songsThatStartWithH = m.getSongsThatStartWithH(songs);
    System.out.println("\nSongs that start with 'H' (" + songsThatStartWithH.size() + "):\n" + songsThatStartWithH);

    List<Song> songsMoreRecentThan1995 = m.getSongsMoreRecentThan1995(songs);
    System.out.println("\nSongs more recent than 1995 (" + songsMoreRecentThan1995.size() + "):\n" + songsMoreRecentThan1995);

    Set<String> genres = m.getAllGenres(songs);
    System.out.println("\nAll genres (" + genres.size() + "):\n" + genres);

    String songTitle = "Immigrant Song";
    String songArtist = "Karen O";
    List<String> artistsThatHaveCoveredSong = m.getArtistsThatHaveCoveredSong(songs, songTitle, songArtist);
    System.out.println("\nArtists that have covered '" + songTitle + "':\n" + artistsThatHaveCoveredSong);
  
    List<Song> songsOldestToNewest = m.orderSongsOldestToNewest(songs);
    System.out.println("\nSongs ordered from oldest to newest:\n" + songsOldestToNewest);

    int year = 1999;
    Optional<Song> firstSongPlayedReleasedInYear = m.findFirstSongPlayedReleasedInYear(songs, year);
    System.out.println("\nFirst song played that was released in " + year + ":\n" + firstSongPlayedReleasedInYear);

  }

  List<Song> getSongsOfRockGenre(List<Song> songs) {
    Stream<Song> songsStream = songs.stream();
    return songsStream
    .filter(song -> song.getGenre().equals("Rock"))
    .collect(Collectors.toList());
  }

  List<Song> getSongsContainingRockGenre(List<Song> songs) {
    Stream<Song> songsStream = songs.stream();
    return songsStream
    .filter(song -> song.getGenre().contains("Rock"))
    .collect(Collectors.toList());
  }

  List<Song> getSongsByTheBeatles(List<Song> songs) {
    Stream<Song> songsStream = songs.stream();
    return songsStream
    .filter(song -> song.getArtist().equals("The Beatles"))
    .collect(Collectors.toList());
  }

  List<Song> getSongsThatStartWithH(List<Song> songs) {
    Stream<Song> songsStream = songs.stream();
    return songsStream
    .filter(song -> song.getTitle().startsWith("H"))
    .collect(Collectors.toList());
  }

  List<Song> getSongsMoreRecentThan1995(List<Song> songs) {
    Stream<Song> songsStream = songs.stream();
    return songsStream
    .filter(song -> song.getYear() > 1995)
    .collect(Collectors.toList());
  }

  Set<String> getAllGenres(List<Song> songs) {
    Stream<Song> songStream = songs.stream();
    return songStream
    .map(song -> song.getGenre())
    // .distinct()
    .collect(Collectors.toSet());
  }

  List<String> getArtistsThatHaveCoveredSong(List<Song> songs, String title, String artist) {
    return songs
    .stream()
    .filter(song -> song.getTitle().equals(title))
    .map(song -> song.getArtist())
    .filter(a -> !a.equals(artist))
    .collect(Collectors.toList());
  }

  List<Song> orderSongsOldestToNewest(List<Song> songs) {
    return songs
    .stream()
    .sorted(Comparator.comparingInt(Song::getYear))
    .collect(Collectors.toList());
  }

  Optional<Song> findFirstSongPlayedReleasedInYear(List<Song> songs, int year) {
    return songs
    .stream()
    .filter(s -> s.getYear() == year)
    .findFirst();
  }

}
