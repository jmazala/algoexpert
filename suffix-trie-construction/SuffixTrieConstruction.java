// https://www.algoexpert.io/questions/suffix-trie-construction

import java.util.*;

class SuffixTrieConstruction {
  // Do not edit the class below except for the
  // populateSuffixTrieFrom and contains methods.
  // Feel free to add new properties and methods
  // to the class.
  static class TrieNode {
    Map<Character, TrieNode> children = new HashMap<Character, TrieNode>();
  }

  static class SuffixTrie {
    TrieNode root = new TrieNode();
    char endSymbol = '*';

    public SuffixTrie(String str) {
      populateSuffixTrieFrom(str);
    }

    public void populateSuffixTrieFrom(String str) {
      for (int i = 0; i < str.length(); i++) {
        insertSubstring(i, str);
      }
    }

    public void insertSubstring(int i, String str) {
      TrieNode temp = root;
      while (i < str.length()) {
        char c = str.charAt(i);
        if (!temp.children.containsKey(c)) {
          temp.children.put(c, new TrieNode());
        }

        temp = temp.children.get(c);
        i++;
      }

      temp.children.put(endSymbol, null);
    }

    public boolean contains(String str) {
      TrieNode temp = root;

      for (char c : str.toCharArray()) {
        temp = temp.children.get(c);
        if (temp == null) {
          return false;
        }
      }

      return temp.children.containsKey(endSymbol);
    }
  }
}