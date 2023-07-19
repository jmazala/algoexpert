// https://www.algoexpert.io/questions/longest-increasing-subsequence

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class LongestIncreasingSubsequence {
  public static List<Integer> longestIncreasingSubsequence(int[] array) {
    /*
     * At each index i, store the index of the second to last number
     * in the longest increasing subsequence ending with array[i]
     */
    int[] sequences = new int[array.length];

    /*
     * at each index i, store the index of the last element in the
     * longest increasing subsequence of length i
     */
    int[] indices = new int[array.length + 1];
    Arrays.fill(indices, -1);
    int longestLength = 0;

    for (int i = 0; i < array.length; i++) {
      int val = array[i];
      /*
       * Binary search for the subsequence length ending with the
       * smallest number ending with a number less than array[i]
       */
      int lengthFromIndex = binarySearch(1, longestLength, indices, array, val);
      sequences[i] = indices[lengthFromIndex - 1];
      indices[lengthFromIndex] = i;
      longestLength = Math.max(lengthFromIndex, longestLength);
    }

    return buildSubsequence(array, sequences, indices[longestLength]);
  }

  private static int binarySearch(int low, int high, int[] indices, int[] array, int val) {
    if (low > high) {
      return low;
    }

    int mid = (low + high) / 2;

    if (array[indices[mid]] < val) {
      low = mid + 1;
    } else {
      high = mid - 1;
    }

    return binarySearch(low, high, indices, array, val);
  }

  private static List<Integer> buildSubsequence(int[] array, int[] sequences, int curIndex) {
    List<Integer> subsequence = new ArrayList<Integer>();
    while (curIndex != -1) {
      subsequence.add(0, array[curIndex]);
      curIndex = sequences[curIndex];
    }

    return subsequence;
  }

  public static void main(String[] args) {
    System.out.println(longestIncreasingSubsequence(new int[] { 5, 7, -24, 12, 10, 2, 3, 12, 5, 6, 35 }).toString());
  }
}
