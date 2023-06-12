// https://www.algoexpert.io/questions/largest-range

import java.util.*;

public class LargestRange {
  public static int[] largestRange(int[] array) {
    int[] answer = new int[] { array[0], array[0] };
    Map<Integer, Boolean> hash = new HashMap<>();
    for (int val : array) {
      hash.put(val, false);
    }

    for (int val : array) {
      if (hash.get(val)) {
        continue;
      }

      hash.put(val, true);

      int left = val - 1;
      int right = val + 1;

      while (hash.containsKey(left) && !hash.get(left)) {
        hash.put(left, true);
        left--;
      }

      while (hash.containsKey(right) && !hash.get(right)) {
        hash.put(right, true);
        right++;
      }

      if ((right - 1) - (left + 1) > (answer[1] - answer[0])) {
        answer[0] = left + 1;
        answer[1] = right - 1;
      }
    }

    return answer;
  }

  public static void main(String[] args) {
    System.out.println(Arrays.toString(largestRange(new int[] { 100, 4, 200, 1, 3, 2 }))); // [1, 4]
    System.out.println(Arrays.toString(largestRange(new int[] { 9, 1, 4, 7, 3, -1, 0, 5, 8, -1, 6 }))); // [3, 9]
    System.out.println(Arrays
        .toString(largestRange(new int[] { 2147483646, -2147483647, 0, 2, 2147483644, -2147483645, 2147483645 }))); // [2147483644,
                                                                                                                    // 2147483646]
    System.out.println(Arrays.toString(largestRange(new int[] { 1, 11, 3, 0, 15, 5, 2, 4, 10, 7, 12, 6 }))); // [0, 7]
  }
}