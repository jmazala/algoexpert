// This is an input class. Do not edit.
class BST {
  constructor(value, left = null, right = null) {
    this.value = value;
    this.left = left;
    this.right = right;
    this.parent = null;
  }
}

/*
 METHOD 1 - Just construct binary tree by traversing tree every time
 TIME:  O(n log n) because it takes log n to decide where to place each node
 SPACE: O(1)
*/
function reconstructBst(preOrderTraversalValues) {
  const head = new BST(preOrderTraversalValues[0]);
  let node = head;

  for (let i = 1; i < preOrderTraversalValues.length; i++) {
    let node = head;
    const currentVal = preOrderTraversalValues[i];
    while (true) {
      if (currentVal < node.value) {
        if (node.left) {
          node = node.left;
        } else {
          node.left = new BST(currentVal);
          break;
        }
      } else {
        if (node.right) {
          node = node.right;
        } else {
          node.right = new BST(currentVal);
          break;
        }
      }
    }
  }
}

/*
METHOD 2 - Take advantage of the fact that the nodes are in pretraversal order
This means the order is node, left subtree, right subtree

All values <= head will be in left subtree.  Rest are right
The left ones come first so we can keep splitting the array
Recursively call, building a node with every recursive call

TIME:  O(n^2) as you are looping through the entire array of values with every node
*/
function reconstructBst(preOrderTraversalValues) {
  if (preOrderTraversalValues.length === 0) {
    return;
  }

  const head = new BST(preOrderTraversalValues.shift());
  const left = [];

  while (preOrderTraversalValues.length > 0 && preOrderTraversalValues[0] < head.value) {
    left.push(preOrderTraversalValues.shift());
  }

  if (left.length > 0) {
    head.left = reconstructBst(left);
  }

  if (preOrderTraversalValues.length > 0) {
    head.right = reconstructBst(preOrderTraversalValues);
  }

  return head;
}

/*
METHOD 3 - Use an object to keep track of the index
Make recursive calls with the bounds defined
*/

class TreeInfo {
  constructor(idx) {
    this.idx = idx;
  }
}

function reconstructBst(preOrderTraversalValues) {
  const treeInfo = new TreeInfo(0);
  return helper(-Infinity, Infinity, preOrderTraversalValues, treeInfo);
}

function helper(lowerBound, upperBound, values, treeInfo) {
  if (treeInfo.idx === values.length) {
    return;
  }

  const rootValue = values[treeInfo.idx];
  if (rootValue < lowerBound || rootValue >= upperBound) {
    return;
  }

  treeInfo.idx++;
  const left = helper(lowerBound, rootValue, values, treeInfo);
  const right = helper(rootValue, upperBound, values, treeInfo);
  return new BST(rootValue, left, right);
}

console.log(reconstructBst([10, 4, 2, 1, 5, 17, 19, 18]));

// Do not edit the lines below.
exports.BST = BST;
exports.reconstructBst = reconstructBst;
