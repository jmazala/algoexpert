// https://www.algoexpert.io/questions/transpose-matrix

import java.util.*;

class TransposeMatrix {
  public static int[][] transposeMatrix(int[][] matrix) {
    int m = matrix.length;
    int n = matrix[0].length;
    int[][] output = new int[n][m];
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        output[j][i] = matrix[i][j];
      }
    }

    return output;
  }

  public static void main(String[] args) {
    int[][] output = transposeMatrix(new int[][] { { 1, 2 } });
    for (int[] row : output) {
      System.out.println(Arrays.toString(row));
    }

    output = transposeMatrix(new int[][] { { 1, 2 }, { 3, 4 }, { 5, 6 } });
    for (int[] row : output) {
      System.out.println(Arrays.toString(row));
    }
  }
}
