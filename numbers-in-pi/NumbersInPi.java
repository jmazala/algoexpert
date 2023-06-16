import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

class NumbersInPi {
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

    // For the sake of this problem, don't try if we've found a shorter solution
    if (numSpaces > answer) {
      return Integer.MAX_VALUE;
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

  /*
   * BFS approach. In the queue store the remaining string to match.
   * This could get super expensive especially if we have a long pi string
   * and numbers contains a bunch of strings of length 1.
   * The space for the queue would be expensive.
   */
  static class QueueItem {
    int spacesUsed;
    String remaining;

    public QueueItem(int spacesUsed, String remaining) {
      this.spacesUsed = spacesUsed;
      this.remaining = remaining;
    }
  }

  public static int numbersInPi2(String pi, String[] numbers) {
    Queue<QueueItem> queue = new LinkedList<>();

    queue.add(new QueueItem(0, pi));

    while (!queue.isEmpty()) {
      QueueItem item = queue.remove();
      for (String next : numbers) {
        if (item.remaining.indexOf(next) == 0) {
          String remaining = item.remaining.substring(next.length(), item.remaining.length());
          if (remaining.length() == 0) {
            return item.spacesUsed;
          }

          queue.add(new QueueItem(item.spacesUsed + 1, remaining));
        }
      }
    }

    return -1;
  }

  public static void main(String[] args) {
    String pi = "3141592653589793238462643383279";
    System.out.println(numbersInPi(pi,
        new String[] { "314159265358979323846", "26433", "8", "3279", "314159265", "35897932384626433832", "79" }));
    System.out.println(numbersInPi(pi,
        new String[] { "3", "1", "4", "592", "65", "35", "8", "9793", "2384626", "55", "83279" })); // 13
    System.out.println(numbersInPi2(pi,
        new String[] { "3", "1", "4", "592", "65", "35", "8", "9793", "2384626", "55", "83279" })); // 13
  }
}
