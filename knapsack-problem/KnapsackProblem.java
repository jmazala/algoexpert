// https://www.algoexpert.io/questions/knapsack-problem

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class KnapsackProblem {
  /*
   * Easy to solve by hand, intuitively with a few items. But with more items its
   * more or less impossible.
   * TIME: O(n * c) where n is # of items, c is capacity
   * SPACE: O(n*c)
   */
  public static List<List<Integer>> knapsackProblem(int[][] items, int capacity) {
    List<List<Integer>> result = new ArrayList<List<Integer>>();

    /*
     * Build a 2D array holding greatest values we can put in a knapsack
     * with the restriction being capacity / index of each item used
     * Java initializes these with 0
     */

    int[][] maxValues = new int[items.length + 1][capacity + 1];

    for (int i = 1; i <= items.length; i++) {
      int[] currentItem = items[i - 1];
      int currentValue = currentItem[0];
      int currentWeight = currentItem[1];

      for (int currentCapacity = 0; currentCapacity <= capacity; currentCapacity++) {
        if (currentWeight > currentCapacity) {
          maxValues[i][currentCapacity] = maxValues[i - 1][currentCapacity];
        } else {
          maxValues[i][currentCapacity] = Math.max(maxValues[i - 1][currentCapacity],
              currentValue + maxValues[i - 1][currentCapacity - currentWeight]);
        }
      }
    }

    result.add(Arrays.asList(maxValues[items.length][capacity]));
    result.add(getItemsUsed(maxValues, items));
    return result;
  }

  private static List<Integer> getItemsUsed(int[][] maxValues, int[][] items) {
    List<Integer> itemsUsed = new LinkedList<>();

    int i = maxValues.length - 1;
    int j = maxValues[0].length - 1;

    while (i > 0) {
      /*
       * We used item (i-1).
       * Mark that and subtract its weight
       */
      if (maxValues[i - 1][j] < maxValues[i][j]) {
        itemsUsed.add(i - 1);
        j -= items[i - 1][1];
      }

      i--;
    }

    return itemsUsed;
  }

  public static void main(String[] args) {
    List<List<Integer>> output = knapsackProblem(new int[][] { { 1, 2 }, { 4, 3 }, { 5, 6 }, { 6, 7 } }, 10);
    System.out.println(output.get(0).toString() + "," + output.get(1).toString());
  }
}
