class WashingMachine {

  public static void main(String[] args) throws ClothingException {
    new WashingMachine().go();
  }

  void go() throws ClothingException {
    Laundary laundary = new Laundary();
    boolean flip = true;
    laundary.doLaundary(flip);

    // try {

    //   laundary.doLaundary(flip);
     
    // } catch (TeeShirtException tse) {
    //   System.out.println("TeeShirt Exception: " + tse.getMessage());
    // } catch (LingerieException le) {
    //   System.out.println("Lingerie Exception: " + le.getMessage());
    // } catch (ClothingException ce) {
    //   System.out.println("Clothing Exception: " + ce.getMessage());
    // }
  }
}
