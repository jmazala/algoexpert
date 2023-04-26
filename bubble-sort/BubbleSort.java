class BubbleSort {
  public static int[] bubbleSort(int[] array) {
    boolean keepGoing = true;
    int iterations = 0;

    while (keepGoing) {
      keepGoing = false;

      for (int i = 0; i < array.length - 1 - iterations; i++) {
        if (array[i + 1] < array[i]) {
          int temp = array[i];
          array[i] = array[i + 1];
          array[i + 1] = temp;
          keepGoing = true;
        }
      }

      iterations++;
    }

    return array;
  }
}
