// https://www.algoexpert.io/questions/max-sum-increasing-subsequence

import java.util.*;

/*
 * TIME:  O(n^2)
 * SPACE:  O(n).  This one is reduced a bit compared to solution 1 because
 * we are storing indices instead of entire subsequences.  And build it
 * at the end
 */
public class MaxSumIncreasingSubsequence {
  public static List<List<Integer>> maxSumIncreasingSubsequence(int[] array) {
    int[] dp = new int[array.length];
    Integer[] subsequences = new Integer[array.length];
    int maxIndex = 0;
    int overallMax = array[0];

    dp[0] = array[0];
    subsequences[0] = null;

    for (int i = 1; i < array.length; i++) {
      int curMax = array[i];
      subsequences[i] = null;

      for (int j = 0; j < i; j++) {
        if (array[i] > array[j]) {
          if (dp[j] + array[i] > curMax) {
            curMax = dp[j] + array[i];
            subsequences[i] = j;
          }
        }
      }

      dp[i] = curMax;

      if (curMax > overallMax) {
        overallMax = curMax;
        maxIndex = i;
      }
    }

    List<List<Integer>> output = new ArrayList<>();
    output.add(new ArrayList<>(Arrays.asList(new Integer[] { dp[maxIndex] })));
    output.add(getSubsequences(array, subsequences, maxIndex));
    return output;
  }

  private static List<Integer> getSubsequences(int[] array, Integer[] subsequences, Integer maxIndex) {
    List<Integer> subsequence = new ArrayList<>();
    while (maxIndex != null) {
      subsequence.add(array[maxIndex]);
      maxIndex = subsequences[maxIndex];
    }

    Collections.reverse(subsequence);
    return subsequence;
  }

  public static void main(String[] args) {
    List<List<Integer>> output = maxSumIncreasingSubsequence(new int[] { 10, 70, 20, 30, 50, 11, 30 });
    System.out.println(output.get(0).toString() + " " + output.get(1).toString());
  }
}
