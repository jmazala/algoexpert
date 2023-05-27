// https://www.algoexpert.io/questions/three-number-sort

import java.util.*;

class ThreeNumberSort {
  // public static int[] threeNumberSort(int[] array, int[] order) {
  // int p0 = 0;
  // int p1 = 0;
  // int p2 = 0;

  // int p0Val = order[0];
  // int p1Val = order[1];
  // int p2Val = order[2];

  // int length = array.length;

  // while (true) {
  // /*
  // * Move pointers up
  // */

  // if (p0 < length && array[p0] != p0Val) {
  // p0++;
  // continue;
  // }

  // if (p1 < length && array[p1] != p1Val) {
  // p1++;
  // continue;
  // }

  // if (p2 < length && array[p2] != p2Val) {
  // p2++;
  // continue;
  // }

  // /*
  // * Swaps
  // */

  // if (p1 < length && p2 < length && p1 > p2) {
  // swap(array, p1, p2);
  // continue;
  // }

  // if (p0 < length && p2 < length && p0 > p2) {
  // swap(array, p0, p2);
  // continue;
  // }

  // if (p0 < length && p1 < length && p0 > p1) {
  // swap(array, p0, p1);
  // continue;
  // }

  // if (p0 < length) {
  // p0++;
  // continue;
  // }

  // if (p1 < length) {
  // p1++;
  // continue;
  // }

  // if (p2 < length) {
  // p2++;
  // continue;
  // }

  // break;
  // }

  // return array;
  // }

  /*
   * Remember counts
   * O(2n) time with 2 passes through the array
   * O(1) space
   */
  public static int[] threeNumberSort(int[] array, int[] order) {
    int p0Val = order[0];
    int p1Val = order[1];
    int p2Val = order[2];

    int p0Count = 0;
    int p1Count = 0;

    for (int i : array) {
      if (i == p0Val) {
        p0Count++;
      } else if (i == p1Val) {
        p1Count++;
      }
    }

    for (int i = 0; i < array.length; i++) {
      if (p0Count > 0) {
        array[i] = p0Val;
        p0Count--;
      } else if (p1Count > 0) {
        array[i] = p1Val;
        p1Count--;
      } else {
        array[i] = p2Val;
      }
    }

    return array;
  }

  /*
   * Keep track of where low and high values belong
   * Iterate through the array one time and swap values where you can
   * TIME: O(n)
   * SPACE: O(1)
   */
  public static int[] threeNumberSort2(int[] array, int[] order) {
    int lowVal = order[0];
    int highVal = order[2];

    int lowIndex = 0;
    int highIndex = array.length - 1;

    while (lowIndex < array.length && array[lowIndex] == lowVal) {
      lowIndex++;
    }

    while (highIndex >= 0 && array[highIndex] == highVal) {
      highIndex--;
    }

    int i = lowIndex;

    while (i < array.length) {
      if (array[i] == lowVal) {
        if (i < lowIndex) {
          i++;
          continue;
        }

        swap(array, lowIndex, i);
        lowIndex++;
        continue;
      }

      if (array[i] == highVal) {
        if (i > highIndex) {
          i++;
          continue;
        }

        swap(array, highIndex, i);
        highIndex--;
        continue;
      }

      i++;
    }

    return array;
  }

  private static void swap(int[] array, int p0, int p1) {
    int temp = array[p0];
    array[p0] = array[p1];
    array[p1] = temp;
  }

  public static void main(String[] args) {
    int[] array = new int[] { 1, 0, 0, -1, -1, 0, 1, 1 };
    ThreeNumberSort.threeNumberSort(array, new int[] { 0, 1, -1 });
    System.out.println(Arrays.toString(array)); // 0, 0, 0, 1, 1, 1, -1, -1]

    int[] a2 = new int[] {};
    ThreeNumberSort.threeNumberSort(a2, new int[] { 0, 1, -1 });
    System.out.println(Arrays.toString(a2)); // []

    int[] a3 = new int[] { 0, 1 };
    ThreeNumberSort.threeNumberSort(a3, new int[] { 0, 1, -1 });
    System.out.println(Arrays.toString(a3)); // [0, 1]

    int[] a4 = new int[] { 1, 0 };
    ThreeNumberSort.threeNumberSort(a4, new int[] { 0, 1, -1 });
    System.out.println(Arrays.toString(a4)); // [0, 1]

    int[] a5 = new int[] { -2, -3, -3, -3, -3, -3, -2, -2, -3 };
    ThreeNumberSort.threeNumberSort(a5, new int[] { -2, -3, 0 });
    System.out.println(Arrays.toString(a5)); // [-2, -2, -2, -3, -3, -3, -3, -3, -3]

    int[] a8 = new int[] { -2, -3, -3, -3, -3, -3, -2, -2, -3 };
    ThreeNumberSort.threeNumberSort2(a8, new int[] { -2, -3, 0 });
    System.out.println(Arrays.toString(a8)); // [-2, -2, -2, -3, -3, -3, -3, -3, -3]

    int[] a6 = new int[] { 0, 10, 10, 10, 10, 10, 25, 25, 25, 25, 25 };
    ThreeNumberSort.threeNumberSort(a6, new int[] { 25, 10, 0 });
    System.out.println(Arrays.toString(a6)); // [25, 25, 25, 25, 25, 10, 10, 10, 10, 10, 0]

    int[] a7 = new int[] { 0, 10, 10, 10, 10, 10, 25, 25, 25, 25, 25 };
    ThreeNumberSort.threeNumberSort2(a7, new int[] { 25, 10, 0 });
    System.out.println(Arrays.toString(a7)); // [25, 25, 25, 25, 25, 10, 10, 10, 10, 10, 0]
  }
}
