class FindClosestValueInBST {
  public static int findClosestValueInBst(BST tree, int target) {
    double answer = Double.MAX_VALUE;

    while (tree != null) {
      int difference = Math.abs(target - tree.value);
      if (difference == 0) {
        return target;
      }

      if (difference < Math.abs(answer - target)) {
        answer = tree.value;
      }

      if (tree.value < target) {
        tree = tree.right;
      } else {
        tree = tree.left;
      }
    }

    return (int) answer;
  }

  static class BST {
    public int value;
    public BST left;
    public BST right;

    public BST(int value) {
      this.value = value;
    }
  }
}
