// https://www.algoexpert.io/questions/compare-leaf-traversal

class CompareLeafTraversal {
  // This is an input class. Do not edit.
  static class BinaryTree {
    public int value;
    public BinaryTree left = null;
    public BinaryTree right = null;

    public BinaryTree(int value) {
      this.value = value;
    }
  }

  // Helper class to keep track of leaf nodes (1st, and last seen)
  static class TreeInfo {
    public BinaryTree head;
    public BinaryTree previous;

    public TreeInfo() {
      this.head = null;
      this.previous = null;
    }
  }

  /*
   * Traverse the tree (left, right, node)
   * The first leaf node encountered is leftmost, that will be the head
   * of the entire linked list returned.
   * Use an object to keep track of that node (set it once).
   * Otherwise we want to remember the previous leaf node encountered
   * (tracked in the same object) so we can set its right child
   * to the next leaf node encountered. (Effectively turning the leaf nodes of the
   * tree into a linked list in place). This uses no extra memory. And the right
   * node
   * pointers of leaf nodes are null so there is nothing to preserve anyway.
   * 
   * TIME: O(n) to visit every node
   * SPACE: O(h) for the recursion stack. Where h is the largest height of
   * tree1 and tree2
   */
  public boolean compareLeafTraversal(BinaryTree tree1, BinaryTree tree2) {
    TreeInfo info = new TreeInfo();
    linkNodes(tree1, info);
    BinaryTree node1 = info.head;

    info = new TreeInfo();
    linkNodes(tree2, info);
    BinaryTree node2 = info.head;

    while (node1 != null && node2 != null) {
      if (node1.value != node2.value) {
        return false;
      }

      node1 = node1.right;
      node2 = node2.right;
    }

    return node1 == null && node2 == null;
  }

  private static void linkNodes(BinaryTree current, TreeInfo info) {
    if (current == null) {
      return;
    }

    if (isLeafNode(current)) {
      // first leaf node (leftmost)
      if (info.previous == null) {
        info.head = current;
      } else {
        info.previous.right = current;
      }

      info.previous = current;
    }

    linkNodes(current.left, info);
    linkNodes(current.right, info);
  }

  private static boolean isLeafNode(BinaryTree node) {
    return node.left == null && node.right == null;
  }
}
