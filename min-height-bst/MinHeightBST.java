// https://www.algoexpert.io/questions/min-height-bst

import java.util.*;

class MinHeightBST {
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

    /*
     * Problem statement says array is already sorted
     */
    public static BST minHeightBst(List<Integer> array) {
      return constructBST(array, 0, array.size() - 1);
    }

    private static BST constructBST(List<Integer> array, int start, int end) {
      if (start > end) {
        return null;
      }

      int mid = (start + end) / 2;
      BST node = new BST(array.get(mid));
      node.left = constructBST(array, start, mid - 1);
      node.right = constructBST(array, mid + 1, end);
      return node;
    }
  }

  public static void main(String[] args) {
    BST.minHeightBst(new ArrayList<Integer>(Arrays.asList(new Integer[] { 1, 2, 3, 4, 5 })));
    BST.minHeightBst(new ArrayList<Integer>(Arrays.asList(new Integer[] { 1, 2, 3, 4, 5, 6 })));
  }
}
