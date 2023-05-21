
// https://www.algoexpert.io/questions/cycle-in-graph
import java.util.*;

class CycleInGraph {

  private class CycleInfo {
    public boolean foundCycle = false;
    public Set<Integer> visited = new HashSet<>();
    public Set<Integer> inCycle = new HashSet<>();
  }

  public boolean cycleInGraph(int[][] edges) {
    CycleInfo info = new CycleInfo();

    for (int node = 0; node < edges.length; node++) {
      if (info.visited.contains(node)) {
        continue;
      }

      info.inCycle = new HashSet<>();
      visit(node, edges, info);
    }

    return info.foundCycle;
  }

  private static void visit(int node, int[][] edges, CycleInfo info) {
    if (info.inCycle.contains(node)) {
      info.foundCycle = true;
    }

    if (info.foundCycle) {
      return;
    }

    info.visited.add(node);
    info.inCycle.add(node);

    for (int nextNode : edges[node]) {
      visit(nextNode, edges, info);
    }

    info.inCycle.remove(node);
  }
}
