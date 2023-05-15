// https://www.algoexpert.io/questions/symmetrical-tree

// This is an input class. Do not edit.
class BinaryTree {
  constructor(value) {
    this.value = value;
    this.left = null;
    this.right = null;
  }
}

exports.BinaryTree = BinaryTree;

/*
Recursive solution

TIME: O(n/2) as we visit half the nodes
SPACE: O(h) for recursion stack
*/
function symmetricalTree(tree) {
  if (!tree) {
    return true;
  }

  return helper(tree.left, tree.right);
}

function helper(node1, node2) {
  if (!node1 && !node2) {
    return true;
  }

  if (!node1 || !node2) {
    return false;
  }

  if (node1.value !== node2.value) {
    return false;
  }

  return helper(node1.left, node2.right) && helper(node1.right, node2.left);
}

// Do not edit the line below.
exports.symmetricalTree = symmetricalTree;
