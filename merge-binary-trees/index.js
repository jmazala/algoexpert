// https://www.algoexpert.io/questions/merge-binary-trees

// This is an input class. Do not edit.
class BinaryTree {
  constructor(value) {
    this.value = value;
    this.left = null;
    this.right = null;
  }
}

exports.BinaryTree = BinaryTree;

function mergeBinaryTrees(tree1, tree2) {
  const mergedTree = merge(tree1, tree2);
  return mergedTree;
}

// Return new tree
function merge(tree1, tree2) {
  if (!tree1 && !tree2) {
    return null;
  }

  if (tree1 && !tree2) {
    return tree1;
  }

  if (!tree1 && tree2) {
    return tree2;
  }

  const node = new BinaryTree(tree1.value + tree2.value);
  node.left = merge(tree1.left, tree2.left);
  node.right = merge(tree1.right, tree2.right);
  return node;
}

// Mutate tree1
// This is an input class. Do not edit.
class BinaryTree {
  constructor(value) {
    this.value = value;
    this.left = null;
    this.right = null;
  }
}

exports.BinaryTree = BinaryTree;

// Mutate existing tree
function mergeBinaryTrees(tree1, tree2) {
  if (!tree1) {
    return tree2;
  }

  if (!tree2) {
    return tree1;
  }

  tree1.value += tree2.value;
  tree1.left = mergeBinaryTrees(tree1.left, tree2.left);
  tree1.right = mergeBinaryTrees(tree1.right, tree2.right);
  return tree1;
}

// Do not edit the line below.
exports.mergeBinaryTrees = mergeBinaryTrees;


// Do not edit the line below.
exports.mergeBinaryTrees = mergeBinaryTrees;
