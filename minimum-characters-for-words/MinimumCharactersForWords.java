import java.util.*;

class MinimumCharactersForWords {

  /*
   * TIME: O(c) where c is total # of characters in words
   * SPACE: O(d) where d is distinct # of characters
   */
  public static char[] minimumCharactersForWords(String[] words) {
    Map<Character, Integer> charMap = new HashMap<>();

    for (String word : words) {
      Map<Character, Integer> currentCharMap = new HashMap<>();
      for (Character c : word.toCharArray()) {
        currentCharMap.put(c, currentCharMap.getOrDefault(c, 0) + 1);
      }

      for (Map.Entry<Character, Integer> e : currentCharMap.entrySet()) {
        char c = e.getKey();
        int count = e.getValue();

        charMap.put(c, Math.max(count, charMap.getOrDefault(c, 0)));
      }
    }

    ArrayList<Character> outputList = new ArrayList<>();
    for (Map.Entry<Character, Integer> e : charMap.entrySet()) {
      char c = e.getKey();
      int count = e.getValue();

      while (count > 0) {
        outputList.add(c);
        count--;
      }
    }

    char[] output = new char[outputList.size()];
    for (int i = 0; i < outputList.size(); i++) {
      output[i] = outputList.get(i);
    }

    return output;
  }

  public static void main(String[] args) {
    System.out
        .println(
            Arrays.toString(minimumCharactersForWords(new String[] { "this", "that", "did", "deed", "them!", "a" })));
  }
}
