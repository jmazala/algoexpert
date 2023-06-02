import java.util.*;

class LongestCommonSubsequence {
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

    List<Character> answer = new ArrayList<>(matrix[str1.length()][str2.length()].length());
    for (char c : matrix[str1.length()][str2.length()].toCharArray()) {
      answer.add(c);
    }

    return answer;
  }
}
