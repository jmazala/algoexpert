// https://www.algoexpert.io/questions/min-number-of-coins-for-change

import java.util.*;

public class MinNumberOfCoinsForChange {

  public static int minNumberOfCoinsForChange(int n, int[] denoms) {
    denoms = Arrays.stream(denoms).filter(x -> x <= n).toArray();
    Arrays.sort(denoms);

    Integer[] dp = new Integer[n + 1];
    Arrays.fill(dp, null);
    dp[0] = 0;

    for (int i = 1; i <= n; i++) {
      for (int coin : denoms) {
        if (coin > i) {
          break;
        }

        int missing = i - coin;
        if (dp[missing] != null) {
          if (dp[i] == null) {
            dp[i] = dp[missing] + 1;
          } else {
            dp[i] = Math.min(dp[i], dp[missing] + 1);
          }
        }
      }
    }

    return dp[n] == null ? -1 : dp[n];
  }

  public static void main(String[] args) {
    System.out.println(minNumberOfCoinsForChange(7, new int[] { 2, 4 })); // -1
    System.out.println(minNumberOfCoinsForChange(7, new int[] { 1, 5, 10 })); // 3
    System.out.println(minNumberOfCoinsForChange(14, new int[] { 1, 5, 10 })); // 5
  }
}
