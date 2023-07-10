// https://www.algoexpert.io/questions/max-profit-with-k-transactions

class MaxProfitWithKTransactions {
  /*
   * TIME: O(nk)
   * O(k) for first loop
   * O(n) for second loop
   * O(2) inside second loop = O(1)
   * SPACE: O(k + 1 * n) = O(kn) for dp array
   * This could be improved as we really only need to store 2 rows at a time in
   * the dp array. (The current row, and the row above)
   */
  public static int maxProfitWithKTransactions(int[] prices, int k) {
    if (prices.length < 2) {
      return 0;
    }

    // cols is days and rows is # of transactions
    int[][] dp = new int[k + 1][prices.length];
    for (int t = 1; t <= k; t++) {
      int maxMoneySoFar = Integer.MIN_VALUE;

      for (int d = 1; d < prices.length; d++) {
        // take into account if we buy a stock on day d-1
        maxMoneySoFar = Math.max(maxMoneySoFar, dp[t - 1][d - 1] - prices[d - 1]);

        // decide to sell at this day, or keep previous max profit
        int maxProfitFromDayBefore = dp[t][d - 1];
        int maxProfitIfWeSellOnDay = maxMoneySoFar + prices[d];
        dp[t][d] = Math.max(maxProfitFromDayBefore, maxProfitIfWeSellOnDay);
      }
    }

    return dp[k][prices.length - 1];
  }

  public static void main(String[] args) {
    System.out.println(maxProfitWithKTransactions(new int[] { 5, 11, 3, 50, 60, 90 }, 2)); // 93
  }
}
