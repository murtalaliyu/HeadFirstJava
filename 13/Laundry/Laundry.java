class Laundary {
  public void doLaundary(boolean flip) throws ClothingException {
    if (flip) {
      System.out.println("doing laundry...");
    } else {
      throw new ClothingException();
    }
  }
}
