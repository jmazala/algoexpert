class MinNumberOfJumps {

  // O(n) time, O(1) space
  public static int minNumberOfJumps(int[] array) {
    if (array.length == 1) {
      return 0;
    }

    int jumps = 0;
    int furthest = array[0];
    int steps = array[0];

    for (int i = 1; i < array.length - 1; i++) {
      furthest = Math.max(furthest, i + array[i]);
      steps--;

      if (steps == 0) {
        jumps++;
        steps = furthest - i;
      }
    }

    return jumps + 1;
  }

  // O(n) time, O(n) space
  // public static int minNumberOfJumps(int[] array) {
  // int[] dp = new int[array.length];
  // Arrays.fill(dp, Integer.MAX_VALUE);
  // dp[0] = 0;

  // for (int i = 0; i < array.length; i++) {
  // int jumps = array[i];
  // while (jumps > 0) {
  // int next = i + jumps;
  // jumps--;
  // if (next >= array.length) {
  // continue;
  // }

  // dp[next] = Math.min(dp[next], dp[i] + 1);

  // }
  // }

  // return dp[dp.length - 1];
  // }

  public static void main(String[] args) {
    System.out.println(minNumberOfJumps(new int[] { 3, 4, 2, 1, 2, 3, 7, 1, 1, 1, 3 })); // 4
  }
}
