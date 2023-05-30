// https://www.algoexpert.io/questions/group-anagrams

import java.util.*;

class GroupAnagrams {
  /*
   * Couple ways to do this problem:
   * 1 - sort every string and iterate through them as anagrams will be equal
   * 2 - build hashes for each string and iterate / compare the hashes
   */
  public static List<List<String>> groupAnagrams(List<String> words) {
    Map<String, List<String>> wordMap = new HashMap<>();
    for (int i = 0; i < words.size(); i++) {
      char[] a = words.get(i).toCharArray();
      Arrays.sort(a);
      String sortedWord = new String(a);

      if (!wordMap.containsKey(sortedWord)) {
        wordMap.put(sortedWord, new ArrayList<>());
      }

      wordMap.get(sortedWord).add(words.get(i));
    }

    return new ArrayList<>(wordMap.values());
  }

  public static void main(String[] args) {
    List<String> words = new ArrayList<>(
        Arrays.asList(new String[] { "yo", "act", "flop", "tac", "foo", "cat", "oy", "olfp" }));
    System.out.println(groupAnagrams(words).toString());
  }
}
