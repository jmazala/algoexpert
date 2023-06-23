// https://www.algoexpert.io/questions/search-for-range

public class SearchForRange {
  public static int[] searchForRange(int[] array, int target) {
    int[] answer = new int[] { -1, -1 };
    helper(0, array.length - 1, target, array, answer, true, true);
    return answer;
  }

  private static void helper(int low, int high, int target, int[] array, int[] answer, boolean lookLower,
      boolean lookHigher) {
    while (low <= high) {
      int mid = (low + high) / 2;
      int val = array[mid];

      if (val == target) {
        answer[0] = (answer[0] == -1 ? mid : Math.min(mid, answer[0]));
        answer[1] = (answer[1] == -1 ? mid : Math.max(mid, answer[1]));

        if (lookLower) {
          helper(low, mid - 1, target, array, answer, true, false);
        }

        if (lookHigher) {
          helper(mid + 1, high, target, array, answer, false, true);
        }

        break;
      } else if (val < target) {
        low = mid + 1;
      } else {
        high = mid - 1;
      }
    }
  }
}
