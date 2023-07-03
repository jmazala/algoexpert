// https://www.algoexpert.io/questions/flatten-binary-tree

class FlattenBinaryTree {

  /*
   * TIME: O(n) for traversal
   * SPACE: O(1) for each level of the tree = O(h) where h is height of tree
   */
  public static BinaryTree flattenBinaryTree(BinaryTree root) {
    BinaryTree[] flattened = helper(root);
    return flattened[0];
  }

  private static BinaryTree[] helper(BinaryTree node) {
    BinaryTree leftMost;
    BinaryTree rightMost;

    if (node.left == null) {
      leftMost = node;
    } else {
      BinaryTree[] leftSubtreeOutput = helper(node.left);
      connectNodes(leftSubtreeOutput[1], node);
      leftMost = leftSubtreeOutput[0];
    }

    if (node.right == null) {
      rightMost = node;
    } else {
      BinaryTree[] rightSubtreeOutput = helper(node.right);
      connectNodes(node, rightSubtreeOutput[0]);
      rightMost = rightSubtreeOutput[1];
    }

    return new BinaryTree[] { leftMost, rightMost };
  }

  private static void connectNodes(BinaryTree left, BinaryTree right) {
    left.right = right;
    right.left = left;
  }

  // This is the class of the input root. Do not edit it.
  static class BinaryTree {
    int value;
    BinaryTree left = null;
    BinaryTree right = null;

    public BinaryTree(int value) {
      this.value = value;
    }
  }
}
