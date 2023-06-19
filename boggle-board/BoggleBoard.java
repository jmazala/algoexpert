// https://www.algoexpert.io/questions/boggle-board

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

class BoggleBoard {
  private static TrieNode trie;
  private static final char PLACEHOLDER = '!';
  private static final int[][] DIRECTIONS = { { -1, -1 }, { -1, 1 }, { 1, -1 }, { 1, 1 }, { -1, 0 }, { 1, 0 },
      { 0, -1 }, { 0, 1 } };

  public static List<String> boggleBoard(char[][] board, String[] words) {
    Set<String> result = new HashSet<>();

    if (words.length == 0) {
      return new LinkedList<String>();
    }

    int m = board.length;
    if (m == 0) {
      return new LinkedList<String>();
    }

    int n = board[0].length;
    if (n == 0) {
      return new LinkedList<String>();
    }

    buildTrie(words);

    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        dfs(board, m, n, i, j, trie, result);
      }
    }

    return new LinkedList<String>(result);
  }

  private static void dfs(char[][] board, int m, int n, int i, int j, TrieNode node, Set<String> result) {
    if (i < 0 || i >= m || j < 0 || j >= n) {
      return;
    }

    if (node.isWord) {
      result.add(node.word);
    }

    char c = board[i][j];
    if (node.children.containsKey(c)) {
      board[i][j] = PLACEHOLDER;

      TrieNode next = node.children.get(c);

      for (int[] direction : DIRECTIONS) {
        dfs(board, m, n, i + direction[0], j + direction[1], next, result);
      }

      board[i][j] = c;
    }
  }

  private static void buildTrie(String[] words) {
    trie = new TrieNode();

    for (String word : words) {
      trie.addWord(word);
    }
  }

  public static void main(String[] args) {
    char[][] board = {
        { 't', 'h', 'i', 's', 'i', 's', 'a' },
        { 's', 'i', 'm', 'p', 'l', 'e', 'x' },
        { 'b', 'x', 'x', 'x', 'x', 'e', 'b' },
        { 'x', 'o', 'g', 'g', 'l', 'x', 'o' },
        { 'x', 'x', 'x', 'D', 'T', 'r', 'a' },
        { 'R', 'E', 'P', 'E', 'A', 'd', 'x' },
        { 'x', 'x', 'x', 'x', 'x', 'x', 'x' },
        { 'N', 'O', 'T', 'R', 'E', '-', 'P' },
        { 'x', 'x', 'D', 'E', 'T', 'A', 'E' }
    };

    String[] words = { "this", "is", "not", "a", "simple", "boggle", "board", "test", "REPEATED", "NOTRE-PEATED" };
    System.out.println(BoggleBoard.boggleBoard(board, words)); // [a, boggle, this, is, simple, board, NOTRE-PEATED]
  }
}
