// https://www.algoexpert.io/questions/smallest-difference

import java.util.*;

public class SmallestDifference {
  /*
   * TIME: O(a log a / b log b for sorting), then O(a + b) for iterating where its
   * the length of each array
   * SPACE: O(1)
   */
  public static int[] smallestDifference(int[] arrayOne, int[] arrayTwo) {
    Arrays.sort(arrayOne);
    Arrays.sort(arrayTwo);

    int currentMinDifference = Integer.MAX_VALUE;
    int[] output = new int[2];

    int i = 0;
    int j = 0;

    while (i < arrayOne.length && j < arrayTwo.length) {
      int difference = Math.abs(arrayOne[i] - arrayTwo[j]);
      if (difference < currentMinDifference) {
        currentMinDifference = difference;
        output[0] = arrayOne[i];
        output[1] = arrayTwo[j];

        if (currentMinDifference == 0) {
          break;
        }
      }

      if (arrayOne[i] > arrayTwo[j]) {
        j++;
      } else {
        i++;
      }
    }

    return output;
  }

  public static void main(String[] args) {
    System.out.println(
        Arrays.toString(smallestDifference(new int[] { -1, 5, 10, 20, 28, 3 }, new int[] { 26, 134, 135, 15, 17 })));
  }

}
