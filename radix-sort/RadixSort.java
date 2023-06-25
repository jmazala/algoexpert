// https://www.algoexpert.io/questions/radix-sort

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class RadixSort {
  /*
   * Very efficient sorting algorithm when the digits you are sorting
   * have a very narrow distribution of # of digits.
   * For instance, sorting test scores would be excellent because
   * they're all
   * 1-3 digit numbers (mostly 2 digits). You'll end up with a linear
   * sorting algorithm.
   * 
   * RadixSort relies on calling counting sort d times where d is the largest
   * # of digits in a number.
   * 
   * TIME: O(d * (n + b)) where d is number of digits of base b (base 10 for us,
   * base 2 for binary). You can see that d is multiplicative. Binary (base 2) is
   * better than base 10.
   * Counting sort is O(n+b) because counts array is b.
   * SPACE: O(n + b) where b is base of numbering system (i.e. base 10 for us).
   * Counting sort is O(n + b) space. And RadixSort space is same as counting sort
   * 
   * NOTE: Radix sort is not necessarily bound to use counting sort as its method.
   * It just means sorting by each digits. There could be other ways to do it
   * (i.e. quick sort by digit but track indices in a stable manner) That would
   * change time complexity of radix sort. Makes sense to use counting sort
   * because it could be linear.
   * Otherwise you'd use a n log n algorithm and you'd end up with a O(d * (n log
   * n)) time complexity (so whats the point?)
   * 
   * NOTE: If you have negative numbers, Radix sort will be differently because
   * you have to count negative digits.
   * The algorithm below will not work with negative digits.
   * You'd probably have to sort negative numbers and positive numbers separately,
   * treating
   * the negative numbers as positive numbers but marking them as negative
   * somewhere.
   * i.e. concat reversed radix sorted negative numbers with radix sorted positive
   * numbers
   */
  public static ArrayList<Integer> radixSort(ArrayList<Integer> array) {
    if (array.size() < 2) {
      return array;
    }

    int maxNumber = Collections.max(array);

    // Run counting sort on each digit place
    for (int digit = 0; (maxNumber / Math.pow(10, digit)) > 0; digit++) {
      array = countingSort(array, digit);
    }

    return array;
  }

  private static int getDigitFromNumber(int number, int digit) {
    int digitColumn = (int) Math.pow(10, digit);
    return (int) Math.floor(number / digitColumn) % 10;
  }

  private static ArrayList<Integer> countingSort(ArrayList<Integer> array, int digit) {
    Integer[] sorted = new Integer[array.size()];
    int[] counts = new int[10];

    // Extract the correct place digit from each number
    for (int number : array) {
      int digitValue = getDigitFromNumber(number, digit);
      counts[digitValue]++;
    }

    /*
     * loop through count array and modify values to get indices to place values
     */
    for (int i = 1; i < 10; i++) {
      counts[i] += counts[i - 1];
    }

    for (int i = array.size() - 1; i >= 0; i--) {
      int number = array.get(i);
      int digitValue = getDigitFromNumber(number, digit);
      counts[digitValue]--;
      int sortedIndex = counts[digitValue];
      sorted[sortedIndex] = number;
    }

    return new ArrayList<Integer>(Arrays.asList(sorted));
  }

  public static void main(String[] args) {
    System.out.println(radixSort(new ArrayList<>(Arrays.asList(8762, 654, 3008, 345, 87, 65, 234, 12, 2))).toString());
  }
}
