import java.util.*;

class Program {
  public static int minNumberOfJumps(int[] array) {
    int[] dp = new int[array.length];
    Arrays.fill(dp, Integer.MAX_VALUE);
    dp[0] = 0;

    for (int i = 0; i < array.length; i++) {
      int jumps = array[i];
      while (jumps > 0) {
        int next = i + jumps;
        jumps--;
        if (next >= array.length) {
          continue;
        }

        dp[next] = Math.min(dp[next], dp[i] + 1);

      }
    }

    return dp[dp.length - 1];
  }

  public static void main(String[] args) {
    System.out.println(Program.minNumberOfJumps(new int[] { 3, 4, 2, 1, 2, 3, 7, 1, 1, 1, 3 })); // 4
  }
}
