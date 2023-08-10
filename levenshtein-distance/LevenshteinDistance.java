// https://www.algoexpert.io/questions/levenshtein-distance

/*
 * This is a classic algorithm.  Don't think, just memorize it.
 * https://medium.com/@ethannam/understanding-the-levenshtein-distance-equation-for-beginners-c4285a5604f0
 * is a good resource
 */
public class LevenshteinDistance {
  public static int levenshteinDistance(String str1, String str2) {
    int[][] dp = new int[str1.length() + 1][str2.length() + 1];

    for (int i = 0; i <= str1.length(); i++) {
      dp[i][0] = i;
    }

    for (int i = 0; i <= str2.length(); i++) {
      dp[0][i] = i;
    }

    for (int i = 1; i <= str1.length(); i++) {
      char c1 = str1.charAt(i - 1);
      for (int j = 1; j <= str2.length(); j++) {
        char c2 = str2.charAt(j - 1);

        /*
         * dp[i-1][j-1] represents an edit
         * dp[i-1][j] or dp[i][j-1] represents adding a char to either string
         */
        int up = dp[i - 1][j] + 1;
        int left = dp[i][j - 1] + 1;
        int diagonal = dp[i - 1][j - 1] + ((c1 == c2) ? 0 : 1);

        // same as sorted asc [x1, x2, x3][0]
        int curDistance = Math.min(diagonal, Math.min(up, left)); // oh java....
        dp[i][j] = curDistance;
      }
    }

    return dp[str1.length()][str2.length()];
  }

  public static void main(String[] args) {
    System.out.println(levenshteinDistance("sitting", "kitten")); // 3
  }
}
