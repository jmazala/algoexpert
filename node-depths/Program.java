import java.util.*;

class Program {

  public static int nodeDepths(BinaryTree root) {
    return helper(root, 0);
  }

  public static int helper(BinaryTree root, int depth) {
    if (root == null) {
      return 0;
    }

    return depth + helper(root.left, depth + 1) + helper(root.right, depth + 1);
  }

  static class BinaryTree {
    int value;
    BinaryTree left;
    BinaryTree right;

    public BinaryTree(int value) {
      this.value = value;
      left = null;
      right = null;
    }
  }
}
