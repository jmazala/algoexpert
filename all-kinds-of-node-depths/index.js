// https://www.algoexpert.io/questions/all-kinds-of-node-depths

/*
SOLUTION 1 (GOOD):  Recursively keeping track of parents
TIME: O(nd)
  O(n) for visiting each node
    O(d - 1) for looping through parents and adding to sum
  = O(nd)
SPACE: O(d)
  O(1) for sum
  O(d) for parents
  O(d) for recursion stack
*/
function allKindsOfNodeDepths3(root) {
  let sum = 0;
  const parents = [];
  helper(root, 0);
  return sum;

  function helper(node, depth) {
    if (!node) {
      return;
    }

    node.depth = depth;
    parents.push(node);
    helper(node.left, depth + 1);
    helper(node.right, depth + 1);
    parents.pop();

    for (const parent of parents) {
      sum += (node.depth - parent.depth);
    }
  }
}

/*
SOLUTION 2 (BETTER):  Understand the math property.
Above, we could simplify the keeping track of (and looping through) parents
to add to the sum by understanding that the loop represents sum += (1 + 2 + ...)
which is the same as (n*(n+1)) / 2
TIME: O(n)
SPACE: O(d) for recursion stack
*/
function allKindsOfNodeDepths2(root) {
  let sum = 0;
  helper(root, 0);
  return sum;

  function helper(node, depth) {
    if (!node) {
      return;
    }

    helper(node.left, depth + 1);
    helper(node.right, depth + 1);
    sum += ((depth * (depth + 1)) / 2);
  }
}

/*
SOLUTION 3 (STACK)
Instead of a helper recursive function, use a stack
TIME: O(n) for visiting every node
SPACE: O(d) on average, O(n) worst case.  Depends on distribution of tree
*/
function allKindsOfNodeDepths(root) {
  let sum = 0;
  let stack = [{ node: root, depth: 0 }];

  while (stack.length) {
    const { node, depth } = stack.pop();
    if (node.left) {
      stack.push({ node: node.left, depth: depth + 1 });
    }

    if (node.right) {
      stack.push({ node: node.right, depth: depth + 1 });
    }

    sum += ((depth * (depth + 1)) / 2);
  }

  return sum;
}

// This is the class of the input binary tree.
class BinaryTree {
  constructor(value) {
    this.value = value;
    this.left = null;
    this.right = null;
  }
}

// Do not edit the line below.
exports.allKindsOfNodeDepths = allKindsOfNodeDepths;

const root = new BinaryTree(1);
root.left = new BinaryTree(2);
root.right = new BinaryTree(3);
root.left.left = new BinaryTree(4);
root.left.right = new BinaryTree(5);
root.right.left = new BinaryTree(6);
root.right.right = new BinaryTree(7);
root.left.left.left = new BinaryTree(8);
root.left.left.right = new BinaryTree(9);

console.log(allKindsOfNodeDepths(root)); // 26