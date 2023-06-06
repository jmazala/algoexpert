// https://www.algoexpert.io/questions/dijkstra's-algorithm

import java.util.*;

class DijkstrasAlgorithm {
  /*
   * TIME: O(v) where v is number of edges
   * SPACE: O(n) where n is number of nodes
   */
  public static int[] dijkstrasAlgorithm(int start, int[][][] edges) {
    int[] output = new int[edges.length];
    Arrays.fill(output, -1);
    helper(start, 0, edges, output);
    return output;
  }

  private static void helper(int currentNode, int currentCost, int[][][] edges, int[] output) {
    if (output[currentNode] != -1 && currentCost > output[currentNode]) {
      return;
    }

    if (currentCost < output[currentNode] || output[currentNode] == -1) {
      output[currentNode] = currentCost;
    }

    for (int[] edge : edges[currentNode]) {
      int nextNode = edge[0];
      int cost = edge[1];

      helper(nextNode, currentCost + cost, edges, output);
    }
  }

  /*
   * USING A MINHEAP IS PREFERRED
   */

  static class HeapItem {
    int node;
    int totalDistance;

    public HeapItem(int node, int totalDistance) {
      this.node = node;
      this.totalDistance = totalDistance;
    }
  }

  public static int[] dijkstrasAlgorithm2(int start, int[][][] edges) {
    int numberOfNodes = edges.length;
    int[] output = new int[numberOfNodes];

    Arrays.fill(output, -1);

    PriorityQueue<HeapItem> minHeap = new PriorityQueue<>(Comparator.comparingInt(item -> item.totalDistance));
    HashSet<Integer> visited = new HashSet<>();
    minHeap.add(new HeapItem(start, 0));

    while (!minHeap.isEmpty() && visited.size() != numberOfNodes) {
      HeapItem item = minHeap.poll();
      int currentNode = item.node;

      if (visited.contains(currentNode)) {
        continue;
      }

      visited.add(currentNode);

      int currentDistance = item.totalDistance;
      output[currentNode] = currentDistance;

      for (int[] edge : edges[currentNode]) {
        int nextNode = edge[0];
        if (!visited.contains(nextNode)) {
          minHeap.add(new HeapItem(nextNode, currentDistance + edge[1]));
        }
      }
    }

    return output;
  }

  public static void main(String[] args) {
    int[][][] edges = {
        { { 1, 7 } },
        { { 2, 6 }, { 3, 20 }, { 4, 3 } },
        { { 3, 14 } },
        { { 4, 2 } },
        {},
        {} };
    System.out.println(Arrays.toString(dijkstrasAlgorithm2(0, edges))); // [0, 7, 13, 27, 10, -1 ]
  }
}
