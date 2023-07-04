// https://www.algoexpert.io/questions/right-sibling-tree

public class RightSiblingTree {

  public static BinaryTree rightSiblingTree(BinaryTree root) {
    helper(root, null, false);
    return root;
  }

  private static void helper(BinaryTree node, BinaryTree parent, boolean isLeftChild) {
    if (node == null) {
      return;
    }

    BinaryTree preservedRight = node.right;
    helper(node.left, node, true);

    if (isLeftChild) {
      node.right = parent.right;
    } else if (parent == null) {
      node.right = null;
    } else {
      if (parent.right != null) {
        node.right = parent.right.left;
      } else {
        node.right = null;
      }
    }

    helper(preservedRight, node, false);
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
