import java.util.*;

class RemoveIslands {
  private static final int WATER = 0;
  private static final int LAND = 1;
  private static final int LAND_NOT_ISLAND = 2;
  private static final int LAND_ISLAND = 3;

  /*
   * TIME:
   * O(h + w) for perimiter
   * O(hw) for inner point loop
   * SPACE:
   * O(1) for height and width
   * O(hw) for recursion stack
   */
  public static int[][] removeIslands(int[][] matrix) {
    int height = matrix.length;
    int width = matrix[0].length;

    // Label all the perimiter points
    for (int row = 0; row < height; row++) {
      if (matrix[row][0] == LAND) {
        matrix[row][0] = LAND_NOT_ISLAND;
      }

      if (matrix[row][width - 1] == LAND) {
        matrix[row][width - 1] = LAND_NOT_ISLAND;
      }
    }

    for (int col = 0; col < width; col++) {
      if (matrix[0][col] == LAND) {
        matrix[0][col] = LAND_NOT_ISLAND;
      }

      if (matrix[height - 1][col] == LAND) {
        matrix[height - 1][col] = LAND_NOT_ISLAND;
      }
    }

    // Rest of the points
    for (int row = 1; row < height - 1; row++) {
      for (int col = 1; col < width - 1; col++) {
        visitLand(row, col, matrix, height, width);
      }
    }

    for (int row = 0; row < height; row++) {
      for (int col = 0; col < width; col++) {
        int val = matrix[row][col];
        if (val == LAND_NOT_ISLAND) {
          matrix[row][col] = LAND;
        } else if (val == LAND_ISLAND) {
          matrix[row][col] = WATER;
        }
      }
    }

    return matrix;
  }

  private static void visitLand(int row, int col, int[][] matrix, int height, int width) {
    int val = matrix[row][col];
    if (val == WATER || val == LAND_NOT_ISLAND) {
      return;
    }

    // we are traversing right and then down
    int upVal = matrix[row - 1][col];
    if (upVal == LAND_NOT_ISLAND) {
      matrix[row][col] = LAND_NOT_ISLAND;
      return;
    }

    int leftVal = matrix[row][col - 1];
    if (leftVal == LAND_NOT_ISLAND) {
      matrix[row][col] = LAND_NOT_ISLAND;
      return;
    }

    if (col < width - 1) {
      visitLand(row, col + 1, matrix, height, width);
      int rightVal = matrix[row][col + 1];
      if (rightVal == LAND_NOT_ISLAND) {
        matrix[row][col] = LAND_NOT_ISLAND;
        return;
      }
    }

    if (row < height - 1) {
      visitLand(row + 1, col, matrix, height, width);
      int downVal = matrix[row + 1][col];
      if (downVal == LAND_NOT_ISLAND) {
        matrix[row][col] = LAND_NOT_ISLAND;
        return;
      }
    }

    matrix[row][col] = LAND_ISLAND;
  }

  public static void main(String[] args) {
    int[][] matrix = new int[][] {
        { 1, 0, 0, 0, 0, 0 },
        { 0, 1, 0, 1, 1, 1 },
        { 0, 0, 1, 0, 1, 0 },
        { 1, 1, 0, 0, 1, 0 },
        { 1, 0, 1, 1, 0, 0 },
        { 1, 0, 0, 0, 0, 1 }
    };

    RemoveIslands.removeIslands(matrix);

    for (int[] row : matrix) {
      System.out.println(Arrays.toString(row));
    }

    System.out.println(Arrays.toString(matrix));
  }
}
