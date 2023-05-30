// https://www.algoexpert.io/questions/powerset

import java.util.*;

public class Powerset {
  public static List<List<Integer>> powerset(List<Integer> array) {
    List<List<Integer>> output = new ArrayList<>();
    List<Integer> prefix = new ArrayList<>();
    helper(0, array, output, prefix);
    return output;
  }

  private static void helper(int i, List<Integer> array, List<List<Integer>> output, List<Integer> prefix) {
    if (i >= array.size()) {
      output.add(new ArrayList<>(prefix));
      return;
    }

    // take the value at [i]
    prefix.add(array.get(i));
    helper(i + 1, array, output, prefix);
    prefix.remove(prefix.size() - 1);
    // don't take the value at i
    helper(i + 1, array, output, prefix);
  }

  public static void main(String[] args) {
    List<List<Integer>> l = powerset(new ArrayList<Integer>(Arrays.asList(new Integer[] { 1, 2, 3 })));
    for (List<Integer> set : l) {
      System.out.print(set.toString());
    }
  }
}
