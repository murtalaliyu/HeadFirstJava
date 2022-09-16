import java.util.List;

class MixForLoops {
  public static void main(String[] args) {
    List<Integer> nums = List.of(1,2,3,4,5);
    String output = "";

    for (Integer num : nums) {
      output += nums + " ";
    }

    System.out.println(output);
  }
}
