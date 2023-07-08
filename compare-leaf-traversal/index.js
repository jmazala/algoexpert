// https://www.algoexpert.io/questions/compare-leaf-traversal


// This is an input class. Do not edit.
class BinaryTree {
  constructor(value) {
    this.value = value;
    this.left = null;
    this.right = null;
  }
}

const isLeafNode = (node) => !!node && !node.left && !node.right;

/*
Method 1
Traverse tree using postorder traversal (left, right, node)
Store leaf nodes of each tree in an array.  Compare arrays.
TIME:  O(n)
SPACE:  O(n)
*/
function compareLeafTraversal3(tree1, tree2) {
  const leaf1 = [];
  const leaf2 = [];

  gatherLeaves(tree1, leaf1);
  gatherLeaves(tree2, leaf2);

  if (leaf1.length !== leaf2.length) {
    return false;
  }

  for (let i = 0; i < leaf1.length; i++) {
    if (leaf1[i] !== leaf2[i]) {
      return false;
    }
  }

  return true;
}

function gatherLeaves(node, leaves) {
  if (!node) {
    return;
  }

  gatherLeaves(node.left, leaves);
  gatherLeaves(node.right, leaves);

  if (!node.left && !node.right) {
    leaves.push(node.value);
  }
}

/*
Method 2 - 
Traverse trees using 2 pointers and stop at a leaf.
When both pointers are at a leaf, compare values.
TIME: O(n)
SPACE: O(n) for parent pointers
*/
function compareLeafTraversal2(tree1, tree2) {
  if (isLeafNode(tree1) && isLeafNode(tree2)) {
    return tree1.value === tree2.value;
  }

  populateParents(tree1, null);
  populateParents(tree2, null);
  const pointers = [
    {
      current: tree1,
      previous: null,
      next: null
    },
    {
      current: tree2,
      previous: null,
      next: null
    }
  ];

  while (true) {
    moveToLeafNode(0);
    moveToLeafNode(1);

    if (!pointers[0].current && !pointers[1].current) {
      return true;
    }

    if (!pointers[0].current && pointers[1].current || pointers[0].current && !pointers[1].current || pointers[0].current.value !== pointers[1].current.value) {
      return false;
    }
  }

  function moveToLeafNode(i) {
    while (pointers[i].current) {

      // CASE:  Root of the tree
      if (!pointers[i].previous) {
        if (pointers[i].current.left) {
          pointers[i].next = pointers[i].current.left;
        } else {
          pointers[i].next = pointers[i].current.right;
        }
      }
      // CASE:  Traversing downwards
      else if (pointers[i].current === pointers[i].previous.left || pointers[i].current === pointers[i].previous.right) {
        if (pointers[i].current.left) {
          pointers[i].next = pointers[i].current.left;
        } else if (pointers[i].current.right) {
          pointers[i].next = pointers[i].current.right;
        } else {
          pointers[i].next = pointers[i].previous;
        }
      }
      // CASE:  traversing back up, still need to process right subtree
      else if (pointers[i].previous === pointers[i].current.left) {
        if (pointers[i].current.right) {
          pointers[i].next = pointers[i].current.right;
        } else {
          pointers[i].next = pointers[i].current.parent;
        }
      }
      // CASE:  traversing back up, processed both subtrees
      // pointers[i].previous === pointers[i].current.right
      else {
        pointers[i].next = pointers[i].current.parent;
      }

      pointers[i].previous = pointers[i].current;
      pointers[i].current = pointers[i].next;

      if (isLeafNode(pointers[i].current)) {
        return;
      }
    }
  }
}

function populateParents(node, parent) {
  if (!node) {
    return;
  }

  node.parent = parent;
  populateParents(node.left, node);
  populateParents(node.right, node);
}

/*
Method 3
Recursively travel the tree
When you encounter leaf nodes, link the previous leaf node's
right child to the current leaf node.  Effectively this
creates a linked list starting at leftmost leaf node
and ending at rightmost leaf node.  And each recursive call
needs to return (it's) leftmost leaf node.  That
goes up to the first function call where we can then
traverse the linkedlist.  By doing this on both trees
we can compare if the linked lists are equal

TIME: O(n) because we traverse every node
SPACE: O(h) for recursion stack where h is larger of 2 trees height
*/
function compareLeafTraversal(tree1, tree2) {
  let [node1, _] = connectLeafNodes(tree1, null, null);
  let [node2, __] = connectLeafNodes(tree2, null, null);

  while (node1 && node2) {
    if (node1.value !== node2.value) {
      return false;
    }

    node1 = node1.right;
    node2 = node2.right;
  }

  return !node1 && !node2;
}

/*
Effectively, head is only set 1 time and represents the head
of the entire linked list (i.e. roots leftmost leaf node)
Previous keeps track of the last node in the linked list so we
can append to it when we find another leaf node
*/
function connectLeafNodes(current, head, previous) {
  if (!current) {
    return [head, previous];
  }

  if (isLeafNode(current)) {
    // Set head for the first (and only) time
    if (!previous) {
      head = current;
    } else {
      // otherwise append to the linked list
      previous.right = current;
    }

    previous = current;
  }

  const [leftHead, leftPrevious] = connectLeafNodes(current.left, head, previous);
  return connectLeafNodes(current.right, leftHead, leftPrevious);
}

// Do not edit the lines below.
exports.BinaryTree = BinaryTree;
exports.compareLeafTraversal = compareLeafTraversal;

const tree1 = new BinaryTree(1);
tree1.left = new BinaryTree(2);
tree1.right = new BinaryTree(3);
tree1.left.left = new BinaryTree(4);
tree1.left.right = new BinaryTree(5);
tree1.right.right = new BinaryTree(6);
tree1.left.right.left = new BinaryTree(7);
tree1.left.right.right = new BinaryTree(8);

const tree2 = new BinaryTree(1);
tree2.left = new BinaryTree(2);
tree2.right = new BinaryTree(3);
tree2.left.left = new BinaryTree(4);
tree2.right.right = new BinaryTree(5);
tree2.right.right.right = new BinaryTree(6);
tree2.left.right = new BinaryTree(7);
tree2.right.right.left = new BinaryTree(8);

console.log(compareLeafTraversal(tree1, tree2));