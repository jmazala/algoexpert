public class WaterArea {
  /*
   * TIME: O(n) for while loop
   * SPACE: O(1) for 5 vars
   */
  public static int waterArea(int[] heights) {
    if (heights.length == 0) {
      return 0;
    }

    int water = 0;
    int left = 0;
    int right = heights.length - 1;
    int leftMax = heights[left];
    int rightMax = heights[right];

    /*
     * Traverse inwards, side depends on which wall is lower at each step.
     */
    while (left < right) {
      int leftHeight = heights[left];
      int rightHeight = heights[right];

      if (leftHeight < rightHeight) {
        left++;
        leftMax = Math.max(leftMax, heights[left]);
        water += leftMax - heights[left];
      } else {
        right--;
        rightMax = Math.max(rightMax, heights[right]);
        water += rightMax - heights[right];
      }
    }

    return water;
  }

  public static void main(String[] args) {
    System.out.println(waterArea(new int[] { 0, 8, 0, 0, 5, 0, 0, 10, 3, 3, 0, 0, 1, 1, 0, 3 })); // 48
  }
}
