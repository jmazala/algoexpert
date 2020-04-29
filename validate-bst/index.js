// This is an input class. Do not edit.
class BST {
  constructor(value) {
    this.value = value;
    this.left = null;
    this.right = null;
  }
}

function validateBst(tree) {
  return helper(tree, -Infinity, Infinity);
}

function helper(node, lower, upper) {
  if (!node) {
    return true;
  }

  if (node.value < lower || node.value >= upper) {
    return false;
  }

  return helper(node.left, lower, node.value) && helper(node.right, node.value, upper);
}

// Do not edit the line below.
exports.BST = BST;
exports.validateBst = validateBst;

tree = new BST(10);
tree.left = new BST(5);
tree.left.right = new BST(10);
tree.right = new BST(15);

console.log(validateBst(tree));