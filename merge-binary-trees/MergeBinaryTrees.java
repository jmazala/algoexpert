import java.util.*;

class MergeBinaryTrees {
  // This is an input class. Do not edit.
  static class BinaryTree {
    public int value;
    public BinaryTree left = null;
    public BinaryTree right = null;

    public BinaryTree(int value) {
      this.value = value;
    }
  }

  public BinaryTree mergeBinaryTrees(BinaryTree tree1, BinaryTree tree2) {
    if (tree1 == null) {
      return tree2;
    }

    Stack<BinaryTree[]> stack = new Stack<>();
    stack.add(new BinaryTree[] { tree1, tree2 });

    while (!stack.isEmpty()) {
      BinaryTree[] trees = stack.pop();
      BinaryTree tree1Node = trees[0];
      BinaryTree tree2Node = trees[1];

      if (tree2Node == null) {
        continue;
      }

      tree1Node.value += tree2Node.value;

      if (tree1Node.left == null) {
        tree1Node.left = tree2Node.left;
      } else {
        stack.add(new BinaryTree[] { tree1Node.left, tree2Node.left });
      }

      if (tree1Node.right == null) {
        tree1Node.right = tree2Node.right;
      } else {
        stack.add(new BinaryTree[] { tree1Node.right, tree2Node.right });
      }
    }

    return tree1;
  }
}
