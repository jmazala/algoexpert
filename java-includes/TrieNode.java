import java.util.HashMap;
import java.util.Map;

public class TrieNode {
  public Map<Character, TrieNode> children;
  public boolean isWord;
  public String word;

  public TrieNode() {
    this.children = new HashMap<>();
    this.isWord = false;
    this.word = null;
  }

  public TrieNode(Map<Character, TrieNode> children, boolean isWord, String word) {
    this.children = children;
    this.isWord = isWord;
    this.word = null;
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
    temp.word = word;
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