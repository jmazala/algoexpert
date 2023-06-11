// https://www.algoexpert.io/questions/largest-island

import java.util.*;

class LargestIsland {
  final static int WATER = 1;
  final static int LAND = 0;
  final static String DELIMITER = ",";
  final static int[][] DIRECTIONS = new int[][] { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };

  private List<Set<String>> islands = new ArrayList<>();;
  private List<int[]> water = new ArrayList<>();

  public int largestIsland(int[][] matrix) {
    this.getIslandsAndWater(matrix);

    int largestIsland = Integer.MIN_VALUE;

    for (int[] currentWater : this.water) {
      // Start with 1 as we have changed this water to land
      int potentialIslandSize = 1;

      for (Set<String> island : this.islands) {
        if (isAdjacent(currentWater, island)) {
          potentialIslandSize += island.size();
        }
      }

      largestIsland = Math.max(largestIsland, potentialIslandSize);
    }

    return largestIsland;
  }

  private static boolean isAdjacent(int[] coordinate, Set<String> island) {
    for (int[] direction : DIRECTIONS) {
      int nextI = coordinate[0] + direction[0];
      int nextJ = coordinate[1] + direction[1];
      String key = getKey(nextI, nextJ);
      if (island.contains(key)) {
        return true;
      }
    }

    return false;
  }

  private void getIslandsAndWater(int[][] matrix) {
    Set<String> visited = new HashSet<>();
    int m = matrix.length;
    int n = matrix[0].length;

    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if (matrix[i][j] == WATER) {
          this.water.add(new int[] { i, j });
        } else {
          String key = getKey(i, j);
          if (visited.contains(key)) {
            continue;
          }

          Queue<int[]> queue = new LinkedList<>();
          Set<String> island = new HashSet<>();
          queue.add(new int[] { i, j });
          visited.add(key);
          island.add(key);

          while (!queue.isEmpty()) {
            int[] currentCoordinates = queue.remove();
            int curI = currentCoordinates[0];
            int curJ = currentCoordinates[1];

            for (int[] direction : DIRECTIONS) {
              int nextI = curI + direction[0];
              int nextJ = curJ + direction[1];
              String nextKey = getKey(nextI, nextJ);

              if (nextI >= m || nextJ >= n || nextI < 0 || nextJ < 0 || matrix[nextI][nextJ] != LAND
                  || visited.contains(nextKey)) {
                continue;
              }

              visited.add(nextKey);
              island.add(nextKey);
              queue.add(new int[] { nextI, nextJ });
            }
          }

          this.islands.add(island);
        }
      }
    }
  }

  private static String getKey(int i, int j) {
    return i + DELIMITER + j;
  }

  public static void main(String[] args) {
    LargestIsland li = new LargestIsland();
    int[][] matrix1 = new int[][] { { 0, 1, 1 }, { 0, 0, 1 }, { 1, 1, 0 } };
    System.out.println(li.largestIsland(matrix1)); // 5
  }
}
