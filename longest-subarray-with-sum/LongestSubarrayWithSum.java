// https://www.algoexpert.io/questions/longest-subarray-with-sum

import java.util.Arrays;

public class LongestSubarrayWithSum {

  /*
   * TIME: O(n)
   * SPACE: O(1)
   * 
   * Sliding window problem.
   * Extend the window 1 element at a time with every outer loop iteration
   * Inside that loop, if you need to decrease the window do so.
   * Then check for equality and window length.
   */
  public static int[] longestSubarrayWithSum(int[] array, int targetSum) {
    int[] answer = new int[] { -1, -1 };
    int start = 0;
    int end = 0;
    int currentSum = 0;

    while (end < array.length) {
      currentSum += array[end];

      while (start < end && currentSum > targetSum) {
        currentSum -= array[start];
        start++;
      }

      if (currentSum == targetSum) {
        if (answer[0] == -1 || (answer[1] - answer[0]) < (end - start)) {
          answer[0] = start;
          answer[1] = end;
        }
      }

      end++;
    }

    return (answer[0] == -1) ? (new int[] {}) : answer;
  }

  public static void main(String[] args) {
    // [0, 9]
    System.out.println(Arrays.toString(longestSubarrayWithSum(new int[] { 0, 0, 0, 0, 0, 1, 0, 0, 0, 0 }, 1)));
    // [4, 8]
    System.out.println(Arrays.toString(longestSubarrayWithSum(new int[] { 1, 2, 3, 4, 3, 3, 1, 2, 1, 2 }, 10))); // [4,
    // [4, 11]
    System.out
        .println(Arrays.toString(longestSubarrayWithSum(new int[] { 25, 25, 25, 25, 100, 0, 0, 0, 0, 0, 0, 0 }, 100)));
  }
}
