function nodeDepths(root) {
  let answer = 0;
  helper(root, 0);
  return answer;

  function helper(node, depth) {
    if (!node) {
      return;
    }

    answer += depth;
    depth++;
    helper(node.left, depth);
    helper(node.right, depth);
  }
}

// This is the class of the input binary tree.
class BinaryTree {
  constructor(value) {
    this.value = value;
    this.left = null;
    this.right = null;
  }
}

// Do not edit the line below.
exports.nodeDepths = nodeDepths;
