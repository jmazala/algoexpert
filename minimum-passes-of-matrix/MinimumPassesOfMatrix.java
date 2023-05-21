// https://www.algoexpert.io/questions/minimum-passes-of-matrix

import java.util.*;

class MinimumPassesOfMatrix {

  /*
   * TIME:
   * O (hw) for each pass, which is really O((hw)^2)
   * SPACE: O(hw) worst case scenario
   * 
   * Can we do better by storing the coordinates of negative ones
   * and iterating through that instead?
   */
  public int minimumPassesOfMatrix(int[][] matrix) {
    int height = matrix.length;
    int width = matrix[0].length;

    int passes = 0;
    boolean foundNegative;
    ArrayList<int[]> coordinatesToFlip;

    while (true) {
      coordinatesToFlip = new ArrayList<>();
      foundNegative = false;

      for (int row = 0; row < height; row++) {
        for (int col = 0; col < width; col++) {
          int val = matrix[row][col];
          if (val >= 0) {
            continue;
          }

          foundNegative = true;
          if (MinimumPassesOfMatrix.canFlip(row, col, matrix, height, width)) {
            coordinatesToFlip.add(new int[] { row, col });
          }
        }
      }

      if (foundNegative && coordinatesToFlip.isEmpty()) {
        return -1;
      }

      if (!foundNegative) {
        return passes;
      }

      passes++;
      for (int[] coordinate : coordinatesToFlip) {
        int i = coordinate[0];
        int j = coordinate[1];
        matrix[i][j] *= -1;
      }
    }
  }

  public int minimumPassesOfMatrix2(int[][] matrix) {
    int height = matrix.length;
    int width = matrix[0].length;

    Queue<int[]> queue = new LinkedList<>();

    for (int row = 0; row < height; row++) {
      for (int col = 0; col < width; col++) {
        int val = matrix[row][col];
        if (val < 0) {
          queue.add(new int[] { row, col });
        }
      }
    }

    int passes = 0;

    while (true) {
      if (queue.isEmpty()) {
        return passes;
      }

      int queueSize = queue.size();
      List<int[]> coordinatesToFlip = new LinkedList<>();

      for (int i = 0; i < queueSize; i++) {
        int[] coordinates = queue.remove();
        int row = coordinates[0];
        int col = coordinates[1];

        if (MinimumPassesOfMatrix.canFlip(row, col, matrix, height, width)) {
          coordinatesToFlip.add(new int[] { row, col });
        } else {
          queue.add(coordinates);
        }
      }

      if (coordinatesToFlip.isEmpty()) {
        return -1;
      }

      for (int[] coordinates : coordinatesToFlip) {
        int row = coordinates[0];
        int col = coordinates[1];
        matrix[row][col] *= -1;
      }

      passes++;
    }
  }

  private static boolean canFlip(int row, int col, int[][] matrix, int height, int width) {
    return (col > 0 && matrix[row][col - 1] > 0) || // left
        (col < width - 1 && matrix[row][col + 1] > 0) || // right
        (row > 0 && matrix[row - 1][col] > 0) || // top
        (row < height - 1 && matrix[row + 1][col] > 0); // bottom
  }
}
