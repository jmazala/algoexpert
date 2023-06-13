// https://www.algoexpert.io/questions/monotonic-array

public class MonotonicArray {
  public static boolean isMonotonic(int[] array) {
    if (array.length < 3) {
      return true;
    }

    Boolean isNonIncreasing = null;

    for (int i = 0; i < array.length - 1; i++) {
      if (array[i] == array[i + 1]) {
        continue;
      }

      if (isNonIncreasing == null) {
        isNonIncreasing = array[i] > array[i + 1];
      }

      if (isNonIncreasing && array[i + 1] > array[i]) {
        return false;
      }

      if (!isNonIncreasing && array[i + 1] < array[i]) {
        return false;
      }
    }

    return true;
  }

  /*
   * Exact same as above just some may prefer this to be more readable
   */
  public static boolean isMonotonic2(int[] array) {
    boolean isNonDecreasing = false;
    boolean isNonIncreasing = false;

    for (int i = 0; i < array.length - 1; i++) {
      if (array[i] == array[i + 1]) {
        continue;
      }

      if (array[i] < array[i + 1]) {
        isNonDecreasing = true;
      }

      if (array[i] > array[i + 1]) {
        isNonIncreasing = true;
      }

      if (isNonDecreasing && isNonIncreasing) {
        return false;
      }
    }

    return true;
  }

  public static void main(String[] args) {
    System.out.println(isMonotonic(new int[] { 0, -1, 0 })); // false
    System.out.println(isMonotonic(new int[] { 0, 1, 0 })); // false
    System.out.println(isMonotonic(new int[] { 0, 1 })); // true
    System.out.println(isMonotonic(new int[] { 1 })); // true
    System.out.println(isMonotonic(new int[] { 1, 1 })); // true
    System.out.println(isMonotonic(new int[] {})); // true
    System.out.println(isMonotonic(new int[] { -1, -5, -10, -1100, -1100, -1101, -1102, -9001 })); // true
    System.out.println(isMonotonic(new int[] { 1, 2, 3 })); // true
    System.out.println(isMonotonic(new int[] { 1, 1, 2, 3, 4, 5, 5, 5, 6, 7, 8, 8, 9, 10, 11 }));
  }

}
