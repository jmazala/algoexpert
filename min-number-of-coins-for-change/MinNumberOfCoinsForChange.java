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

  /*
   * Use a BFS structure to search until we find n.
   * Because it's BFS, we will always use the least number of steps (coins) to
   * arrive
   * at n.
   * 
   * Optimized further by filtering the denoms array twice
   * 
   * 1 - once at inception for any denoms > n
   * 2 - When BFSing, don't bother queueing next items greater than n
   */
  public static int minNumberOfCoinsForChange2(int n, int[] denoms) {
    if (n == 0) {
      return 0;
    }

    denoms = Arrays.stream(denoms).filter(x -> x <= n).toArray();
    Arrays.sort(denoms);

    Queue<Integer> queue = new LinkedList<>();
    queue.add(0);
    int[] dp = new int[n + 1];
    Arrays.fill(dp, -1);
    dp[0] = 0;

    while (!queue.isEmpty()) {
      int queueSize = queue.size();
      for (int i = 0; i < queueSize; i++) {
        int amount = queue.remove();
        int coins = dp[amount];

        for (int denom : Arrays.stream(denoms).filter(x -> (x + amount) <= n).toArray()) {
          int nextAmount = amount + denom;

          if (dp[nextAmount] != -1) {
            continue;
          }

          int nextCoins = coins + 1;

          if (nextAmount == n) {
            return nextCoins;
          }

          dp[nextAmount] = nextCoins;
          queue.add(nextAmount);
        }
      }
    }

    return -1;
  }

  public static void main(String[] args) {
    System.out.println(minNumberOfCoinsForChange2(7, new int[] { 2, 4 })); // -1
    System.out.println(minNumberOfCoinsForChange2(7, new int[] { 1, 5, 10 })); // 3
    System.out.println(minNumberOfCoinsForChange2(14, new int[] { 1, 5, 10 })); // 5
  }
}
