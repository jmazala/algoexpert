// https://www.algoexpert.io/questions/max-path-sum-in-binary-tree

public class MaxPathSumInBinaryTree {
  static class TreeInfo {
    public int maxPathSum = Integer.MIN_VALUE;
  }

  public static int maxPathSum(BinaryTree tree) {
    TreeInfo info = new TreeInfo();
    pathSum(tree, info);
    return info.maxPathSum;
  }

  private static int pathSum(BinaryTree tree, TreeInfo info) {
    if (tree == null) {
      return 0;
    }

    int left = Math.max(0, pathSum(tree.left, info));
    int right = Math.max(0, pathSum(tree.right, info));

    // For this node, it's both subtrees + root value
    int maxPathSumForThisNode = tree.value + left + right;
    info.maxPathSum = Math.max(maxPathSumForThisNode, info.maxPathSum);

    /*
     * For above this node, we can only use 1 or 0 of the
     * left and the right subtrees
     */
    return tree.value + Math.max(left, right);
  }

  static class BinaryTree {
    public int value;
    public BinaryTree left;
    public BinaryTree right;

    public BinaryTree(int value) {
      this.value = value;
    }
  }
}
