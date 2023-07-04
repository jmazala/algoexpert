// https://www.algoexpert.io/questions/right-sibling-tree

// This is the class of the input root. Do not edit it.
class BinaryTree {
  constructor(value) {
    this.value = value;
    this.left = null;
    this.right = null;
  }
}

/*
Recursive with DFS.  In-order traverse the tree (left, node, right).
First the left child is processed, which its right sibling will always be its
parent's right child.  The right child's right sibling will be the parent's right
sibling's left child (hence in order traverse, need to modify parent).
The root of the tree will never have a right sibling

TIME: O(n) to traverse every node
SPACE: O(h) for recursion stack where h is height of the tree
*/

function rightSiblingTree(root) {
  helper(root, null, false);
  return root;
}

// modified in-order traversal function
function helper(node, parent, isLeftChild) {
  if (!node) {
    return;
  }

  const { left, right } = node;
  helper(left, node, true);

  // Root of the tree never has a right sibling
  if (!parent) {
    node.right = null;
  } else if (isLeftChild) {
    // left child's sibling is always parent's right sibling
    node.right = parent.right;
  } else {
    // right child's sibling is parent's right sibling's left child
    if (!parent.right) {
      node.right = null;
    } else {
      node.right = parent.right.left;
    }
  }

  helper(right, node, false);
}

/*
Use a BFS to level order traverse the tree
This works well because the right sibling will always
be the head of the queue
TIME: O(n) as we traverse the tree
SPACE: O(w) where w is the widest row of the tree
*/
function rightSiblingTree2(root) {
  let queue = [root];

  while (queue.length) {
    const nextRow = [];
    const queueLength = queue.length;
    for (let i = 0; i < queueLength; i++) {
      const node = queue.shift();

      if (!node) {
        continue;
      }

      nextRow.push(node.left);
      nextRow.push(node.right);
      node.right = queue.length ? queue[0] : null;
    }

    queue = queue.concat(nextRow);
  }

  return root;
}

// Do not edit the lines below.
exports.BinaryTree = BinaryTree;
exports.rightSiblingTree = rightSiblingTree;

const root = new BinaryTree(1);
root.left = new BinaryTree(2);
root.right = new BinaryTree(3);
root.left.left = new BinaryTree(4);
root.left.right = new BinaryTree(5);
root.right.left = new BinaryTree(6);
root.right.right = new BinaryTree(7);
root.left.left.left = new BinaryTree(8);
root.left.left.right = new BinaryTree(9);
root.left.right.right = new BinaryTree(10);
root.right.left.left = new BinaryTree(11);
root.right.right.left = new BinaryTree(12);
root.right.right.right = new BinaryTree(13);
root.right.left.left.left = new BinaryTree(14);

rightSiblingTree(root);