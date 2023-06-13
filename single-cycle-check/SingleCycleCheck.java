// https://www.algoexpert.io/questions/single-cycle-check

public class SingleCycleCheck {
  /*
   * single cycle means we can start anywhere, visit every node exactly once,
   * returning to the same node
   */
  public static boolean hasSingleCycle(int[] array) {
    // we can start anywhere. lets pick 0
    final int startingIndex = 0;
    int currentIndex = startingIndex;
    int leftToVisit = array.length - 1;

    while (leftToVisit >= 0) {
      int nextIndex = (currentIndex + array[currentIndex]) % array.length;

      // If we jump backwards and wrap around
      if (nextIndex < 0) {
        nextIndex = array.length + nextIndex;
      }

      // We arrived at startingIndex too early
      if (nextIndex == startingIndex && leftToVisit > 0) {
        return false;
      }

      leftToVisit--;
      currentIndex = nextIndex;
    }

    // No nodes left to visit. We should be back at the start
    return currentIndex == startingIndex;
  }

  public static void main(String[] args) {
    System.out.println(hasSingleCycle(new int[] { 2, 3, 1, -4, -4, 2 })); // true
  }
}
