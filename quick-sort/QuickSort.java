// https://www.algoexpert.io/questions/quick-sort

import java.util.Arrays;

class QuickSort {
  public static int[] quickSort(int[] array) {
    helper(array, 0, array.length - 1);
    return array;
  }

  private static void helper(int[] array, int low, int high) {
    if (low >= high) {
      return;
    }

    int partitionIndex = partition(array, low, high);
    helper(array, low, partitionIndex - 1);
    helper(array, partitionIndex + 1, high);
  }

  private static int partition(int[] array, int low, int high) {
    final int pivot = array[low];

    int i = low + 1;
    int j = high;

    while (i <= j) {
      if (array[i] > pivot && array[j] < pivot) {
        swap(array, i, j);
        i++;
        j--;
        continue;
      }

      if (array[i] <= pivot) {
        i++;
      }

      if (array[j] >= pivot) {
        j--;
      }
    }

    swap(array, low, j);
    return j;
  }

  private static void swap(int[] array, int i, int j) {
    int temp = array[i];
    array[i] = array[j];
    array[j] = temp;
  }

  public static void main(String[] args) {
    System.out.println(Arrays.toString(quickSort(new int[] { 8, 5, 2, 9, 7, 6, 3 })));
  }
}
