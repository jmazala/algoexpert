import java.util.*;

class Program {
  private static TrieNode trie;
  private static final char PLACEHOLDER = '!';
  private static final int[][] DIRECTIONS = { { -1, -1 }, { -1, 1 }, { 1, -1 }, { 1, 1 }, { -1, 0 }, { 1, 0 },
      { 0, -1 }, { 0, 1 } };

  public static List<String> boggleBoard(char[][] board, String[] words) {
    Set<String> set = new HashSet<>();
    List<String> answer = new ArrayList<>();

    if (words.length == 0) {
      return answer;
    }

    int M = board.length;
    if (M == 0) {
      return answer;
    }

    int N = board[0].length;
    if (N == 0) {
      return answer;
    }

    buildTrie(words);

    for (int i = 0; i < M; i++) {
      for (int j = 0; j < N; j++) {
        TrieNode temp = trie;
        dfs(board, M, N, i, j, "", temp, set);
      }
    }

    answer.addAll(set);
    return answer;
  }

  private static void dfs(char[][] board, int M, int N, int i, int j, String prefix, TrieNode node, Set<String> set) {
    if (node.isWord) {
      set.add(prefix);
    }

    char c = board[i][j];
    board[i][j] = PLACEHOLDER;
    if (node.children.containsKey(c)) {
      TrieNode next = node.children.get(c);

      for (int[] direction : DIRECTIONS) {
        int nextI = i + direction[0];
        int nextJ = j + direction[1];

        if (nextI < 0 || nextI >= M || nextJ < 0 || nextJ >= N) {
          continue;
        }

        dfs(board, M, N, nextI, nextJ, prefix + c, next, set);
      }
    }

    board[i][j] = c;
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
    System.out.println(Program.boggleBoard(board, words)); // [a, boggle, this, is, simple, board, NOTRE-PEATED]
  }
}
