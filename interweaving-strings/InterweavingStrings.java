// https://www.algoexpert.io/questions/interweaving-strings

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
   * BRUTE FORCE USING A STACK. (I misunderstood the problem)
   */
  public static boolean interweavingStrings2(String one, String two, String three) {
    Stack<StackItem> stack = new Stack<>();
    stack.push(new StackItem(0, 0, 0));

    while (!stack.isEmpty()) {
      StackItem item = stack.pop();
      if (item.p3 == three.length() && item.p1 == one.length() && item.p2 == two.length()) {
        return true;
      }

      if (item.p3 == three.length()) {
        continue;
      }

      char c3 = three.charAt(item.p3);

      for (int p2 = item.p2; p2 < two.length(); p2++) {
        char c2 = two.charAt(p2);
        if (c2 == c3) {
          stack.push(new StackItem(item.p1, p2 + 1, item.p3 + 1));
        }
      }

      for (int p1 = item.p1; p1 < one.length(); p1++) {
        char c1 = one.charAt(p1);
        if (c3 == c1) {
          stack.push(new StackItem(p1 + 1, item.p2, item.p3 + 1));
        }
      }
    }

    return false;
  }

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

    return false;
  }

  public static void main(String[] args) {
    System.out.println(interweavingStrings("beansprouting", "chartera", "banana")); // false
    System.out.println(interweavingStrings("algoexpert", "your-dream-job", "your-algodream-expertjob")); // true
    System.out.println(interweavingStrings("ababab", "ababab", "abababababab")); // true
  }
}
