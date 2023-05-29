// https://www.algoexpert.io/questions/search-in-sorted-matrix

public class SearchInSortedMatrix {

  public static int[] searchInSortedMatrix(int[][] matrix, int target) {
    int i = 0;
    int j = matrix[0].length - 1;

    while (i < matrix.length && j < matrix[0].length) {
      int val = matrix[i][j];

      if (val == target) {
        return new int[] { i, j };
      }

      if (val < target) {
        j--;
      } else {
        i++;
      }
    }

    return new int[] { -1, -1 };
  }

}
