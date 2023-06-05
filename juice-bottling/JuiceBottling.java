
// https://www.algoexpert.io/questions/juice-bottling
import java.util.*;

class JuiceBottling {
  static class ProblemInfo {
    ArrayList<Integer> prefix = new ArrayList<>();
    ArrayList<Integer> output = new ArrayList<>();
  }

  /*
   * See JS for dp iterative solution
   */
  public static ArrayList<Integer> juiceBottling(int[] prices) {
    int n = prices.length; // 2
    int totalUnits = n - 1; // 1
    ProblemInfo info = new ProblemInfo();
    helper(prices, totalUnits, 0, info);
    return info.output; // []
  }

  private static int getRevenue(int[] prices, ArrayList<Integer> points) {
    int revenue = 0;

    for (Integer point : points) {
      revenue += prices[point];
    }

    return revenue;
  }

  private static void helper(int[] prices, int totalUnits, int currentUnits, ProblemInfo info) {
    // [0,1] | 1 | 0 | [] | {}
    if (currentUnits == totalUnits) {
      if (getRevenue(prices, info.prefix) > getRevenue(prices, info.output)) {
        info.output = new ArrayList<Integer>(info.prefix);
      }

      return;
    }

    for (int i = 1; i < prices.length; i++) {
      if (currentUnits + i <= totalUnits) {
        info.prefix.add(i);
        helper(prices, totalUnits, currentUnits + i, info);
        info.prefix.remove(info.prefix.size() - 1);
      }
    }

    return;
  }

  public static void main(String[] args) {
    System.out.println(juiceBottling(new int[] { 0, 1, 3, 2 }));
  }
}
