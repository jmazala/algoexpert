// https://www.algoexpert.io/questions/lowest-common-manager

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LowestCommonManager {

  /*
   * Traverse tree until you find both nodes reportOne and reportTwo,
   * recording depth of those nodes and parents of all nodes.
   * 
   * Starting at the lower node, traverse up until both nodes are at the same
   * level
   * in the org chart. Then traverse both nodes upwards until they reach the same
   * manager.
   * 
   * TIME: O(d) where d is the depth of the lower of reportOne and reportTwo
   * SPACE: O(d)
   */
  public static OrgChart getLowestCommonManager(
      OrgChart topManager, OrgChart reportOne, OrgChart reportTwo) {
    Map<OrgChart, Integer> depthMap = new HashMap<>();
    Map<OrgChart, OrgChart> parentsMap = new HashMap<>();
    findDepthAndPopulateParents(topManager, null, depthMap, parentsMap, reportOne, reportTwo, 0);

    int depthOne = depthMap.get(reportOne);
    int depthTwo = depthMap.get(reportTwo);

    while (depthOne < depthTwo) {
      reportTwo = parentsMap.get(reportTwo);
      depthTwo--;
    }

    while (depthTwo < depthOne) {
      reportOne = parentsMap.get(reportOne);
      depthOne--;
    }

    while (reportOne != reportTwo) {
      reportOne = parentsMap.get(reportOne);
      reportTwo = parentsMap.get(reportTwo);

      if (reportOne == topManager) {
        return topManager;
      }
    }

    return reportOne;
  }

  private static void findDepthAndPopulateParents(OrgChart node, OrgChart parent, Map<OrgChart, Integer> depthMap,
      Map<OrgChart, OrgChart> parentsMap, OrgChart reportOne, OrgChart reportTwo, int currentDepth) {
    if (depthMap.size() == 2) {
      return;
    }

    parentsMap.put(node, parent);

    if (node == reportOne || node == reportTwo) {
      depthMap.put(node, currentDepth);
    }

    for (OrgChart directReport : node.directReports) {
      findDepthAndPopulateParents(directReport, node, depthMap, parentsMap, reportOne, reportTwo, currentDepth + 1);
    }
  }

  static class OrgChart {
    public char name;
    public List<OrgChart> directReports;

    OrgChart(char name) {
      this.name = name;
      this.directReports = new ArrayList<OrgChart>();
    }

    // This method is for testing only.
    public void addDirectReports(OrgChart[] directReports) {
      for (OrgChart directReport : directReports) {
        this.directReports.add(directReport);
      }
    }
  }

  public static void main(String[] args) {
    OrgChart a = new OrgChart('A');
    OrgChart b = new OrgChart('B');
    OrgChart c = new OrgChart('C');
    OrgChart d = new OrgChart('D');
    OrgChart e = new OrgChart('E');
    OrgChart f = new OrgChart('F');
    OrgChart g = new OrgChart('G');
    OrgChart h = new OrgChart('H');
    OrgChart i = new OrgChart('I');

    d.addDirectReports(new OrgChart[] { h, i });
    b.addDirectReports(new OrgChart[] { d, e });
    c.addDirectReports(new OrgChart[] { f, g });
    a.addDirectReports(new OrgChart[] { b, c });

    System.out.println(getLowestCommonManager(a, e, i).name); // B
  }
}
