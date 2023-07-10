// https://www.algoexpert.io/questions/longest-palindromic-substring

public class LongestPalindromicSubstring {
  /*
   * For every character in str, check the 2 palindrome cases:
   * 1 - single char in the middle ("aba")
   * 2 - double char in the middle ("abba")
   * For as long as the string is a palindrome and can be expanded, expand
   * Compare the palindromic strings with your answer
   */
  public static String longestPalindromicSubstring(String str) {
    if (str.length() < 2) {
      return str;
    }

    String answer = new String(new char[] { str.charAt(0) });

    for (int i = 1; i < str.length(); i++) {
      /*
       * A palindrome can start with a single char in the middle ("aba")
       * Or a pair of the same char in the middle ("abba")
       */
      if (str.charAt(i) == str.charAt(i - 1)) {
        String doublePalindrome = palindromicSubstringFrom(i - 1, i, str);
        if (doublePalindrome.length() > answer.length()) {
          answer = doublePalindrome;
        }
      }

      String singlePalindrome = palindromicSubstringFrom(i - 1, i + 1, str);

      if (singlePalindrome.length() > answer.length()) {
        answer = singlePalindrome;
      }
    }

    return answer;
  }

  private static String palindromicSubstringFrom(int start, int end, String str) {
    while (start >= 0 && end < str.length() && str.charAt(start) == str.charAt(end)) {
      start--;
      end++;
    }

    return str.substring(start + 1, end);
  }

  public static void main(String[] args) {
    System.out.println(longestPalindromicSubstring("a")); // a
    System.out.println(longestPalindromicSubstring("ab")); // a
    System.out.println(longestPalindromicSubstring("aba")); // aba
    System.out.println(longestPalindromicSubstring("abba")); // abba
    System.out.println(longestPalindromicSubstring("abaxyzzyxf")); // xyzzyx
  }
}
