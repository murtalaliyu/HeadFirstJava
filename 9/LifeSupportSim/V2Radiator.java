import java.util.ArrayList;

class V2Radiator {
  V2Radiator() {}
  V2Radiator(ArrayList<SimUnit> list) {
    for (int i = 0; i < 5; i++) {
      list.add(new SimUnit("V2Radiator"));
    }
  }
}