import java.util.HashMap;
import java.util.Map;

public class TrieNode {
  Map<Character, TrieNode> children;
  boolean isWord;

  public TrieNode() {
    this.children = new HashMap<>();
    this.isWord = false;
  }

  public TrieNode(Map<Character, TrieNode> children, boolean isWord) {
    this.children = children;
    this.isWord = isWord;
  }

  /**
   * adds the word to the trie.
   * 
   * @param word the word to add to the trie
   */
  public void addWord(String word) {
    TrieNode temp = this;

    for (char c : word.toCharArray()) {
      if (!temp.children.containsKey(c)) {
        temp.children.put(c, new TrieNode());
      }

      temp = temp.children.get(c);
    }

    temp.isWord = true;
  }

  /**
   * locates the entire word in the trie.
   * 
   * @param word the word to find in the trie
   * @return boolean
   */
  public boolean containsWord(String word) {
    TrieNode temp = this;

    for (char c : word.toCharArray()) {
      temp = temp.children.get(c);
      if (temp == null) {
        return false;
      }
    }

    return temp.isWord;
  }
}