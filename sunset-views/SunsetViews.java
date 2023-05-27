// https://www.algoexpert.io/questions/sunset-views

import java.util.*;

class SunsetViews {
  /*
   * Brute force. O(n^2) time O(1) space
   */
  public static ArrayList<Integer> sunsetViews(int[] buildings, String direction) {
    ArrayList<Integer> output = new ArrayList<>();
    int multiplier = direction.equals("EAST") ? 1 : -1;

    for (int i = 0; i < buildings.length; i++) {
      int height = buildings[i];
      boolean canSeeSunset = true;

      for (int j = 1; j < buildings.length; j++) {
        if (!canSeeSunset) {
          break;
        }

        int nextBuildingIndex = (multiplier * j) + i;

        if (nextBuildingIndex < 0 || nextBuildingIndex >= buildings.length) {
          break;
        }

        int nextBuildingHeight = buildings[nextBuildingIndex];
        canSeeSunset = nextBuildingHeight < height;
      }

      if (canSeeSunset) {
        output.add(i);
      }
    }

    return output;
  }

  /*
   * Store maximum height after a building in an array
   */
  public static ArrayList<Integer> sunsetViews2(int[] buildings, String direction) {
    ArrayList<Integer> output = new ArrayList<>();

    int maxHeight = Integer.MIN_VALUE;
    int i = buildings.length - 1;
    int step = -1;

    if (direction.equals("WEST")) {
      i = 0;
      step = 1;
    }

    while (i >= 0 && i < buildings.length) {
      int height = buildings[i];

      if (height > maxHeight) {
        int insertionIndex = direction.equals("EAST") ? 0 : output.size();
        output.add(insertionIndex, i);
      }

      maxHeight = Math.max(height, maxHeight);
      i += step;
    }

    return output;
  }

  public static void main(String[] args) {
    System.out.println(SunsetViews.sunsetViews2(new int[] { 3, 5, 4, 4, 3, 1, 3, 2 }, "EAST").toString()); // [1, 3, 6,
                                                                                                           // 7]
    System.out.println(SunsetViews.sunsetViews2(new int[] { 3, 5, 4, 4, 3, 1, 3, 2 }, "WEST").toString()); // [0, 1]

    System.out.println(
        SunsetViews.sunsetViews2(new int[] { 1, 2, 3, 1, 5, 6, 9, 1, 9, 9, 11, 10, 9, 12, 8 }, "WEST").toString());
  }

}
