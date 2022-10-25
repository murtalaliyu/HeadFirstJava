package jukebox;

public class Song {
  private final String title;
  private final String artist;
  private final String genre;
  private final int year;
  private final int timesPlayed;

  // constructor
  public Song(String title, String artist, String genre, 
  int year, int timesPlayed) {
    this.title = title;
    this.artist = artist;
    this.genre = genre;
    this.year = year;
    this.timesPlayed = timesPlayed;
  }

  // getters
  public String getTitle() {
    return title;
  }

  public String getArtist() {
    return artist;
  }

  public String getGenre() {
    return genre;
  }

  public int getYear() {
    return year;
  }

  public int getTimesPlayed() {
    return timesPlayed;
  }

  // override toString
  public String toString() {
    return "\n" + title + " | " + artist + " | " + genre + " | " + year + " | " + timesPlayed ;
  }

}
