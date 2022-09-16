class SongV4 implements Comparable<SongV4> {
  private String title;
  private String artist;
  private int bpm;

  SongV4(String title, String artist, int bpm) {
    this.title = title;
    this.artist = artist;
    this.bpm = bpm;
  }

  public String getTitle() {
    return title;
  }

  public String getArtist() {
    return artist;
  }

  public int getBpm() {
    return bpm;
  }

  @Override
  public int compareTo(SongV4 s) {
    return artist.compareTo(s.getArtist());
  }

  public String toString() {
    return title + " | " + artist + " | " + bpm + "\n";
  }

  public int hashCode() {
    return title.hashCode();
  }

  public boolean equals(Object o) {
    SongV4 s = (SongV4) o;
    return title.equals(s.getTitle());
  }
  
}
