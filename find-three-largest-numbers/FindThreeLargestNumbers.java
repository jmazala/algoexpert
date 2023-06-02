import java.util.*;

class FindThreeLargestNumbers {
  public static int[] findThreeLargestNumbers(int[] array) {
    PriorityQueue<Integer> heap = new PriorityQueue<>((a, b) -> a - b);

    for (int i : array) {
      heap.add(i);
      if (heap.size() > 3) {
        heap.remove();
      }
    }

    int[] answer = new int[3];
    for (int i = 0; i < 3; i++) {
      answer[i] = heap.remove();
    }

    return answer;
  }
}
