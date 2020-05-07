const { TreeNode } = require('../js-includes');

function maxPathSum(tree) {
  let answer = -Infinity;
  helper(tree);
  return answer;

  function helper(node) {
    if (!node) {
      return 0;
    }

    //the max path sum is either the left subtree, through the root, or the right subtree
    const left = Math.max(0, helper(node.left));
    const right = Math.max(0, helper(node.right));

    answer = Math.max(answer, left + right + node.val);

    return node.val + Math.max(left, right);
  }
}

// Do not edit the line below.
exports.maxPathSum = maxPathSum;

const root = new TreeNode(1);
root.left = new TreeNode(2);
root.left.left = new TreeNode(4);
root.left.right = new TreeNode(5);
root.right = new TreeNode(3);
root.right.left = new TreeNode(6);
root.right.right = new TreeNode(7);

console.log(maxPathSum(root)); //18