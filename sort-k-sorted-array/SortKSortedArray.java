// https://www.algoexpert.io/questions/sort-k-sorted-array

import java.util.*;

class SortKSortedArray {

  public static int[] sortKSortedArray(int[] array, int k) {
    PriorityQueue<Integer> minHeap = new PriorityQueue<>();
    int start = 0;
    int end = 0;

    while (start < array.length) {
      if (end < array.length && minHeap.size() < (k + 1)) {
        minHeap.add(array[end]);
        end++;
        continue;
      }

      array[start] = minHeap.remove();
      start++;
    }

    return array;
  }

  public static void main(String[] args) {
    System.out.println(Arrays.toString(sortKSortedArray(new int[] { -1, -3, -4, 2, 1, 3 }, 2)));
  }
}
