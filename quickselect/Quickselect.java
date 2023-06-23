// https://www.algoexpert.io/questions/quickselect

import java.util.Arrays;

class Quickselect {
  public static int quickselect(int[] array, int k) {
    int low = 0;
    int high = array.length - 1;

    while (true) {
      // Arbitrarily pick a pivot
      final int pivot = low;
      int left = low + 1;
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

      swap(array, pivot, right);
      if (right == k - 1) {
        return array[right];
      }

      if (right < k - 1) {
        low = right + 1;
      } else {
        high = right - 1;
      }
    }
  }

  private static void swap(int[] array, int i, int j) {
    int temp = array[i];
    array[i] = array[j];
    array[j] = temp;
  }

  public static void main(String[] args) {
    Arrays.toString(new int[] {});
    System.out.println(quickselect(new int[] { 8, 5, 2, 9, 7, 6, 3 }, 3)); // 5
  }
}
