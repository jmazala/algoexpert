// https://www.algoexpert.io/questions/longest-peak
class LongestPeak {
  public static int longestPeak(int[] array) {
    int answer = 0;
    int i = 1;

    // traverse the array to find a peak
    while (i < array.length - 1) {
      boolean isPeak = array[i] > array[i - 1] && array[i] > array[i + 1];
      if (!isPeak) {
        i++;
        continue;
      }

      // once you've found a peak measure it's width
      int left = i - 1;
      while (left > 0 && array[left - 1] < array[left]) {
        left--;
      }

      int right = i + 1;
      while (right < array.length - 1 && array[right + 1] < array[right]) {
        right++;
      }

      answer = Math.max(answer, right - left + 1);
      i = right;
    }

    return answer;
  }

  public static void main(String[] args) {
    System.out.println(longestPeak(new int[] { 1, 2, 3, 3, 4, 0, 10, 6, 5, -1, -3, 2, 3 })); // 6
  }
}
