// https://www.algoexpert.io/questions/reverse-words-in-string

import java.util.*;

class ReverseWordsInString {

  public static String reverseWordsInString(String string) {
    char[] characters = string.toCharArray();
    ReverseWordsInString.reverseArray(characters, 0, characters.length - 1);

    int start = 0;

    while (start < string.length()) {
      int end = start;
      while (end < string.length() && characters[end] != ' ') {
        end++;
      }

      ReverseWordsInString.reverseArray(characters, start, end - 1);
      start = end + 1;
    }

    return new String(characters);
  }

  private static void reverseArray(char[] array, int start, int end) {
    while (end > start) {
      char temp = array[start];
      array[start] = array[end];
      array[end] = temp;
      start++;
      end--;
    }
  }

  public static void main(String[] args) {
    String[] words = new String[] { "tim is great", "whitespaces    4", "AlgoExpert is  the   best!" };

    for (String word : words) {
      System.out.println(ReverseWordsInString.reverseWordsInString(word));
    }

  }
}
