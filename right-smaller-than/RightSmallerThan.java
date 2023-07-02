// https://www.algoexpert.io/questions/right-smaller-than

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class RightSmallerThan {
  static class BST {
    int value;
    BST left;
    BST right;
    int leftSubtreeSize;
    int outputIndex;

    public BST(int value, int outputIndex) {
      this.outputIndex = outputIndex;
      this.value = value;
      this.leftSubtreeSize = 0;
    }

    public void insertNode(int value, int index) {
      if (value >= this.value) {
        if (this.right == null) {
          this.right = new BST(value, index);
        } else {
          this.right.insertNode(value, index);
        }
      } else {
        this.incrementNodeAndRightSubtree(true);
        if (this.left == null) {
          this.left = new BST(value, index);
        } else {
          this.left.insertNode(value, index);
        }
      }
    }

    private void incrementNodeAndRightSubtree(boolean firstTime) {
      this.leftSubtreeSize++;
      if (this.right != null) {
        this.right.incrementNodeAndRightSubtree(false);
      }

      if (!firstTime) {
        if (this.left != null) {
          this.left.incrementNodeAndRightSubtree(false);
        }
      }
    }
  }

  private static void traverseAndFillOutput(BST head, Integer[] output) {
    Queue<BST> queue = new LinkedList<>();
    queue.add(head);

    while (!queue.isEmpty()) {
      BST node = queue.remove();
      output[node.outputIndex] = node.leftSubtreeSize;

      if (node.left != null) {
        queue.add(node.left);
      }

      if (node.right != null) {
        queue.add(node.right);
      }
    }
  }

  public static List<Integer> rightSmallerThan(List<Integer> array) {
    if (array.isEmpty()) {
      return new ArrayList<Integer>();
    }

    if (array.size() == 1) {
      return Arrays.asList(new Integer[] { 0 });
    }

    Integer[] output = new Integer[array.size()];
    BST head = new BST(array.get(0), 0);
    for (int i = 1; i < array.size(); i++) {
      head.insertNode(array.get(i), i);
    }

    traverseAndFillOutput(head, output);
    return Arrays.asList(output);
  }

  public static void main(String[] args) {
    System.out.println(rightSmallerThan(Arrays.asList(new Integer[] {})).toString()); // []
    System.out.println(rightSmallerThan(Arrays.asList(new Integer[] { 5 })).toString()); // [0]
    System.out.println(rightSmallerThan(Arrays.asList(new Integer[] { 5, 6 })).toString()); // [0, 0]
    System.out.println(rightSmallerThan(Arrays.asList(new Integer[] { 5, 4 })).toString()); // [1, 0]

    // [5, 4, 4, 0, 1, 1, 0]
    System.out.println(rightSmallerThan(Arrays.asList(new Integer[] { 8, 5, 11, -1, 3, 4, 2 })).toString());
  }
}
