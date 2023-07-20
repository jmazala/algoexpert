// https://www.algoexpert.io/questions/maximize-expression

class MaximizeExpression {

  /*
   * Brute Force is O(n^4)
   */
  public static int maximizeExpression(int[] array) {
    if (array.length < 4) {
      return 0;
    }

    int answer = Integer.MIN_VALUE;

    for (int a = 0; a < array.length - 3; a++) {
      int aVal = array[a];
      for (int b = a + 1; b < array.length - 2; b++) {
        int bVal = array[b];
        for (int c = b + 1; c < array.length - 1; c++) {
          int cVal = array[c];
          for (int d = c + 1; d < array.length; d++) {
            int dVal = array[d];

            int output = aVal - bVal + cVal - dVal;
            answer = Math.max(answer, output);
          }
        }
      }
    }

    return answer;
  }

  public static int maximizeExpression2(int[] array) {
    if (array.length < 4) {
      return 0;
    }

    // store max(a) at any point i
    int[] maxA = new int[array.length - 1];
    maxA[0] = array[0];
    for (int i = 1; i < array.length - 1; i++) {
      maxA[i] = Math.max(maxA[i - 1], array[i]);
    }

    // store max(a-b) at any point i
    int[] maxAMinusB = new int[array.length - 1];
    maxAMinusB[0] = Integer.MIN_VALUE;
    maxAMinusB[1] = array[0] - array[1];
    for (int i = 2; i < array.length - 1; i++) {
      maxAMinusB[i] = Math.max(maxAMinusB[i - 1], maxA[i - 1] - array[i]);
    }

    // store max(a-b+c) at any point i
    int[] maxAMinusBPlusC = new int[array.length - 1];
    maxAMinusBPlusC[0] = Integer.MIN_VALUE;
    maxAMinusBPlusC[1] = Integer.MIN_VALUE;
    maxAMinusBPlusC[2] = array[0] - array[1] + array[2];
    for (int i = 3; i < array.length - 1; i++) {
      maxAMinusBPlusC[i] = Math.max(maxAMinusBPlusC[i - 1], maxAMinusB[i - 1] + array[i]);
    }

    // store max(a-b+c-d) at any point i
    int[] maxAMinusBPlusCMinusD = new int[array.length];
    maxAMinusBPlusCMinusD[0] = Integer.MIN_VALUE;
    maxAMinusBPlusCMinusD[1] = Integer.MIN_VALUE;
    maxAMinusBPlusCMinusD[2] = Integer.MIN_VALUE;
    maxAMinusBPlusCMinusD[3] = array[0] - array[1] + array[2] - array[3];
    for (int i = 4; i < array.length; i++) {
      maxAMinusBPlusCMinusD[i] = Math.max(maxAMinusBPlusCMinusD[i - 1], maxAMinusBPlusC[i - 1] - array[i]);
    }

    return maxAMinusBPlusCMinusD[array.length - 1];
  }

  public static void main(String[] args) {
    System.out.println(maximizeExpression2(new int[] { 3, 6, 1, -3, 2, 7 })); // 4
  }
}
