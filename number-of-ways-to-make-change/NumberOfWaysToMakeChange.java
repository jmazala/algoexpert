// https://www.algoexpert.io/questions/number-of-ways-to-make-change

import java.util.Arrays;

class NumberOfWaysToMakeChange {
  public static int numberOfWaysToMakeChange(int n, int[] denoms) {
    if (denoms.length == 0) {
      return 0;
    }

    if (denoms.length == 1) {
      return n % denoms[0] == 0 ? 1 : 0;
    }

    int[] dp = new int[n + 1];

    dp[0] = 1; // only 1 way to make 0
    Arrays.sort(denoms);

    // use all of 1 coin, then all of another coin
    for (int coin : denoms) {
      if (coin > n) {
        break;
      }

      for (int i = coin; i <= n; i++) {
        int missing = i - coin;
        dp[i] += dp[missing];
      }
    }

    return dp[n];
  }

  public static void main(String[] args) {
    // System.out.println(numberOfWaysToMakeChange(6, new int[] { 1, 5 }));
    // // 2
    /*
     * 1 - 16 pennies
     * 2 - 11 pennies 1 nickel
     * 3 - 6 pennies 2 nickels
     * 4 - 6 pennies 1 dime
     * 5 - 1 penny 3 nickels
     * 6 - 1 penny 1 nickel 1 dime
     */
    // System.out.println(numberOfWaysToMakeChange(16, new int[] { 1, 5, 10,
    // 25 })); // 6
    System.out.println(numberOfWaysToMakeChange(10, new int[] { 1, 5, 10, 25 })); // 4
  }
}
