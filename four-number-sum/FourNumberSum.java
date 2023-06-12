// https://www.algoexpert.io/questions/four-number-sum

import java.util.*;

public class FourNumberSum {

  /*
   * TIME: AVG: O(n^2) for building the hash, O(n^2) for iterating the hash
   * SPACE: O(n^2) for the hash
   */
  public static List<Integer[]> fourNumberSum(int[] array, int targetSum) {
    Map<Integer, List<int[]>> twoSums = new HashMap<>();
    List<Integer[]> output = new ArrayList<>();

    for (int i = 0; i < array.length - 1; i++) {
      for (int j = i + 1; j < array.length; j++) {
        int sum = array[i] + array[j];
        if (!twoSums.containsKey(sum)) {
          twoSums.put(sum, new ArrayList<int[]>());
        }
        twoSums.get(sum).add(new int[] { i, j });
      }
    }

    for (Map.Entry<Integer, List<int[]>> e : twoSums.entrySet()) {
      Integer sum = e.getKey();
      int difference = targetSum - sum;

      for (int[] pair1 : e.getValue()) {
        int i1 = pair1[0];
        int i2 = pair1[1];
        for (int[] pair2 : twoSums.getOrDefault(difference, new ArrayList<>())) {
          int j1 = pair2[0];
          int j2 = pair2[1];
          if (j1 <= i1 || j1 <= i2 || j2 <= i1 || j2 <= i2) {
            continue;
          }

          output.add(new Integer[] { array[i1], array[i2], array[j1], array[j2] });
        }
      }
    }

    return output;
  }

  /*
   * TIME: O(n log n) to sort + O(n^3) for loops
   * (worst case if all combinations are too small)
   * SPACE: O(n) for output
   */
  public static List<Integer[]> fourNumberSum2(int[] array, int targetSum) {
    List<Integer[]> output = new ArrayList<>();
    Arrays.sort(array);

    for (int i = 0; i < array.length - 3; i++) {
      if (array[i] > targetSum) {
        break;
      }

      for (int j = i + 1; j < array.length - 2; j++) {
        if (array[i] + array[j] > targetSum) {
          break;
        }

        int left = j + 1;
        int right = array.length - 1;

        while (left < right) {
          int sum = array[i] + array[j] + array[left] + array[right];
          if (sum == targetSum) {
            output.add(new Integer[] { array[i], array[j], array[left], array[right] });
            left++;
            right--;
          } else if (sum < targetSum) {
            left++;
          } else {
            right--;
          }
        }
      }
    }

    return output;
  }

  public static void main(String[] args) {
    List<Integer[]> output = FourNumberSum.fourNumberSum(new int[] { 7, 6, 4, -1, 1, 2 }, 16);

    for (Integer[] item : output) {
      System.out.println(Arrays.toString(item));
    }
  }
}
