import java.util.*;

class Program {
  public static BST minHeightBst(List<Integer> array) {
    return helper(array, 0, array.size() - 1);
  }

  private static BST helper(List<Integer> array, int start, int end) {
    if (start > end) {
      return null;
    }

    int mid = start + (end - start) / 2;
    BST node = new BST(array.get(mid));
    node.left = helper(array, start, mid - 1);
    node.right = helper(array, mid + 1, end);
    return node;
  }

  static class BST {
    public int value;
    public BST left;
    public BST right;

    public BST(int value) {
      this.value = value;
      left = null;
      right = null;
    }

    public void insert(int value) {
      if (value < this.value) {
        if (left == null) {
          left = new BST(value);
        } else {
          left.insert(value);
        }
      } else {
        if (right == null) {
          right = new BST(value);
        } else {
          right.insert(value);
        }
      }
    }
  }
}
