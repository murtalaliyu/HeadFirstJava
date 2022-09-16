class Mountain {
  String name;
  int height;

  Mountain(String name, int height) {
    this.name = name;
    this.height = height;
  }

  // override
  public String toString() {
    return name + " " + height;
  }
}
