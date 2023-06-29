// https://www.algoexpert.io/questions/waterfall-streams

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

class WaterfallStreams {
  static class QueueItem {
    int i;
    int j;
    double percent;
    int direction;

    public QueueItem(int i, int j, double percent, int direction) {
      this.i = i;
      this.j = j;
      this.percent = percent;
      this.direction = direction;
    }
  }

  final static int DOWN = 0;
  final static int LEFT = 1;
  final static int RIGHT = 2;
  final static double BLOCK = 1;

  public static double[] waterfallStreams(double[][] array, int source) {
    final int n = array[0].length;

    double[] output = new double[n];

    Queue<QueueItem> queue = new LinkedList<>();
    queue.add(new QueueItem(0, source, 100.0, DOWN));

    while (!queue.isEmpty()) {
      QueueItem current = queue.remove();

      if (isBucket(current.i, array)) {
        output[current.j] += current.percent;
        continue;
      }

      if (canMoveDown(current.i, current.j, array)) {
        queue.add(new QueueItem(current.i + 1, current.j, current.percent, DOWN));
        continue;
      }

      boolean canMoveLeft = canMoveLeft(current.i, current.j, array, current.direction);
      boolean canMoveRight = canMoveRight(current.i, current.j, array, current.direction);

      if (canMoveLeft && canMoveRight) {
        queue.add(new QueueItem(current.i, current.j - 1, current.percent / 2.0, LEFT));
        queue.add(new QueueItem(current.i, current.j + 1, current.percent / 2.0, RIGHT));
      } else if (canMoveLeft) {
        double nextPercent = (current.direction == LEFT ? current.percent : current.percent / 2.0);
        queue.add(new QueueItem(current.i, current.j - 1, nextPercent, LEFT));
      } else if (canMoveRight) {
        double nextPercent = (current.direction == RIGHT ? current.percent : current.percent / 2.0);
        queue.add(new QueueItem(current.i, current.j + 1, nextPercent, RIGHT));
      }
    }

    return output;
  }

  private static boolean isBucket(int i, double[][] array) {
    return i == array.length - 1;
  }

  private static boolean canMoveDown(int i, int j, double[][] array) {
    return i < array.length - 1 && array[i + 1][j] != BLOCK;
  }

  private static boolean canMoveLeft(int i, int j, double[][] array, int direction) {
    return j != 0 && direction != RIGHT && array[i][j - 1] != BLOCK;
  }

  private static boolean canMoveRight(int i, int j, double[][] array, int direction) {
    return j < array[0].length - 1 && array[i][j + 1] != BLOCK && direction != LEFT;
  }

  public static void main(String[] args) {
    System.out.println(Arrays.toString(waterfallStreams(new double[][] {
        { 0, 0, 0, 0, 0, 0, 0 },
        { 1, 0, 0, 0, 0, 0, 0 },
        { 0, 0, 1, 1, 1, 0, 0 },
        { 0, 0, 0, 0, 0, 0, 0 },
        { 1, 1, 1, 0, 0, 1, 0 },
        { 0, 0, 0, 0, 0, 0, 1 },
        { 0, 0, 0, 0, 0, 0, 0 } }, 3))); // [0.0, 0.0, 0.0, 25.0, 25.0, 0.0, 0.0]

    System.out.println(Arrays.toString(waterfallStreams(new double[][] {
        { 0, 0, 0, 0, 0, 0, 0 },
        { 1, 0, 1, 0, 0, 0, 0 },
        { 0, 0, 1, 1, 1, 0, 0 },
        { 0, 0, 0, 0, 0, 0, 0 },
        { 1, 1, 1, 0, 0, 1, 0 },
        { 0, 0, 0, 0, 0, 0, 1 },
        { 0, 0, 0, 0, 0, 0, 0 }
    }, 3))); // [0, 0, 0, 0, 25, 0, 0]

  }

}
