// https://www.algoexpert.io/questions/max-sum-increasing-subsequence

import java.util.*;

public class MaxSumIncreasingSubsequence {
  public static List<List<Integer>> maxSumIncreasingSubsequence(int[] array) {
    int[] dp = new int[array.length];
    List<List<Integer>> subsequences = new ArrayList<>();
    int maxIndex = 0;
    int overallMax = array[0];

    dp[0] = array[0];
    subsequences.add(new ArrayList<Integer>(Arrays.asList(new Integer[] { array[0] })));

    for (int i = 1; i < array.length; i++) {
      int curMax = array[i];
      List<Integer> subsequence = new ArrayList<>(Arrays.asList(new Integer[] { array[i] }));

      for (int j = 0; j < i; j++) {
        if (array[i] > array[j]) {
          if (dp[j] + array[i] > curMax) {
            curMax = dp[j] + array[i];
            subsequence = new ArrayList<>(subsequences.get(j));
            subsequence.add(array[i]);
          }
        }
      }

      dp[i] = curMax;
      subsequences.add(subsequence);

      if (curMax > overallMax) {
        overallMax = curMax;
        maxIndex = i;
      }
    }

    List<List<Integer>> output = new ArrayList<>();
    output.add(new ArrayList<>(Arrays.asList(new Integer[] { dp[maxIndex] })));
    output.add(subsequences.get(maxIndex));
    return output;
  }

  public static void main(String[] args) {
    List<List<Integer>> output = maxSumIncreasingSubsequence(new int[] { 10, 70, 20, 30, 50, 11, 30 });
    System.out.println(output.get(0).toString() + " " + output.get(1).toString());
  }
}
