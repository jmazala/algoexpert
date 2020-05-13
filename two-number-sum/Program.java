import java.util.*;

class Program {

  // O(n) time / O(n) space
  public static int[] twoNumberSum(int[] array, int targetSum) {
    Set<Integer> set = new HashSet<>();

    for (int i : array) {
      if (set.contains(targetSum - i)) {
        return new int[] { targetSum - i, i };
      }
      set.add(i);
    }

    return new int[] {};
  }

  // O(n log n) time / O(1) space
  // public static int[] twoNumberSum(int[] array, int targetSum) {
  //   Arrays.sort(array);
  //   int left = 0;
  //   int right = array.length - 1;

  //   while (left < right) {
  //     int currentSum = array[left] + array[right];
  //     if (currentSum == targetSum) {
  //       return new int[] { array[left], array[right] };
  //     }

  //     if (currentSum < targetSum) {
  //       left++;
  //     } else {
  //       right--;
  //     }
  //   }

  //   return new int[] {};
  // }
}
