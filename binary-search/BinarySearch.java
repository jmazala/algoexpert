class BinarySearch {
  public static int binarySearch(int[] array, int target) {
    if (array == null || array.length == 0) {
      return -1;
    }

    int low = 0;
    int high = array.length - 1;

    while (low <= high) {
      int mid = low + (high - low) / 2;
      if (array[mid] == target) {
        return mid;
      }

      if (array[mid] > target) {
        high = mid - 1;
      } else {
        low = mid + 1;
      }
    }

    return -1;
  }
}
