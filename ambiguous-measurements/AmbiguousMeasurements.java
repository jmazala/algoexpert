
// https://www.algoexpert.io/questions/ambiguous-measurements
import java.util.*;

class AmbiguousMeasurements {
  private static final char DELIMITER = '|';

  /*
   * Brute force, slow approach
   */
  public static boolean ambiguousMeasurements2(int[][] measuringCups, int low, int high) {
    Queue<int[]> queue = new LinkedList<>();
    queue.add(new int[] { 0, 0 });

    while (!queue.isEmpty()) {
      int[] ranges = queue.remove();
      int curLow = ranges[0];
      int curHigh = ranges[1];

      for (int[] cup : measuringCups) {
        int nextLow = curLow + cup[0];
        int nextHigh = curHigh + cup[1];

        if (nextHigh > high) {
          continue;
        }

        if (nextLow >= low && nextHigh <= high) {
          return true;
        }

        queue.add(new int[] { nextLow, nextHigh });
      }
    }

    return false;
  }

  /*
   * Form of backtracking with memoization. This is just like the sudoku problem.
   * Keep trying combinations until you find one that works.
   * 
   */
  public static boolean ambiguousMeasurements(int[][] measuringCups, int low, int high) {
    HashMap<String, Boolean> memo = new HashMap<>();
    return helper(measuringCups, low, high, memo);
  }

  private static boolean helper(int[][] measuringCups, int low, int high, HashMap<String, Boolean> memo) {
    String key = getKey(low, high);

    if (memo.containsKey(key)) {
      return memo.get(key);
    }

    boolean foundMeasurement = false;

    for (int[] currentCup : measuringCups) {
      int currentLow = currentCup[0];
      int currentHigh = currentCup[1];

      if (currentLow >= low && currentHigh <= high) {
        foundMeasurement = true;
        break;
      }

      int nextLow = low - currentLow;
      int nextHigh = high - currentHigh;

      if (nextLow <= 0 || nextHigh <= 0) {
        continue;
      }

      foundMeasurement = helper(measuringCups, nextLow, nextHigh, memo);
      if (foundMeasurement) {
        break;
      }
    }

    memo.put(key, foundMeasurement);
    return foundMeasurement;
  }

  private static String getKey(int low, int high) {
    return String.valueOf(low) + DELIMITER + String.valueOf(high);
  }

  public static void main(String[] args) {
    System.out.println(ambiguousMeasurements(new int[][] { { 200, 210 }, { 450, 465 }, { 800, 850 } }, 2100, 2300)); // true
  }
}
