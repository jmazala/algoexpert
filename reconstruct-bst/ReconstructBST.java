import java.util.*;

class ReconstructBST {
  // This is an input class. Do not edit.
  static class BST {
    public int value;
    public BST left = null;
    public BST right = null;

    public BST(int value) {
      this.value = value;
    }
  }

  /*
   * TIME: O(n^2) because we are iterating through remaining values in a subtree
   * with every node
   * SPACE: O(n) as we are constructing arraylists to hold remaining node values
   */
  // public static BST reconstructBst(ArrayList<Integer> preOrderTraversalValues)
  // {
  // if (preOrderTraversalValues.isEmpty()) {
  // return null;
  // }

  // ArrayList<Integer> leftTreeValues = new ArrayList<>();
  // ArrayList<Integer> rightTreeValues = new ArrayList<>();

  // Integer currentValue = preOrderTraversalValues.get(0);
  // BST node = new BST(currentValue);

  // for (int i = 1; i < preOrderTraversalValues.size(); i++) {
  // if (preOrderTraversalValues.get(i) < currentValue) {
  // leftTreeValues.add(preOrderTraversalValues.get(i));
  // } else {
  // rightTreeValues.add(preOrderTraversalValues.get(i));
  // }
  // }

  // if (!leftTreeValues.isEmpty()) {
  // node.left = ReconstructBST.reconstructBst(leftTreeValues);
  // }

  // if (!rightTreeValues.isEmpty()) {
  // node.right = ReconstructBST.reconstructBst(rightTreeValues);
  // }

  // return node;
  // }

  private static class TrackerInfo {
    public int index;

    public TrackerInfo(int index) {
      this.index = index;
    }
  }

  public static BST reconstructBst(ArrayList<Integer> preOrderTraversalValues) {
    if (preOrderTraversalValues.isEmpty()) {
      return null;
    }

    TrackerInfo info = new TrackerInfo(0);

    return helper(preOrderTraversalValues, info, Integer.MIN_VALUE, Integer.MAX_VALUE);
  }

  private static BST helper(ArrayList<Integer> preOrderTraversalValues, TrackerInfo info, int lowerBound,
      int upperBound) {
    if (info.index >= preOrderTraversalValues.size()) {
      return null;
    }

    int currentNodeValue = preOrderTraversalValues.get(info.index);
    if (currentNodeValue < lowerBound || currentNodeValue >= upperBound) {
      return null;
    }

    BST node = new BST(currentNodeValue);
    info.index += 1;
    node.left = helper(preOrderTraversalValues, info, lowerBound, currentNodeValue);
    node.right = helper(preOrderTraversalValues, info, currentNodeValue, upperBound);
    return node;
  }
}
