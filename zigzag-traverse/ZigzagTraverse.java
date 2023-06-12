// https://www.algoexpert.io/questions/zigzag-traverse

import java.util.*;

class ZigzagTraverse {
  public static List<Integer> zigzagTraverse(final List<List<Integer>> array) {
    final int m = array.size();
    final int n = array.get(0).size();
    boolean goingDown = true;
    int i = 0;
    int j = 0;

    final List<Integer> answer = new LinkedList<>();

    while (answer.size() < m * n) {
      answer.add(array.get(i).get(j));

      // going down and left is a problem at max row or min col
      if (goingDown) {
        if (i == m - 1) {
          goingDown = false;
          j++;
          continue;
        }

        if (j == 0) {
          goingDown = false;
          i++;
          continue;
        }

        i++;
        j--;
        continue;
      }

      if (j == n - 1) {
        goingDown = true;
        i++;
        continue;
      }

      // going up and right is a problem at min row or max col
      if (i == 0) {
        goingDown = true;
        j++;
        continue;
      }

      i--;
      j++;
    }

    return answer;
  }

  public static void main(String[] args) {
    List<List<Integer>> array = new ArrayList<>();
    array.add(new ArrayList<>(Arrays.asList(new Integer[] { 1, 3, 4, 10 })));
    array.add(new ArrayList<>(Arrays.asList(new Integer[] { 2, 5, 9, 11 })));
    array.add(new ArrayList<>(Arrays.asList(new Integer[] { 6, 8, 12, 15 })));
    array.add(new ArrayList<>(Arrays.asList(new Integer[] { 7, 13, 14, 16 })));
    System.out.println(zigzagTraverse(array).toString());
  }
}