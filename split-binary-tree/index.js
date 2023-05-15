// https://www.algoexpert.io/questions/split-binary-tree

// This is an input class. Do not edit.
class BinaryTree {
  constructor(value) {
    this.value = value;
    this.left = null;
    this.right = null;
  }
}

exports.BinaryTree = BinaryTree;

function splitBinaryTree(tree) {
  helper(tree);

  const sum = tree.subTreeSum;
  if (sum % 2 === 1) {
    return 0;
  }

  const stack = [tree];

  while (stack.length) {
    const node = stack.pop();
    if (node.subTreeSum === (sum / 2)) {
      return node.subTreeSum;
    }

    if (node.left) {
      stack.push(node.left);
    }

    if (node.right) {
      stack.push(node.right);
    }
  }

  return 0;

  function helper(node) {
    node.subTreeSum = node.value;

    if (node.left) {
      helper(node.left);
      node.subTreeSum += node.left.subTreeSum;
    }

    if (node.right) {
      helper(node.right);
      node.subTreeSum += node.right.subTreeSum;
    }
  }
}

// Do not edit the line below.
exports.splitBinaryTree = splitBinaryTree;

const one = new BinaryTree(1);
const three = new BinaryTree(3);
const six = new BinaryTree(6);
const two = new BinaryTree(2);
const negativeFive = new BinaryTree(-5);
const negativeTwo = new BinaryTree(-2);
const five = new BinaryTree(5);
const two2 = new BinaryTree(2);

one.left = three;
three.left = six;
six.left = two;
three.right = negativeFive;
one.right = negativeTwo;
negativeTwo.left = five;
negativeTwo.right = two2;

console.log(splitBinaryTree(one));