// https://www.algoexpert.io/questions/height-balanced-binary-tree

// This is an input class. Do not edit.
class BinaryTree {
  constructor(value) {
    this.value = value;
    this.left = null;
    this.right = null;
  }
}

function heightBalancedBinaryTree(tree) {
  let isBalanced = true;
  visit(tree);
  return isBalanced;

  function visit(node) {
    if (!isBalanced) {
      return;
    }

    if (!node) {
      return;
    }

    node.leftHeight = 0;
    node.rightHeight = 0;

    if (node.left) {
      visit(node.left);
      node.leftHeight = node.left.height;
    }

    if (node.right) {
      visit(node.right);
      node.rightHeight = node.right.height;
    }

    if (Math.abs(node.rightHeight - node.leftHeight) > 1) {
      isBalanced = false;
    }

    node.height = 1 + Math.max(node.leftHeight, node.rightHeight);
  }
}

// Do not edit the lines below.
exports.BinaryTree = BinaryTree;
exports.heightBalancedBinaryTree = heightBalancedBinaryTree;

const one = new BinaryTree(1);
const two = new BinaryTree(2);
const three = new BinaryTree(3);
const four = new BinaryTree(4);
const five = new BinaryTree(5);
const six = new BinaryTree(6);
const seven = new BinaryTree(7);
const eight = new BinaryTree(8);

console.log(heightBalancedBinaryTree(one)); //true

one.left = two;
console.log(heightBalancedBinaryTree(one)); //true

two.left = four;
console.log(heightBalancedBinaryTree(one)); //false

one.right = three;
console.log(heightBalancedBinaryTree(one)); //true

three.right = six;
console.log(heightBalancedBinaryTree(one)); //true

two.right = five;
console.log(heightBalancedBinaryTree(one)); //true

five.left = seven;
console.log(heightBalancedBinaryTree(one)); //true

five.right = eight;
console.log(heightBalancedBinaryTree(one)); //true