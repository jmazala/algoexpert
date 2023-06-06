// https://www.algoexpert.io/questions/largest-rectangle-under-skyline

import java.util.*;

class LargestRectangleUnderSkyline {

  static class StackItem {
    int i;
    int height;

    public StackItem(int i, int height) {
      this.i = i;
      this.height = height;
    }
  }

  /*
   * In solution 2 O(n^2) we reconsider buildings multiple times.
   * Use a stack here to find left / right bounds much faster.
   * TIME: O(n)
   * SPACE: O(n)
   */
  public static int largestRectangleUnderSkyline(ArrayList<Integer> buildings) {
    if (buildings.size() == 0) {
      return 0;
    }

    if (buildings.size() == 1) {
      return buildings.get(0);
    }

    /*
     * no building has height lower than 1 according to problem statement
     * so a little shortcut we can take is assume a rectangle of every building
     * with size 1.
     * 
     * Of course, the min height might actually be higher than 1,
     * that will be accounted for at the end
     */
    int largest = Integer.MIN_VALUE;
    Stack<StackItem> stack = new Stack<>();

    // add one more building with size 0 to clear stack at the end
    buildings.add(0);

    for (int i = 0; i < buildings.size(); i++) {
      int buildingHeight = buildings.get(i);

      while (!stack.isEmpty() && stack.peek().height >= buildingHeight) {
        StackItem item = stack.pop();
        int width = stack.isEmpty() ? i : i - stack.peek().i - 1;
        largest = Math.max(largest, width * item.height);
      }

      stack.push(new StackItem(i, buildingHeight));
    }

    return largest;
  }

  /*
   * Brute force solution is O(n^2)
   * For every building, search to the left and the right for <= heights, and
   * expand rectangle
   * TIME: O(n) for loop * O(n) for sliding window inside loop = O(n^2)
   * SPACE: O(1)
   */
  public static int largestRectangleUnderSkyline2(ArrayList<Integer> buildings) {
    if (buildings.size() == 0) {
      return 0;
    }

    if (buildings.size() == 1) {
      return buildings.get(0);
    }

    int largest = Integer.MIN_VALUE;

    for (int i = 0; i < buildings.size(); i++) {
      int start = i;
      int end = i;
      int height = buildings.get(i);

      while (start > 0 && buildings.get(start - 1) >= height) {
        start--;
      }

      while (end < buildings.size() - 1 && buildings.get(end + 1) >= height) {
        end++;
      }

      largest = Math.max(largest, height * (end - start + 1));
    }

    return largest;
  }

  public static void main(String[] args) {
    System.out.println(largestRectangleUnderSkyline(
        new ArrayList<Integer>(Arrays.asList(new Integer[] { 1, 3, 3, 2, 4, 1, 5, 3, 2 })))); // 9
  }
}
