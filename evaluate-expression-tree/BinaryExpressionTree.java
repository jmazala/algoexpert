
// https://www.algoexpert.io/questions/evaluate-expression-tree
import java.util.*;

class BinaryExpressionTree {
  // operands on left and right subtrees
  final static int ADDITION = -1;
  final static int SUBTRACTION = -2;
  final static int DIVISION = -3;
  final static int MULTIPLICATION = -4;

  // This is an input class. Do not edit.
  static class BinaryTree {
    public int value;
    public BinaryTree left = null;
    public BinaryTree right = null;

    public BinaryTree(int value) {
      this.value = value;
    }
  }

  public int evaluateExpressionTree(BinaryTree tree) {
    // problem statement says they are all positive integers
    if (tree.value >= 0) {
      return tree.value;
    }

    switch (tree.value) {
      case ADDITION:
        return evaluateExpressionTree(tree.left) + evaluateExpressionTree(tree.right);
      case SUBTRACTION:
        return evaluateExpressionTree(tree.left) - evaluateExpressionTree(tree.right);
      case DIVISION:
        return evaluateExpressionTree(tree.left) / evaluateExpressionTree(tree.right);
      case MULTIPLICATION:
        return evaluateExpressionTree(tree.left) * evaluateExpressionTree(tree.right);
    }

    return 0; // should never happen.
  }

  public static void main(String[] args) {

  }
}
