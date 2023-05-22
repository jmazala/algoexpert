// https://www.algoexpert.io/questions/two-colorable

import java.util.*;

class TwoColorable {
  private static final boolean COLOR_1 = true;

  /*
   * TIME: O(1) to start the stack, then O(e) after
   * SPACE: O(n) to store colors, O(e) for the stack
   */
  public boolean twoColorable(int[][] edges) {
    Boolean[] nodes = new Boolean[edges.length];
    Arrays.fill(nodes, null);
    nodes[0] = COLOR_1;

    Stack<Integer> stack = new Stack<>();
    stack.add(0);

    Set<Integer> visited = new HashSet<>();

    while (!stack.isEmpty()) {
      int node = stack.pop();
      if (visited.contains(node)) {
        continue;
      }
      visited.add(node);

      Boolean nodeColor = nodes[node];
      for (int nextNode : edges[node]) {
        Boolean nextColor = nodes[nextNode];
        if (nextColor == nodeColor) {
          return false;
        }

        if (nextColor == null) {
          nodes[nextNode] = !nodeColor;
        }

        stack.push(nextNode);
      }
    }

    return true;
  }
}
