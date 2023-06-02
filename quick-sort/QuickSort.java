import java.util.*;

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
    final int pivot = array[high];
    int i = low;

    for (int j = low; j < high; j++) {
      if (array[j] < pivot) {
        swap(array, i, j);
        i++;
      }
    }

    swap(array, i, high);
    return i;
  }

  private static void swap(int[] array, int i, int j) {
    int temp = array[i];
    array[i] = array[j];
    array[j] = temp;
  }
}
