// This is the class of the input root.
// Do not edit it.
class BinaryTree {
  constructor(value) {
    this.value = value;
    this.left = null;
    this.right = null;
  }
}

function branchSums(root) {
  //DFS the tree keeping track of the sum
  const answer = [];
  helper(root, 0);
  return answer;

  function helper(node, currentSum) {
    if (!node) {
      return;
    }

    currentSum += node.value;

    if (!node.left && !node.right) {
      answer.push(currentSum);
      return;
    }

    helper(node.left, currentSum);
    helper(node.right, currentSum);
  }
}

// Do not edit the lines below.
exports.BinaryTree = BinaryTree;
exports.branchSums = branchSums;

root = new BinaryTree(1);
root.left = new BinaryTree(2);
root.right = new BinaryTree(3);
root.left.left = new BinaryTree(4);
root.left.right = new BinaryTree(5);
root.right.left = new BinaryTree(6);
root.right.right = new BinaryTree(7);
root.left.left.left = new BinaryTree(8);
root.left.left.right = new BinaryTree(9);
root.left.right.left = new BinaryTree(10);
console.log(branchSums(root)); //[15, 16, 18, 10, 11]
