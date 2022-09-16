class Book implements Comparable<Book> {
  private String title;
  Book(String t) { title = t; }
  String getTitle() { return title; }
  public String toString() { return title; }
  public int compareTo(Book o) { return o.getTitle().compareTo(title); }
}
