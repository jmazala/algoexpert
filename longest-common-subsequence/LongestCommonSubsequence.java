// https://www.algoexpert.io/questions/longest-common-subsequence

import java.util.List;
import java.util.stream.Collectors;

class LongestCommonSubsequence {

  /*
   * Use dynamic programming here
   * Consider all substrings of length 1 to n of str1
   * against all substrings of length 1 to n of str2.
   * i.e.ZX vs XKYKZPW, ZXV, ZXVV, and so forth.
   * At each comparison, the matrix stores what the longest common subsequence
   * would be if we just added 1 additional character to (either) string
   * This both greatly simplifies the problem and also gives us a visible
   * intermediary
   * (the matrix) to consult while the problem is being solved.
   * 
   * TIME: O(n1 * n2) where n1 is length of str1 and n2 is length of str2
   * SPACE: O(n1 * n2) for dp matrix
   */
  public static List<Character> longestCommonSubsequence(String str1, String str2) {
    // 2d matrix
    String[][] matrix = new String[str1.length() + 1][str2.length() + 1];

    for (int i = 0; i <= str1.length(); i++) {
      for (int j = 0; j <= str2.length(); j++) {
        if (i == 0 || j == 0) {
          matrix[i][j] = "";
          continue;
        }

        if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
          matrix[i][j] = matrix[i - 1][j - 1] + str1.charAt(i - 1);
        } else {
          matrix[i][j] = matrix[i - 1][j].length() > matrix[i][j - 1].length() ? matrix[i - 1][j] : matrix[i][j - 1];
        }
      }
    }

    // This is just a fancy way of adding all the chars into a list
    return matrix[str1.length()][str2.length()].chars().mapToObj(c -> (char) c)
        .collect(Collectors.toList());
  }

  public static void main(String[] args) {
    System.out.println(longestCommonSubsequence("ZXVVYZW", "XKYKZPW").toString()); // [X, Y, Z, W]
  }
}
