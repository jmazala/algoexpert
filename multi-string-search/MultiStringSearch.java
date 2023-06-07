// https://www.algoexpert.io/questions/multi-string-search

import java.util.*;

class MultiStringSearch {

  static class TrieNodeWithWordIndex extends TrieNode {
    Map<Character, TrieNodeWithWordIndex> children;
    int wordIndex;

    public TrieNodeWithWordIndex() {
      this.children = new HashMap<>();
      this.isWord = false;
    }

    public void addWordWithIndex(String word, int index) {
      TrieNodeWithWordIndex temp = this;

      for (char c : word.toCharArray()) {
        if (!temp.children.containsKey(c)) {
          temp.children.put(c, new TrieNodeWithWordIndex());
        }

        temp = temp.children.get(c);
      }

      temp.isWord = true;
      temp.wordIndex = index;
    }
  }

  public static List<Boolean> multiStringSearch(String bigString, String[] smallStrings) {
    TrieNodeWithWordIndex root = new TrieNodeWithWordIndex();
    for (int i = 0; i < smallStrings.length; i++) {
      root.addWordWithIndex(smallStrings[i], i);
    }

    Queue<TrieNodeWithWordIndex> queue = new LinkedList<>();

    Boolean[] found = new Boolean[smallStrings.length];
    Arrays.fill(found, false);

    for (char c : bigString.toCharArray()) {
      int queueSize = queue.size();

      for (int i = 0; i < queueSize; i++) {
        TrieNodeWithWordIndex node = queue.remove();

        if (node.isWord) {
          found[node.wordIndex] = true;
        }

        if (node.children.containsKey(c)) {
          node = node.children.get(c);

          queue.add(node);
        }
      }

      if (root.children.containsKey(c)) {
        queue.add(root.children.get(c));
      }
    }

    while (!queue.isEmpty()) {
      TrieNodeWithWordIndex node = queue.remove();

      if (node.isWord) {
        found[node.wordIndex] = true;
      }
    }

    return new ArrayList<Boolean>(Arrays.asList(found));
  }

  public static void main(String[] args) {
    System.out.println(multiStringSearch("catdog", new String[] { "cat", "dog", "fish" })); // [true, true, false]
    System.out.println(multiStringSearch("catfishdog", new String[] { "cat", "dog", "fish", "catfish" }));
    // [true, true, true, true]
    System.out.println(multiStringSearch("this is a big string",
        new String[] { "this", "yo", "is", "a", "bigger", "string", "kappa" }).toString());
    // [true, false, true, true, false, true, false]
  }
}
