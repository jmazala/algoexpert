import java.util.*;

class Program {

  /*
   * O(n) for array iterate
   * O(n log n) for sort
   * O(n) for iterate
   * = O(n log n) solution
   * O(1) for space (unless Arrays.sort isn't)
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
   * we have to sort it no matter what, we could do a mergesort
   */

  public static void main(String[] args) {
    System.out.println(Program.sortedSquaredArray(new int[] { 1, 2, 3, 5, 6, 8, 9 }));
  }
}