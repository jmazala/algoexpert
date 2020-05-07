import java.util.*;

class Program {
  public static List<Integer> zigzagTraverse(final List<List<Integer>> array) {
    final int M = array.size();
    final int N = array.get(0).size();
    boolean goingDown = true;
    int i = 0;
    int j = 0;

    final List<Integer> answer = new LinkedList<>();

    while (answer.size() < M * N) {
      answer.add(array.get(i).get(j));

      // going down and left is a problem at max row or min col
      if (goingDown) {
        if (i == M - 1 || j == 0) {
          goingDown = false;
          if (i == M - 1) {
            j++;
          } else {
            i++;
          }
        } else {
          i++;
          j--;
        }
      } else {
        // going up and right is a problem at min row or max col
        if (i == 0 || j == N - 1) {
          goingDown = true;
          if (j == N - 1) {
            i++;
          } else {
            j++;
          }
        } else {
          i--;
          j++;
        }
      }
    }

    return answer;
  }
}