// https://www.algoexpert.io/questions/number-of-ways-to-traverse-graph

import java.util.*;

class NumberOfWaysToTraverseGraph {

  public int numberOfWaysToTraverseGraph(int width, int height) {
    int[][] dp = new int[height][width];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        if (i == 0 || j == 0) {
          dp[i][j] = 1;
          continue;
        }

        dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
      }
    }

    return dp[height - 1][width - 1];
  }
}
