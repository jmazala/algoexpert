import java.util.*;

class Program {
  // Tip: You can use `element instanceof ArrayList` to check whether an item
  // is an array or an integer.
  public static int productSum(List<Object> array) {
    return helper(array, 1);
  }

  private static int helper(List<Object> array, int multiplier) {
    int answer = 0;

    for (Object element : array) {
      if (element instanceof List) {
        @SuppressWarnings("unchecked")
        ArrayList<Object> subList = (ArrayList<Object>) element;
        answer += helper(subList, multiplier + 1);
      } else {
        answer += (int) element;
      }
    }

    return answer * multiplier;
  }
}
