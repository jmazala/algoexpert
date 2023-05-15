
// https://www.algoexpert.io/questions/symmetrical-tree
import java.util.*;

class SymmetricalTree {
  // This is an input class. Do not edit.
  static class BinaryTree {
    public int value;
    public BinaryTree left = null;
    public BinaryTree right = null;

    public BinaryTree(int value) {
      this.value = value;
    }
  }

  /*
   * Iterative solution using a stack
   * TIME: O(n/2) where n is number of nodes
   * SPACE: O(h) where h is height of tree
   */
  public boolean symmetricalTree(BinaryTree tree) {
    if (tree == null) {
      return true;
    }

    Stack<BinaryTree[]> stack = new Stack<>();
    stack.add(new BinaryTree[] { tree.left, tree.right });

    while (!stack.isEmpty()) {
      BinaryTree[] nodes = stack.pop();
      BinaryTree node1 = nodes[0];
      BinaryTree node2 = nodes[1];

      if (node1 == null && node2 == null) {
        continue;
      }

      if (node1 == null || node2 == null || node1.value != node2.value) {
        return false;
      }

      stack.add(new BinaryTree[] { node1.right, node2.left });
      stack.add(new BinaryTree[] { node1.left, node2.right });
    }

    return true;
  }
}
