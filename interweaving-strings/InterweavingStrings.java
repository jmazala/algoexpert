// https://www.algoexpert.io/questions/interweaving-strings

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class InterweavingStrings {
  static class StackItem {
    int p1;
    int p2;
    int p3;

    public StackItem(int p1, int p2, int p3) {
      this.p1 = p1;
      this.p2 = p2;
      this.p3 = p3;
    }
  }

  /*
   * Using a stack
   * TIME: = O(n*m) worst case is say, one = "aaa", two = "aaa", and three =
   * "aaaaaa"
   * SPACE: O(n*m)
   * If we queue 2 entries for each character, that's 2nm
   */
  public static boolean interweavingStrings2(String one, String two, String three) {
    if (one.length() + two.length() != three.length()) {
      return false;
    }

    Stack<StackItem> stack = new Stack<>();
    stack.push(new StackItem(0, 0, 0));

    while (!stack.isEmpty()) {
      StackItem item = stack.pop();
      if (item.p3 == three.length()) {
        return true;
      }

      char c3 = three.charAt(item.p3);
      if (item.p2 < two.length()) {
        char c2 = two.charAt(item.p2);
        if (c3 == c2) {
          stack.push(new StackItem(item.p1, item.p2 + 1, item.p3 + 1));
        }
      }

      if (item.p1 < one.length()) {
        char c1 = one.charAt(item.p1);
        if (c3 == c1) {
          stack.push(new StackItem(item.p1 + 1, item.p2, item.p3 + 1));
        }
      }
    }

    return false;
  }

  /*
   * Using DP. Use a queue or a stack and fill in an n*m boolean matrix
   * Fill it in backwards and try to trace a path to (0, 1) or (1, 0)
   * 
   * TIME: O(n*m) worst case if say, one = "aaa", two = "aaa", and three =
   * "aaaaaa"
   * SPACE: O(n*m)
   */
  public static boolean interweavingStrings3(String one, String two, String three) {
    if (one.length() + two.length() != three.length()) {
      return false;
    }

    boolean[][] dp = new boolean[one.length() + 1][two.length() + 1];
    Queue<int[]> queue = new LinkedList<>();

    queue.add(new int[] { one.length(), two.length() });

    while (!queue.isEmpty()) {
      int[] coordinates = queue.remove();
      int i = coordinates[0];
      int j = coordinates[1];

      if (i == 0 && j == 0) {
        continue;
      }

      if (j > 0 && two.charAt(j - 1) == three.charAt(i + j - 1)) {
        dp[i][j] = true;
        queue.add(new int[] { i, j - 1 });
      }

      if (i > 0 && one.charAt(i - 1) == three.charAt(i + j - 1)) {
        dp[i][j] = true;
        queue.add(new int[] { i - 1, j });
      }

      if (dp[1][0] || dp[0][1]) {
        return true;
      }
    }

    return false;
  }

  /*
   * TIME: O(n) (same as stack above)
   * SPACE: O(n) for recursion stack, O(n1*n2) for memoization where n1 is
   * length of one and n2 is length of two
   */
  public static boolean interweavingStrings(String one, String two, String three) {
    if (one.length() + two.length() != three.length()) {
      return false;
    }

    Boolean[][] memo = new Boolean[one.length() + 1][two.length() + 1];
    return helper(0, 0, one, two, three, memo);
  }

  private static boolean helper(int p1, int p2, String one, String two, String three, Boolean[][] memo) {
    if (memo[p1][p2] != null) {
      return memo[p1][p2];
    }

    if ((p1 + p2) == three.length()) {
      return true;
    }

    char c3 = three.charAt(p1 + p2);

    if (p1 < one.length()) {
      char c1 = one.charAt(p1);
      if (c3 == c1) {
        memo[p1][p2] = helper(p1 + 1, p2, one, two, three, memo);
        if (memo[p1][p2]) {
          return true;
        }
      }
    }

    if (p2 < two.length()) {
      char c2 = two.charAt(p2);
      if (c3 == c2) {
        memo[p1][p2] = helper(p1, p2 + 1, one, two, three, memo);
        if (memo[p1][p2]) {
          return true;
        }
      }
    }

    memo[p1][p2] = false;
    return false;
  }

  public static void main(String[] args) {
    System.out.println(interweavingStrings("aabcc", "dbbca", "aadbbbaccc"));
    System.out.println(interweavingStrings3("abc", "def", "adbecf")); // true
    System.out.println(interweavingStrings3("beansprouting", "chartera", "banana")); // false
    System.out.println(interweavingStrings3("algoexpert", "your-dream-job", "your-algodream-expertjob")); // true
    System.out.println(interweavingStrings3("ababab", "ababab", "abababababab")); // true
  }
}
