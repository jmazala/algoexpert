class SelectionSort {
  public static int[] selectionSort(int[] array) {
    for (int i = 0; i < array.length; i++) {
      int j = i + 1;
      int minValue = array[i];
      int swapIndex = i;
      while (j < array.length) {
        if (array[j] < minValue) {
          minValue = array[j];
          swapIndex = j;
        }

        j++;
      }

      if (swapIndex != i) {
        int temp = array[i];
        array[i] = array[swapIndex];
        array[swapIndex] = temp;
      }
    }

    return array;
  }
}
