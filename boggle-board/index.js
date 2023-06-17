// https://www.algoexpert.io/questions/boggle-board

const PLACEHOLDER = '!';
const DIRECTIONS = [
  [0, -1], [0, 1], [1, 0], [-1, 0], [1, -1], [1, 1], [-1, -1], [-1, 1]
];

class TrieNode {
  children = {};
  isWord = false;
  word = null;

  addWord(word) {
    let node = this;
    for (const c of word) {
      if (!node.children[c]) {
        node.children[c] = new TrieNode();
      }

      node = node.children[c];
    }

    node.isWord = true;
    node.word = word;
  }
}

function boggleBoard(board, words) {
  if (words.length === 0 || board.length === 0 || board[0].length === 0) {
    return [];
  }

  const result = new Set();
  const trie = new TrieNode();
  buildTrie();

  const m = board.length;
  const n = board[0].length;

  for (let i = 0; i < m; i++) {
    for (let j = 0; j < n; j++) {
      dfs(i, j, trie);
    }
  }

  return Array.from(result);

  function dfs(i, j, trie) {
    if (i < 0 || i >= m || j < 0 || j >= n) {
      return;
    }

    if (trie.isWord) {
      result.add(trie.word);
    }

    const c = board[i][j];

    if (trie.children[c]) {
      board[i][j] = PLACEHOLDER;
      for (const direction of DIRECTIONS) {
        dfs(i + direction[0], j + direction[1], trie.children[c]);
      }
      board[i][j] = c;
    }
  }

  function buildTrie() {
    for (const word of words) {
      trie.addWord(word);
    }
  }
}

// Do not edit the line below.
exports.boggleBoard = boggleBoard;
