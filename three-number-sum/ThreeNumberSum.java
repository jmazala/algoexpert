// https://www.algoexpert.io/questions/three-number-sum

import java.util.*;

public class ThreeNumberSum {

  /*
   * TIME: O(n^2) in worst case if all combinations are too small
   * SPACE: O(n) for output
   */
  public static List<Integer[]> threeNumberSum(int[] array, int targetSum) {
    List<Integer[]> output = new ArrayList<Integer[]>();
    Arrays.sort(array);

    for (int i = 0; i < array.length - 2; i++) {
      if (array[i] > targetSum) {
        break;
      }

      int left = i + 1;
      int right = array.length - 1;

      while (left < right) {
        int sum = array[i] + array[left] + array[right];
        if (sum == targetSum) {
          output.add(new Integer[] { array[i], array[left], array[right] });
          left++;
          right--;
          continue;
        }

        if (sum < targetSum) {
          left++;
          continue;
        }

        right--;
        continue;
      }
    }

    return output;
  }

  public static void main(String[] args) {
    List<Integer[]> output = threeNumberSum(new int[] { 12, 3, 1, 2, -6, 5, -8, 6 }, 0);

    for (Integer[] a : output) {
      System.out.print(Arrays.toString(a) + " ");
    }
  }
}
