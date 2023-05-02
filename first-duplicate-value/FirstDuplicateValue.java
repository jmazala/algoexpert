
// https://www.algoexpert.io/questions/first-duplicate-value
import java.util.*;

class FirstDuplicateValue {
  final static int PLACEHOLDER = 0;

  // O(n) time, O(n) space
  public int firstDuplicateValue(int[] array) {
    HashSet<Integer> set = new HashSet<>(array.length);

    for (int i = 0; i < array.length; i++) {
      if (set.add(array[i]) == false) {
        return array[i];
      }
    }
    return -1;
  }

  // O(n) time O(1) space
  public static int firstDuplicateValue2(int[] array) {
    /*
     * problem statement says that the values are between 1 and n
     * every value inside the array is in bounds
     */
    for (int i = 0; i < array.length; i++) {
      int val = Math.abs(array[i]);
      int newIndex = val - 1;
      if (array[newIndex] < 0) {
        return val;
      }

      array[newIndex] *= -1;
    }

    return -1;
  }

  /*
   * [2, 1, 5, 2, 3, 3, 4]
   * i = 0, val = 2, newIndex = 1
   * [2, -1, 5, 2, 3, 3, 4]
   * 
   * i = 1, val = 1, newIndex = 0
   * [-2, -1, 5, 2, 3, 3, 4]
   * i = 2, val = 5, newIndex = 4
   * [-2, -1, 5, 2, -3, 3, 4]
   * i = 3, val = 2, newIndex = 1
   * we found -1, therefore return val
   */
  //

  public static void main(String[] args) {
    System.out.println(FirstDuplicateValue.firstDuplicateValue2(new int[] { 2, 1, 5, 2, 3, 3, 4 }));
  }
}
