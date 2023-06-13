// https://www.algoexpert.io/questions/staircase-traversal

class StaircaseTraversal {

  public static int staircaseTraversal(int height, int maxSteps) {
    int[] dp = new int[height + 1];
    dp[0] = 1;

    for (int i = 1; i <= height; i++) {
      for (int distance = 1; distance <= maxSteps; distance++) {
        int prev = i - distance;
        if (prev < 0) {
          continue;
        }

        dp[i] += dp[prev];
      }
    }

    return dp[height];
  }

  public static void main(String[] args) {
    System.out.println(StaircaseTraversal.staircaseTraversal(4, 2)); // 5
  }
}
