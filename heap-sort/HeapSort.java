
// https://www.algoexpert.io/questions/heap-sort

import java.util.Arrays;

public class HeapSort {
  /*
   * TIME: O(n log n) (BEST CASE, AVG CASE, WORST CASE)
   * O(n) for building maxHeap
   * O(n) to swap maxHeap greatest value with final value n * O(1))
   * O(n log n) for siftDown operation O(log n) starting from root of heap
   * SPACE: O(1) as we're just swapping in place
   */
  public static int[] heapSort(int[] array) {
    if (array.length < 2) {
      return array;
    }

    buildMaxHeap(array);

    for (int high = array.length - 1; high > 0; high--) {
      swap(array, 0, high);
      /*
       * We made the heap smaller and the sorted subarray
       * bigger by 1
       */
      siftDown(array, 0, high - 1);
    }

    return array;
  }

  private static void buildMaxHeap(int[] array) {
    int firstParentIndex = (array.length - 1) / 2;
    for (int i = firstParentIndex; i >= 0; i--) {
      siftDown(array, i, array.length - 1);
    }
  }

  private static void swap(int[] array, int i, int j) {
    int temp = array[i];
    array[i] = array[j];
    array[j] = temp;
  }

  private static void siftDown(int[] array, int index, int maxIndex) {
    while (index < maxIndex) {
      int leftChildIndex = 2 * index + 1;

      // no children to sift with
      if (leftChildIndex > maxIndex) {
        return;
      }

      int rightChildIndex = 2 * index + 2;

      // Only a left child. Swap if necessary
      if (rightChildIndex > maxIndex) {
        if (array[index] < array[leftChildIndex]) {
          swap(array, index, leftChildIndex);
        }

        return;
      }

      // Bigger than both of its children. Max Heap is set.
      if (array[index] >= array[leftChildIndex] && array[index] >= array[rightChildIndex]) {
        return;
      }

      // swap with the larger of the 2 children

      // left child is bigger
      if (array[index] < array[leftChildIndex] && array[leftChildIndex] >= array[rightChildIndex]) {
        swap(array, index, leftChildIndex);
        index = leftChildIndex;
      } else { // right child is bigger
        swap(array, index, rightChildIndex);
        index = rightChildIndex;
      }
    }
  }

  public static void main(String[] args) {
    System.out.println(Arrays.toString(heapSort(new int[] { 6, 5 }))); // [5, 6]
    System.out.println(Arrays.toString(heapSort(new int[] { 8, 5, 2, 9, 5, 6, 3 }))); // [2, 3, 5, 5, 6, 8, 9]
    System.out.println(Arrays.toString(heapSort(new int[] {}))); // []
    System.out.println(Arrays.toString(heapSort(new int[] { 5 }))); // [5]
  }
}
