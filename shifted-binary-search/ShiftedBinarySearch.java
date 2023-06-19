// https://www.algoexpert.io/questions/shifted-binary-search

import java.util.ArrayList;
import java.util.List;

public class ShiftedBinarySearch {
  /*
   * TIME: O(n) for shifting array
   * SPACE: O(n) for duplicated lists
   */
  public static int shiftedBinarySearch2(int[] array, int target) {
    List<Integer> lower = new ArrayList<>();

    lower.add(array[0]);
    int i = 1;
    while (i < array.length && array[i] > array[i - 1]) {
      lower.add(array[i]);
      i++;
    }

    List<Integer> higher = new ArrayList<>();
    while (i < array.length) {
      higher.add(array[i]);
      i++;
    }

    if (target >= lower.get(0) && target <= lower.get(lower.size() - 1)) {
      return binarySearch(lower, target);
    }

    int result = binarySearch(higher, target);
    return result == -1 ? -1 : result + lower.size();
  }

  private static int binarySearch(List<Integer> nums, int target) {
    int low = 0;
    int high = nums.size() - 1;

    while (low <= high) {
      int mid = (low + high) / 2;
      if (nums.get(mid) == target) {
        return mid;
      }

      if (nums.get(mid) < target) {
        low = mid + 1;
      } else {
        high = mid - 1;
      }
    }

    return -1;
  }

  public static int shiftedBinarySearch(int[] array, int target) {
    if (array.length == 0) {
      return -1;
    }

    if (array.length == 1) {
      return array[0] == target ? 0 : -1;
    }

    int low = 0;
    int high = array.length - 1;

    while (low <= high) {
      if (array[low] == target) {
        return low;
      }

      if (array[high] == target) {
        return high;
      }

      int mid = (low + high) / 2;
      int val = array[mid];

      if (val == target) {
        return mid;
      }

      /*
       * At any given point in a shifted sorted array, either the left half is sorted,
       * or the right half is sorted. Determine which side is sorted, then determine
       * which half target is in.
       */
      if (array[low] < array[mid]) {
        if (target > array[low] && target < array[mid]) {
          high = mid - 1;
        } else {
          low = mid + 1;
        }
      } else {
        if (target > array[mid] && target < array[high]) {
          low = mid + 1;
        } else {
          high = mid - 1;
        }
      }
    }

    return -1;
  }

  public static void main(String[] args) {
    System.out.println(shiftedBinarySearch(new int[] { 45, 61, 71, 72, 73, 0, 1, 21, 33, 37 }, 99)); // -1
    System.out.println(shiftedBinarySearch(new int[] { 45, 61, 71, 72, 73, 0, 1, 21, 33, 37 }, 45)); // 0
    System.out.println(shiftedBinarySearch(new int[] { 45, 61, 71, 72, 73, 0, 1, 21, 33, 37 }, 61)); // 1
    System.out.println(shiftedBinarySearch(new int[] { 45, 61, 71, 72, 73, 0, 1, 21, 33, 37 }, 71)); // 2
    System.out.println(shiftedBinarySearch(new int[] { 45, 61, 71, 72, 73, 0, 1, 21, 33, 37 }, 33)); // 8
    System.out.println(shiftedBinarySearch(new int[] { 5, 23, 111, 1 }, 111)); // 2
  }
}
