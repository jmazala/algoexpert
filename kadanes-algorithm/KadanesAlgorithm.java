// https://www.algoexpert.io/questions/kadane's-algorithm

public class KadanesAlgorithm {
  /*
   *
   * Keep track of the running max sum to account for negative numbers (so far)
   * At any given point in the array, the best maxSum is either that point,
   * or that point inclusive in the sum of all points before it
   * This is because we want consecutive ints only
   */
  public static int kadanesAlgorithm(int[] array) {
    int maxSum = Integer.MIN_VALUE;
    int currentSum = 0;

    for (int i = 0; i < array.length; i++) {
      currentSum = Math.max(array[i], currentSum + array[i]);
      maxSum = Math.max(maxSum, currentSum);
    }

    return maxSum;
  }

  public static void main(String[] args) {
    /*
     * maxSum: -INF
     * i = 0; a[i] = 3 ; cS = max(3, 0 + 3) = 3; mS = max(-Inf, 3) = 3
     * i = 1; a[i] = 5 ; cS = max(5, 5 + 3) = 8; mS = max(3, 8) = 8
     * i = 2; a[i] = -9; cS = max(-9, 8 + -9) = -1; mS = max(8, -1) = 8
     * i = 3; a[i] = 1; cS = max(1, -1) = 1; mS = max(8, 1) = 8
     * i = 4; a[i] = 3; cS = max(3, 3 + 1) = 4; mS = max(8, 4) = 8
     * i = 5; a[i] = -2; cS = max(-2, 4 + -2) = 2; mS = max(8, 2) = 8
     * i = 6; a[i] = 3; cS = max(3, 2 + 3) = 5; mS = max(8, 5) = 8
     * i = 7; a[i] = 4; cS = max(4, 5 + 4) = 9; mS = max(8, 9) = 9
     * i = 8; a[i] = 7; cS = max(7, 9 + 7) = 16; mS = max(9, 16) = 16
     * i = 9; a[i] = 2; cS = max(2, 16 + 2) = 18; mS = max(16, 18) = 18
     * i = 10; a[i] = -9; cS = max(-9, 18 + -9) = 9; mS = max(18, 9) = 18
     * i = 11; a[i] = 6; cS = max(6, 9 + 6) = 15; mS = max(18, 15) = 18
     * i = 12; a[i] = 3; cS = max(3, 15 + 3) = 18; mS = max(18, 18) = 18
     * i = 13; a[i] = 1; cS = max(1, 18 + 1) = 19; mS = max(18, 19) = 19
     * i = 14; a[i] = -5; cS = max(-5, 19 + -5) = 14; mS = max(19, 14) = 19
     * i = 15; a[i] = 4; cS = max(4, 14 + 4) = 18; mS = max(19, 18) = 19
     */
    System.out.println(kadanesAlgorithm(new int[] { 3, 5, -9, 1, 3, -2, 3, 4, 7, 2, -9, 6, 3, 1, -5, 4 })); // 19
  }
}
