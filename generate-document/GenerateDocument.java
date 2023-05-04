
// https://www.algoexpert.io/questions/generate-document

import java.util.*;

class GenerateDocument {

  /*
   * METHOD 1 - Use a hash of char to count
   * METHOD 2 - Sort both strings and iterate through document. characters can
   * have
   * additional chars leftover so account for it using 2 pointers.
   */

  /*
   * METHOD 1 - use a hash
   * TIME: = O(c + d) = O(max(c, d))
   * O(c) for char map where c is length of characters
   * O(d) where d is length of document
   * SPACE
   * O(c) for char map
   */
  public static boolean generateDocument(String characters, String document) {
    Map<Character, Integer> charMap = new HashMap<>();

    for (char c : characters.toCharArray()) {
      int count = charMap.getOrDefault(c, 0);
      charMap.put(c, count + 1);
    }

    for (char c : document.toCharArray()) {
      int count = charMap.getOrDefault(c, 0);
      if (count < 1) {
        return false;
      }

      charMap.put(c, count - 1);
    }

    return true;
  }

  /*
   * TIME:
   * O(d log d + c log c) for sorting strings
   * O(c + d) for iterating through them
   * = O(c log c)
   * SPACE:
   * O(1) for the pointers
   */
  public static boolean generateDocument2(String characters, String document) {
    String sortedCharacters = GenerateDocument.sortString(characters);
    String sortedDocument = GenerateDocument.sortString(document);

    int iCharacters = 0;
    int iDocument = 0;

    while (iDocument < sortedDocument.length()) {
      while (iCharacters < sortedCharacters.length()
          && sortedCharacters.charAt(iCharacters) < sortedDocument.charAt(iDocument)) {
        iCharacters++;
      }

      if (iCharacters == sortedCharacters.length()
          || sortedCharacters.charAt(iCharacters) != sortedDocument.charAt(iDocument)) {
        return false;
      }

      iCharacters++;
      iDocument++;
    }

    return true;
  }

  private static String sortString(String s) {
    char[] charArray = s.toCharArray();
    Arrays.sort(charArray);
    return new String(charArray);
  }

  public static void main(String[] args) {
    System.out.println(GenerateDocument.generateDocument2("abc", "abcd")); // false
    System.out.println(GenerateDocument.generateDocument2("", "1")); // false
    System.out.println(GenerateDocument.generateDocument2("", "")); // true
    System.out.println(GenerateDocument.generateDocument2("aaab", "baa")); // true
    System.out.println(GenerateDocument.generateDocument2("aaabc", "baa")); // true
    System.out.println(GenerateDocument.generateDocument2("algoexpert is the best", "tseb eht si trepxeogla")); // true
  }
}
