// https://www.algoexpert.io/questions/binary-tree-diameter

class BinaryTreeDiameter {
  // This is an input class. Do not edit.
  static class BinaryTree {
    public int value;
    public BinaryTree left = null;
    public BinaryTree right = null;

    public BinaryTree(int value) {
      this.value = value;
    }
  }

  private static class TreeStats {
    public int maximumDiameter = 0;
    public int depthBelow;
    public int currentDiameter;

    public TreeStats() {
    }
  }

  /*
   * TIME complexity is O(n) because we are visiting each node once
   * SPACE complexity is:
   * O(1) for the singleton TreeStats class
   * O(h) for recursion stack where h is the longest height of the three
   */
  public static int binaryTreeDiameter(BinaryTree tree) {
    /*
     * visit each node
     * calculate the depth of left and right subtree
     * path through that node = depth left/right added together
     * after calculating path through that node, compare to max found so far
     * if its bigger, overwrite
     */
    TreeStats stats = new TreeStats();
    BinaryTreeDiameter.populateTreeStats(tree, stats);
    return stats.maximumDiameter;
  }

  private static void populateTreeStats(BinaryTree tree, TreeStats stats) {
    if (tree == null) {
      stats.depthBelow = 0;
      return;
    }

    BinaryTreeDiameter.populateTreeStats(tree.left, stats);
    int leftDepthBelow = stats.depthBelow;
    BinaryTreeDiameter.populateTreeStats(tree.right, stats);
    int rightDepthBelow = stats.depthBelow;

    if (tree.left == null && tree.right == null) {
      stats.depthBelow = 0;
      stats.currentDiameter = 0;
    } else if (tree.left == null) { // we only have a right tree
      rightDepthBelow++;
      stats.depthBelow = rightDepthBelow;
      stats.currentDiameter = stats.depthBelow;
    } else if (tree.right == null) { // we only have a left tree
      leftDepthBelow++;
      stats.depthBelow = leftDepthBelow;
      stats.currentDiameter = stats.depthBelow;
    } else {
      leftDepthBelow++;
      rightDepthBelow++;
      stats.depthBelow = Math.max(leftDepthBelow, rightDepthBelow);
      stats.currentDiameter = leftDepthBelow + rightDepthBelow;
    }

    stats.maximumDiameter = Math.max(stats.currentDiameter, stats.maximumDiameter);
  }

  public static void main(String[] args) {
    BinaryTree one = new BinaryTree(1);
    BinaryTree two = new BinaryTree(2);
    BinaryTree three = new BinaryTree(3);
    BinaryTree four = new BinaryTree(4);
    BinaryTree five = new BinaryTree(5);
    BinaryTree six = new BinaryTree(6);
    BinaryTree seven = new BinaryTree(7);
    BinaryTree eight = new BinaryTree(8);
    BinaryTree nine = new BinaryTree(9);

    System.out.println(BinaryTreeDiameter.binaryTreeDiameter(one)); // 0

    one.left = three;
    System.out.println(BinaryTreeDiameter.binaryTreeDiameter(one)); // 1
    one.right = two;
    System.out.println(BinaryTreeDiameter.binaryTreeDiameter(one)); // 2
    three.left = seven;
    System.out.println(BinaryTreeDiameter.binaryTreeDiameter(one)); // 3
    seven.left = eight;
    System.out.println(BinaryTreeDiameter.binaryTreeDiameter(one)); // 4
    three.right = four;
    System.out.println(BinaryTreeDiameter.binaryTreeDiameter(one)); // 4
    eight.left = nine;
    System.out.println(BinaryTreeDiameter.binaryTreeDiameter(one)); // 5
    four.right = five;
    System.out.println(BinaryTreeDiameter.binaryTreeDiameter(one)); // 5
    five.right = six;
    System.out.println(BinaryTreeDiameter.binaryTreeDiameter(one)); // 6
  }
}