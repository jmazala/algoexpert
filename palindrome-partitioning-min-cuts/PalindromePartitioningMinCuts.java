class PalindromePartitioningMinCuts {

  public static int palindromePartitioningMinCuts(String str) {
    boolean[][] palindromes = new boolean[str.length()][str.length()];
    for (int i = 0; i < str.length(); i++) {
      for (int j = 0; j < str.length(); j++) {
        palindromes[i][j] = (i == j);
      }
    }

    // palindromes length 2
    for (int i = 0; i < str.length() - 1; i++) {
      int j = i + 1;
      palindromes[i][j] = (str.charAt(i) == str.charAt(j));
    }

    // palindromes length 3 and beyond
    for (int length = 3; length <= str.length(); length++) {
      for (int i = 0; i + length <= str.length(); i++) {
        // Palindrome length 3 from 0 is 0 to 2
        int j = i + length - 1;
        palindromes[i][j] = str.charAt(i) == str.charAt(j) && palindromes[i + 1][j - 1];
      }
    }

    int[] minCuts = new int[str.length()];
    for (int i = 0; i < minCuts.length; i++) {
      if (palindromes[0][i]) {
        minCuts[i] = 0;
        continue;
      }

      if (minCuts[i - 1] == 0) {
        minCuts[i] = 1;
        continue;
      }

      minCuts[i] = minCuts[i - 1] + 1;
      for (int j = 1; j < i; j++) {
        if (palindromes[j][i]) {
          minCuts[i] = Math.min(minCuts[i], minCuts[j - 1] + 1);
        }
      }
    }

    return minCuts[minCuts.length - 1];
  }
}
