import java.util.*;

class Program {
  private static int answer;
  private static Set<String> set;

  public static int numbersInPi(String pi, String[] numbers) {
    if (numbers == null || numbers.length == 0) {
      return 0;
    }

    set = new HashSet<>();
    for (String s : numbers) {
      set.add(s);
    }

    answer = Integer.MAX_VALUE;
    helper(pi, 0, 0);
    return answer == Integer.MAX_VALUE ? -1 : answer - 1;
  }

  private static int helper(String pi, int startIndex, int numSpaces) {
    if (startIndex == pi.length()) {
      return numSpaces;
    }

    for (int i = startIndex; i < pi.length(); i++) {
      String prefix = pi.substring(startIndex, i + 1);
      if (set.contains(prefix)) {
        int result = helper(pi, i + 1, numSpaces + 1);
        answer = Math.min(answer, result);
      }
    }

    return Integer.MAX_VALUE;
  }

  public static void main(String[] args) {
    String pi = "3141592653589793238462643383279";
    System.out.println(Program.numbersInPi(pi,
        new String[] { "314159265358979323846", "26433", "8", "3279", "314159265", "35897932384626433832", "79" }));
    System.out.println(Program.numbersInPi(pi,
        new String[] { "3", "1", "4", "592", "65", "35", "8", "9793", "2384626", "55", "83279" })); //13
  }
}
