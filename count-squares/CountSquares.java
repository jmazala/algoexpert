// https://www.algoexpert.io/questions/count-squares

import java.util.*;

class CountSquares {
  final static char DELIMITER = ',';

  /*
   * BRUTE FORCE
   * Store all points in a set of strings "x,y"
   * Iterate through the points again and look for all 4 squares
   */
  public static int countSquares(int[][] points) {
    Set<String> pointSet = new HashSet<>();

    for (int[] point : points) {
      pointSet.add(getKey(point));
    }

    int count = 0;

    for (int[] a : points) {
      for (int[] b : points) {
        if (a == b) {
          continue;
        }

        if (isSquare(a, b, pointSet)) {
          count++;
        }
      }
    }

    return count / 4;
  }

  private static boolean isSquare(int[] a, int[] b, Set<String> pointSet) {
    double[] c = new double[2];
    double[] d = new double[2];

    double[] midpoint = new double[] { (a[0] + b[0]) / 2.0, (a[1] + b[1]) / 2.0 };
    double xDistanceFromMidpoint = a[0] - midpoint[0];
    double yDistanceFromMidpoint = a[1] - midpoint[1];

    c[0] = midpoint[0] + yDistanceFromMidpoint;
    c[1] = midpoint[1] - xDistanceFromMidpoint;

    d[0] = midpoint[0] - yDistanceFromMidpoint;
    d[1] = midpoint[1] + xDistanceFromMidpoint;

    if (c[0] % 1 != 0 || c[1] % 1 != 0 || d[0] % 1 != 0 || d[1] % 1 != 0) {
      return false;
    }

    return pointSet.contains(getKey(c)) && pointSet.contains(getKey(d));
  }

  private static String getKey(int[] point) {
    return getKey(point[0], point[1]);
  }

  private static String getKey(double[] point) {
    return getKey((int) point[0], (int) point[1]);
  }

  private static String getKey(int x, int y) {
    StringBuilder builder = new StringBuilder();
    builder.append(x);
    builder.append(DELIMITER);
    builder.append(y);
    return builder.toString();
  }

  public static void main(String[] args) {
    int[][] points = new int[][] {
        { 1, 1 },
        { 0, 0 },
        { -4, 2 },
        { -2, -1 },
        { 0, 1 },
        { 1, 0 },
        { -1, 4 }
    };
    System.out.println(countSquares(points));
  }
}
