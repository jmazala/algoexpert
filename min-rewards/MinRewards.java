// https://www.algoexpert.io/questions/min-rewards

import java.util.*;

class MinRewards {
  /*
   * TIME: O(2n)
   * SPACE: O(n)
   */
  public static int minRewards(int[] scores) {
    int[] rewards = new int[scores.length];
    Arrays.fill(rewards, 1);

    // left to right
    for (int i = 1; i < scores.length; i++) {
      if (scores[i] > scores[i - 1]) {
        rewards[i] = rewards[i - 1] + 1;
      }
    }

    // right to left
    for (int i = scores.length - 2; i >= 0; i--) {
      if (scores[i] > scores[i + 1]) {
        rewards[i] = Math.max(rewards[i], rewards[i + 1] + 1);
      }
    }

    return Arrays.stream(rewards).sum();
  }

  /*
   * TIME: Worst case is O(n^2) if we have only decreasing scores left to right.
   * As we'd have to backtrack n-1 spaces every time
   * SPACE: O(n)
   */
  public static int minRewards2(int[] scores) {
    int[] rewards = new int[scores.length];
    Arrays.fill(rewards, 1);

    for (int i = 1; i < scores.length; i++) {
      if (scores[i] < scores[i - 1]) {
        int j = i - 1;
        while (j >= 0 && scores[j + 1] < scores[j] && rewards[j + 1] >= rewards[j]) {
          rewards[j]++;
          j--;
        }
      } else if (scores[i] > scores[i - 1]) {
        rewards[i] = rewards[i - 1] + 1;
      }
    }

    return Arrays.stream(rewards).sum();
  }

  public static void main(String[] args) {
    System.out.println(minRewards(new int[] { 8, 4, 2, 1, 3, 6, 7, 9, 5 })); // 25
  }
}
