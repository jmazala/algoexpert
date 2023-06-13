// https://www.algoexpert.io/questions/majority-element

class MajorityElement {

  public static int majorityElement(int[] array) {
    int answer = array[0];
    int count = 0;

    for (int val : array) {
      /*
       * At this point, guessed majority element
       * must not be the majority element in the subarray of a
       * that we've already looked at. Actual majority element
       * will be the majority element in the remaining subarray of a
       * we have yet to look at. This is because at most half of values
       * in the first subarray were majority element (otherwise, negative count)
       */
      if (count == 0) {
        answer = val;
      }

      if (val == answer) {
        count++;
      } else {
        count--;
      }
    }

    return answer;
  }

  /*
   * TIME: O(n^2 / 2)
   * SPACE: O(1)
   */
  public static int majorityElement2(int[] array) {
    int n = array.length;
    if (n == 1) {
      return array[0];
    }

    for (int i = 0; i < array.length; i++) {
      int curCount = 1;

      for (int j = 0; j < array.length; j++) {
        if (i == j) {
          continue;
        }

        if (array[j] == array[i]) {
          curCount++;
          if (curCount > n / 2) {
            return array[i];
          }
        }

      }
    }

    return -1; // will never happen according to problem statement
  }

  public static void main(String[] args) {
    System.out.println(majorityElement(new int[] { 1 })); // 1
    System.out.println(majorityElement(new int[] { 1, 1, 2 })); // 1
    System.out.println(majorityElement(new int[] { 1, 2, 3, 2, 2, 1, 2 })); // 2
  }
}
