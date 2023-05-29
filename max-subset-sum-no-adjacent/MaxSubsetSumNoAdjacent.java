// https://www.algoexpert.io/questions/max-subset-sum-no-adjacent

public class MaxSubsetSumNoAdjacent {
  /*
   * TIME: O(n)
   * SPACE: O(n) for dp array
   * 
   * The idea here create an array to store the max subset sum
   * up to a given point in the array
   * You want to choose including or excluding that particular value.
   * If you include the value, you add it to the maximum non adjacent previous sum
   * (i.e. max sum 2 spaces ago)
   * 
   */
  public static int maxSubsetSumNoAdjacent(int[] array) {
    if (array.length == 0) {
      return 0;
    }

    if (array.length == 1) {
      return array[0];
    }

    int[] dp = new int[array.length];
    dp[0] = array[0];
    dp[1] = Math.max(array[0], array[1]);

    for (int i = 2; i < array.length; i++) {
      dp[i] = Math.max(array[i] + dp[i - 2], dp[i - 1]);
    }

    return dp[array.length - 1];
  }
}
