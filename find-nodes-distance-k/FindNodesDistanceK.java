// https://www.algoexpert.io/questions/find-nodes-distance-k

import java.util.*;

class FindNodesDistanceK {
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
   * TIME: O(n)
   * buildMap is O(n)
   * BFS is O(n)
   * SPACE:
   * BFS is O(n) for queue
   * treeMap is O(2n) as each one is represented 2x
   */
  public ArrayList<Integer> findNodesDistanceK(BinaryTree tree, int target, int k) {
    // build a graph of the nodes
    Map<Integer, ArrayList<Integer>> treeMap = new HashMap<>();
    buildMap(tree, treeMap, null);

    // traverse k spaces to find nodes
    ArrayList<Integer> output = new ArrayList<>();
    Queue<Integer> queue = new LinkedList<>();
    queue.add(target);
    Set<Integer> visited = new HashSet<>();

    while (!queue.isEmpty()) {
      int queueSize = queue.size();
      ArrayList<Integer> nextValues = new ArrayList<>();

      for (int i = 0; i < queueSize; i++) {
        int currentValue = queue.remove();
        visited.add(currentValue);
        nextValues.addAll(treeMap.get(currentValue));
      }

      nextValues.removeIf(x -> visited.contains(x));
      k--;

      if (k == 0) {
        output = nextValues;
        break;
      }

      queue.addAll(nextValues);
    }

    return output;
  }

  private void buildMap(BinaryTree tree,
      Map<Integer, ArrayList<Integer>> treeMap, BinaryTree parent) {
    if (tree == null) {
      return;
    }

    treeMap.put(tree.value, new ArrayList<>());

    if (parent != null) {
      treeMap.get(parent.value).add(tree.value);
      treeMap.get(tree.value).add(parent.value);
    }

    buildMap(tree.left, treeMap, tree);
    buildMap(tree.right, treeMap, tree);
  }
}
