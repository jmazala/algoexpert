// https://www.algoexpert.io/questions/flatten-binary-tree

// This is the class of the input root. Do not edit it.
class BinaryTree {
  constructor(value) {
    this.value = value;
    this.left = null;
    this.right = null;
  }
}

/*
TIME: O(n) for tree traversal
SPACE: O(1) for left/right on each level = O(h)

This is a recursive algorithm.  The important things to understand
is the nodes to connect.  We must (recursively) connect:
  1. the rightmost child of the left subtree with the root
  2. the root with the leftmost child of the right subtree
*/
function flattenBinaryTree(root) {
  const [leftMost, _] = helper(root);
  return leftMost;
}

function helper(node) {
  let leftMost;
  let rightMost;

  if (!node.left) {
    leftMost = node;
  } else {
    const [leftSubtreeLeftMost, leftSubtreeRightMost] = helper(node.left);
    connectNodes(leftSubtreeRightMost, node);
    leftMost = leftSubtreeLeftMost;
  }

  if (!node.right) {
    rightMost = node;
  } else {
    const [rightSubtreeLeftMost, rightSubtreeRightMost] = helper(node.right);
    connectNodes(node, rightSubtreeLeftMost);
    rightMost = rightSubtreeRightMost;
  }

  return [leftMost, rightMost];
}

function connectNodes(node1, node2) {
  node1.right = node2;
  node2.left = node1;
}

/*
TIME:  O(n) for tree traversal
SPACE:  O(n) for nodes array
*/
function flattenBinaryTree2(root) {
  const nodes = [];
  addNodes(root);

  for (let i = 0; i < nodes.length; i++) {
    if (i > 0) {
      nodes[i].left = nodes[i - 1];
    }

    if (i < nodes.length - 1) {
      nodes[i].right = nodes[i + 1];
    }
  }

  return nodes[0];

  function addNodes(node) {
    if (!node) {
      return;
    }

    addNodes(node.left);
    nodes.push(node);
    addNodes(node.right);
  }
}

// Do not edit the lines below.
exports.BinaryTree = BinaryTree;
exports.flattenBinaryTree = flattenBinaryTree;

const root = new BinaryTree(1);
root.left = new BinaryTree(2);
root.left.left = new BinaryTree(4);
root.left.right = new BinaryTree(5);
root.left.right.left = new BinaryTree(7);
root.left.right.right = new BinaryTree(8);
root.right = new BinaryTree(3);
root.right.left = new BinaryTree(6);

// let node = flattenBinaryTree(root);

// let s = '';
// while (node) {
//   s += (node.value + ' <-> ');
//   node = node.right;
// }

// console.log(s.substring(0, s.length - 4));

const root2 = new BinaryTree(1);
root2.left = new BinaryTree(2);
root2.left.left = new BinaryTree(4);
root2.left.right = new BinaryTree(5);
root2.right = new BinaryTree(3);
root2.right.left = new BinaryTree(6);
root2.right.right = new BinaryTree(7);

let node2 = flattenBinaryTree(root2);

let s2 = '';
while (node2) {
  s2 += (node2.value + ' <-> ');
  node2 = node2.right;
}

console.log(s2.substring(0, s2.length - 4));
