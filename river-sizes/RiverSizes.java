// https://www.algoexpert.io/questions/river-sizes

import java.util.*;

public class RiverSizes {
  final static int LAND = 0;
  final static int WATER = 1;
  final static int VISITED = 2;
  final static int[] LEFT = new int[] { 0, -1 };
  final static int[] RIGHT = new int[] { 0, 1 };
  final static int[] UP = new int[] { -1, 0 };
  final static int[] DOWN = new int[] { 1, 0 };
  final static int[][] DIRECTIONS = new int[][] { LEFT, RIGHT, UP, DOWN };

  static class QueueItem {
    int i;
    int j;

    public QueueItem(int i, int j) {
      this.i = i;
      this.j = j;
    }
  }

  public static List<Integer> riverSizes(int[][] matrix) {
    List<Integer> output = new ArrayList<>();
    int m = matrix.length;
    int n = matrix[0].length;

    // from each point in the matrix, try to detect a new river
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        bfs(i, j, m, n, matrix, output);
      }
    }

    return output;
  }

  private static void bfs(int i, int j, int m, int n, int[][] matrix, List<Integer> output) {
    if (matrix[i][j] != WATER) {
      return;
    }

    int size = 0;
    Queue<QueueItem> queue = new LinkedList<>();
    queue.add(new QueueItem(i, j));
    matrix[i][j] = VISITED;

    while (!queue.isEmpty()) {
      int queueSize = queue.size();
      for (int rep = 0; rep < queueSize; rep++) {
        QueueItem item = queue.remove();
        int curI = item.i;
        int curJ = item.j;
        size++;

        for (int[] next : DIRECTIONS) {
          int nextI = curI + next[0];
          if (nextI == m || nextI < 0) {
            continue;
          }

          int nextJ = curJ + next[1];
          if (nextJ == n || nextJ < 0) {
            continue;
          }

          if (matrix[nextI][nextJ] == WATER) {
            queue.add(new QueueItem(nextI, nextJ));
            matrix[nextI][nextJ] = VISITED;
          }
        }
      }
    }

    output.add(size);
  }

  public static void main(String[] args) {
    int[][] matrix = new int[][] {
        { 1, 0, 0, 1, 0 },
        { 1, 0, 1, 0, 0 },
        { 0, 0, 1, 0, 1 },
        { 1, 0, 1, 0, 1 },
        { 1, 0, 1, 1, 0 }
    };

    System.out.println(riverSizes(matrix).toString()); // {2, 1, 5, 2, 2}
  }
}
