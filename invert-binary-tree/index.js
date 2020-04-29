//https://www.algoexpert.io/questions/Invert%20Binary%20Tree

function invertBinaryTree(tree) {
  if (!tree) {
    return;
  }

  const temp = tree.left;
  tree.left = tree.right;
  tree.right = temp;
  invertBinaryTree(tree.left);
  invertBinaryTree(tree.right);
  return tree;
}

// Do not edit the line below.
exports.invertBinaryTree = invertBinaryTree;
