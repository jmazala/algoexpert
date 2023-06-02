// https://www.algoexpert.io/questions/maximum-sum-submatrix

import java.util.*;

class MaximimSumSubmatrix {

  /*
   * Problem statement says size is a positive integer,
   * and m >= size and n >= size.
   * 
   * TIME: O(m*n*s) for 3 loops
   * SPACE: O(m*n*s) for dp array
   */
  public static int maximumSumSubmatrix(int[][] matrix, int size) {
    int m = matrix.length;
    int n = matrix[0].length;

    // dp matrix starting from top left position i,j of size s
    int[][][] dp = new int[m][n][size + 1];
    int answer = Integer.MIN_VALUE;

    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        for (int s = 1; s <= size; s++) {
          if ((i + s) > m || (j + s) > n) {
            continue;
          }

          int sum = dp[i][j][s - 1];

          for (int t = 0; t < s; t++) {
            sum += matrix[i + t][j + s - 1];
          }

          for (int t = 0; t < s - 1; t++) {
            sum += matrix[i + s - 1][j + t];
          }

          dp[i][j][s] = sum;

          if (s == size) {
            answer = Math.max(sum, answer);
          }
        }
      }
    }

    return answer;
  }

  public static void main(String[] args) {
    int[][] matrix = {
        { 5, 3, -1, 5 },
        { -7, 3, 7, 4 },
        { 12, 8, 0, 0 },
        { 1, -8, -8, 2 }
    };

    System.out.println(maximumSumSubmatrix(matrix, 1)); // 12
    System.out.println(maximumSumSubmatrix(matrix, 2)); // 18

    int[][] matrix2 = {
        { 2, 4 },
        { 5, 6 },
        { -3, 2 }
    };

    System.out.println(maximumSumSubmatrix(matrix2, 2)); // 17

    int[][] matrix3 = {
        { -1, -2, -3, -4, -5 },
        { -5, -4, -3, -2, -1 },
        { -1, -2, -3, -4, -5 }
    };

    System.out.println(maximumSumSubmatrix(matrix3, 2)); // -12
  }
}
