// https://www.algoexpert.io/questions/invert-binary-tree

public class InvertBinaryTree {
  static class BinaryTree {
    public int value;
    public BinaryTree left;
    public BinaryTree right;

    public BinaryTree(int value) {
      this.value = value;
    }
  }

  public static void invertBinaryTree(BinaryTree tree) {
    if (tree == null) {
      return;
    }

    BinaryTree temp = tree.left;
    tree.left = tree.right;
    tree.right = temp;
    invertBinaryTree(tree.left);
    invertBinaryTree(tree.right);
  }

  public static void main(String[] args) {
    BinaryTree[] trees = new BinaryTree[9];
    for (int i = 0; i < 9; i++) {
      trees[i] = new BinaryTree(i + 1);
    }

    trees[0].left = trees[1];
    trees[0].right = trees[2];
    trees[1].left = trees[3];
    trees[1].right = trees[4];
    trees[3].left = trees[7];
    trees[3].right = trees[8];
    trees[2].left = trees[5];
    trees[2].right = trees[6];

    invertBinaryTree(trees[0]);
  }
}
