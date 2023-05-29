// https://www.algoexpert.io/questions/spiral-traverse

import java.util.*;

public class SpiralTraverse {
  public static List<Integer> spiralTraverse(int[][] array) {
    List<Integer> output = new ArrayList<>();
    int n = array.length;
    int m = array[0].length;

    int startingRow = 0;
    int endingRow = n - 1;
    int startingColumn = 0;
    int endingColumn = m - 1;

    while (startingRow <= endingRow && startingColumn <= endingColumn) {
      // RIGHT
      for (int j = startingColumn; j <= endingColumn; j++) {
        output.add(array[startingRow][j]);
      }

      // DOWN
      for (int i = startingRow + 1; i <= endingRow; i++) {
        output.add(array[i][endingColumn]);
      }

      // LEFT
      for (int j = endingColumn - 1; j >= startingColumn; j--) {
        /*
         * edge case where single row in middle of matrix
         * don't couble count
         */
        if (startingRow == endingRow) {
          break;
        }

        output.add(array[endingRow][j]);
      }

      // UP
      for (int i = endingRow - 1; i > startingRow; i--) {
        /*
         * edge case where single column in middle of matrix
         * don't double count
         */

        if (startingColumn == endingColumn) {
          break;
        }

        output.add(array[i][startingColumn]);
      }

      startingRow++;
      endingRow--;
      startingColumn++;
      endingColumn--;
    }

    return output;
  }

  public static void main(String[] args) {
    int[][] array = new int[][] { { 1, 2, 3, 4 }, { 12, 13, 14, 5 }, { 11, 16, 15, 6 }, { 10, 9, 8, 7 } };
    System.out.println(spiralTraverse(array).toString());
  }
}
