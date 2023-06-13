// https://www.algoexpert.io/questions/index-equals-value

class IndexEqualsValue {
  /*
   * TIME: O(log n) since we binary search
   * SPACE: O(1) for low / high / output ints
   * 
   * NOTE: After we find a value and set output, we keep searching the left side.
   * Problem statement says find the *lowest* i such that array[i] == i
   * So we keep iterating to see if we can find a lower i
   */
  public int indexEqualsValue(int[] array) {
    int low = 0;
    int high = array.length - 1;
    int output = -1;

    while (low <= high) {
      int mid = (low + high) / 2;
      if (array[mid] == mid) {
        output = mid;
        high = mid - 1;
        continue;
      }

      if (array[mid] < mid) {
        low = mid + 1;
        continue;
      }

      high = mid - 1;
    }

    return output;
  }
}
