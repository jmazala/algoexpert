class Program {
  public static int[] subarraySort(int[] array) {
    if (array.length < 2) {
      return new int[] { -1, -1 };
    }

    int unsortedMin = Integer.MAX_VALUE;
    int unsortedMax = Integer.MIN_VALUE;

    for (int i = 0; i < array.length; i++) {
      if (isUnsorted(i, array)) {
        unsortedMin = Math.min(unsortedMin, array[i]);
        unsortedMax = Math.max(unsortedMax, array[i]);
      }
    }

    if (unsortedMin == Integer.MAX_VALUE) {
      return new int[] { -1, -1 };
    }

    int left = 0;
    while (array[left] <= unsortedMin) {
      left++;
    }

    int right = array.length - 1;
    while (array[right] >= unsortedMax) {
      right--;
    }

    return new int[] { left, right };
  }

  private static boolean isUnsorted(int i, int[] array) {
    if (i == 0) {
      return array[1] < array[0];
    }

    if (i == array.length - 1) {
      return array[i] < array[i - 1];
    }

    return array[i] < array[i - 1] || array[i] > array[i + 1];
  }
}
