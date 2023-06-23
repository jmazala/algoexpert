// https://www.algoexpert.io/questions/quickselect

import java.util.Arrays;

class Quickselect {
  public static int quickselect(int[] array, int k) {
    int low = 0;
    int high = array.length - 1;

    while (true) {
      /*
       * Apply quicksort algorithm, using LOW as partition index.
       * At the end of the while loop, we do the final swap
       * Which puts the pivot element at position $right,
       * i.e. the partition index returned during quicksort algorithm.
       * In quicksort, we'd then quicksort left and right of partition index.
       * But in this case, we can disregard either the left side or right side
       * of partition index depending where we end up with respect to kth smallest.
       */
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

      /*
       * Finished this iteration of quicksort and
       * partition index is $right.
       */
      swap(array, pivot, right);
      if (right == k - 1) {
        return array[right];
      }

      /*
       * Disregard everything to the right
       * or left of partition index ($right).
       * i.e. if partition index < k-1, everything to the right
       * of it is greater than the kth smallest element. Even though
       * they are unsorted, we've done enough work
       */
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
