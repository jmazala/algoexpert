// https://www.algoexpert.io/questions/find-successor

// This is an input class. Do not edit.
class BinaryTree {
  constructor(value) {
    this.value = value;
    this.left = null;
    this.right = null;
    this.parent = null;
  }
}

function findSuccessor(tree, node) {
  if (node.right) {
    return leftMostChild(node.right);
  }

  return rightMostParent(tree, node);
}

function rightMostParent(tree, node) {
  /*
  Otherwise we are the rightmost node in the left subtree.
  Traverse up the tree to find the node whose left subtree
  can satisfy this criteria.
  If we can't, we're the rightmost node of the entire tree
  */
  while (node !== tree && node.parent.right === node) {
    node = node.parent;
  }

  return node.parent;
}

function leftMostChild(node) {
  while (node.left) {
    node = node.left;
  }

  return node;
}

// Do not edit the lines below.
exports.BinaryTree = BinaryTree;
exports.findSuccessor = findSuccessor;
