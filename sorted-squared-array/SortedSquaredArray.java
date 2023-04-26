import java.util.*;

class SortedSquaredArray {

  // we do have 2 sorted arrays, but no way of knowing the bounds of either one
  // therefore we can't declare another array with placeholder (max / min int) and
  // know where to insert

  // option 1 - eliminate the negatives, sort again, square the array
  /*
   * option 2 - use dynamically sized array, insert the squared negatives, insert
   * the squared positives where they are supposed to go
   * 
   * option 3 - same thing as 2, but use 2 pointers on either side of the array
   */

  /*
   * OPTION 1
   * O(n) for array iterate
   * O(n log n) for sort
   * O(n) for iterate
   * = O(n log n) time and O(1) space (unless Arrays.sort uses extra memory)
   */
  public static int[] sortedSquaredArray(int[] array) {
    for (int i = 0; i < array.length; i++) {
      if (array[i] < 0) {
        array[i] *= -1;
      }
    }

    Arrays.sort(array);

    for (int i = 0; i < array.length; i++) {
      array[i] *= array[i];
    }

    return array;
  }

  /*
   * OPTION 2
   * O(n) extra space for sorted array
   * O(1) extra space for pointers
   * O(n) to iterate through array and insert elements
   * O(?) for dynamic resizing and insertion at index of ArrayList... depends on
   * when it decides to allocate more memory and move items over
   * O(n) to map Integer to int
   * = O(n) time and O(n) space
   */
  public static int[] sortedSquaredArray2(int[] array) {
    ArrayList<Integer> sorted = new ArrayList<>();
    int pos = 0;

    while (pos < array.length && array[pos] < 0) {
      sorted.add(0, array[pos] * array[pos]);
      pos++;
    }

    int insertPos = 0;
    for (; pos < array.length; pos++) {
      int squaredToInsert = array[pos] * array[pos];
      while (insertPos < sorted.size() && sorted.get(insertPos) < squaredToInsert) {
        insertPos++;
      }

      sorted.add(insertPos, squaredToInsert);
    }

    return sorted.stream().mapToInt(i -> i).toArray();
  }

  /*
   * OPTION 3
   * O(1) space for insert, left, and right pointers
   * O(n) space for output array
   * O(n) time to iterate through array
   * = O(n) time and O(n) space
   */
  public static int[] sortedSquaredArray3(int[] array) {
    int[] output = new int[array.length];
    int left = 0;
    int right = array.length - 1;

    for (int insertPos = array.length - 1; insertPos > -1; insertPos--) {
      int valueLeft = array[left] * array[left];
      int valueRight = array[right] * array[right];

      if (valueLeft >= valueRight) {
        output[insertPos] = valueLeft;
        left++;
      } else {
        output[insertPos] = valueRight;
        right--;
      }
    }

    return output;
  }

  public static void main(String[] args) {

    int[][] testCases = new int[][] {
        new int[] {}, // []
        new int[] { 0 }, // [0]
        new int[] { 5 }, // [25]
        new int[] { -5 }, // [25]
        new int[] { 1, 2, 3 }, // [1, 4, 9]
        new int[] { -3, -2, -1 }, // [1, 4, 9]
        new int[] { -10, -8, -3, 0, 2, 3, 5, 11 } // [0, 4, 9, 9, 25, 64, 100, 121]
    };

    for (int[] testCase : testCases) {
      System.out
          .println(Arrays.toString(SortedSquaredArray.sortedSquaredArray(Arrays.copyOf(testCase, testCase.length))));
      System.out
          .println(Arrays.toString(SortedSquaredArray.sortedSquaredArray2(Arrays.copyOf(testCase, testCase.length))));
      System.out
          .println(Arrays.toString(SortedSquaredArray.sortedSquaredArray3(Arrays.copyOf(testCase, testCase.length))));
      System.out.println();
    }
  }
}