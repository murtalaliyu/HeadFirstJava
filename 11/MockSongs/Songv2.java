class Songv2 implements Comparable<Songv2> {
  private String title;
  private String artist;
  private int bpm;

  Songv2(String title, String artist, int bpm) {
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

  // overide object's toString()
  public String toString() {
    return title + ": " + artist;
  }

  @Override
  public int compareTo(Songv2 o) {
    // sort by title
    return this.title.compareTo(o.title);
  }
}
