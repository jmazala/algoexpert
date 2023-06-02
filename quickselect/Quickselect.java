import java.util.*;

class Quickselect {
  public static int quickselect(int[] array, int k) {
    return helper(array, 0, array.length - 1, k - 1);
  }

  private static void swap(int[] array, int i, int j) {
    int temp = array[i];
    array[i] = array[j];
    array[j] = temp;
  }

  private static int helper(int[] array, int low, int high, int position) {
    while (true) {
      final int pivot = low;
      int left = pivot + 1;
      int right = high;

      while (left <= right) {
        if (array[left] > array[pivot] && array[right] < array[pivot]) {
          swap(array, left, right);
          left++;
          right--;
          continue;
        }

        if (array[left] <= array[pivot]) {
          left++;
        }

        if (array[right] >= array[pivot]) {
          right--;
        }
      }

      swap(array, right, pivot);
      if (right == position) {
        return array[right];
      }

      if (right > position) {
        high = right - 1;
      } else {
        low = right + 1;
      }
    }
  }
}
