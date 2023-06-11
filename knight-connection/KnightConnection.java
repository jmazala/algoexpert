// https://www.algoexpert.io/questions/knight-connection

import java.util.*;

class KnightConnection {
  private static String DELIMITER = "|";

  static class QueueItem {
    int[] knight;
    int numMoves;

    public QueueItem(int[] knight, int numMoves) {
      this.knight = knight;
      this.numMoves = numMoves;
    }
  }

  public static int knightConnection(int[] knightA, int[] knightB) throws Exception {
    Queue<QueueItem> queue = new LinkedList<>();
    Set<String> visited = new HashSet<>();
    queue.add(new QueueItem(knightA, 0));
    visited.add(getKey(knightA));

    while (!queue.isEmpty()) {
      QueueItem item = queue.remove();

      knightA = item.knight;
      int numMoves = item.numMoves;

      /*
       * The number of turns needed for 2 knights to meet on a common square
       * is the same as number of turns needed for 1 knight to meet the other knight
       * divided by two. Round up to account for odd numbers of moves
       */
      if (areSameSpot(knightA, knightB)) {
        return (int) Math.ceil((double) numMoves / 2);
      }

      int[][] nextMoves = new int[][] {
          moveUpTwo(moveRightOne(knightA)),
          moveUpTwo(moveLeftOne(knightA)),
          moveDownTwo(moveRightOne(knightA)),
          moveDownTwo(moveLeftOne(knightA)),
          moveLeftTwo(moveUpOne(knightA)),
          moveLeftTwo(moveDownOne(knightA)),
          moveRightTwo(moveUpOne(knightA)),
          moveRightTwo(moveDownOne(knightA))
      };

      for (int[] nextKnight : nextMoves) {
        String key = getKey(nextKnight);
        if (!visited.contains(key)) {
          visited.add(key);
          queue.add(new QueueItem(nextKnight, numMoves + 1));
        }
      }

    }

    throw new Exception("will never happen");
  }

  private static String getKey(int[] knight) {
    return knight[0] + DELIMITER + knight[1];
  }

  private static int[] moveUp(int[] knight, int spaces) {
    return new int[] { knight[0], knight[1] + spaces };
  }

  private static int[] moveDown(int[] knight, int spaces) {
    return new int[] { knight[0], knight[1] - spaces };
  }

  private static int[] moveLeft(int[] knight, int spaces) {
    return new int[] { knight[0] - spaces, knight[1] };
  }

  private static int[] moveRight(int[] knight, int spaces) {
    return new int[] { knight[0] + spaces, knight[1] };
  }

  private static int[] moveUpTwo(int[] knight) {
    return moveUp(knight, 2);
  }

  private static int[] moveUpOne(int[] knight) {
    return moveUp(knight, 1);
  }

  private static int[] moveDownTwo(int[] knight) {
    return moveDown(knight, 2);
  }

  private static int[] moveDownOne(int[] knight) {
    return moveDown(knight, 1);
  }

  private static int[] moveLeftOne(int[] knight) {
    return moveLeft(knight, 1);
  }

  private static int[] moveLeftTwo(int[] knight) {
    return moveLeft(knight, 2);
  }

  private static int[] moveRightOne(int[] knight) {
    return moveRight(knight, 1);
  }

  private static int[] moveRightTwo(int[] knight) {
    return moveRight(knight, 2);
  }

  private static boolean areSameSpot(int[] knightA, int[] knightB) {
    return knightA[0] == knightB[0] && knightA[1] == knightB[1];
  }

  public static void main(String[] args) {
    System.out.println(knightConnection(new int[] { 0, 0 }, new int[] { 0, 0 })); // 0
    System.out.println(knightConnection(new int[] { 0, 0 }, new int[] { 4, 2 })); // 1
    System.out.println(knightConnection(new int[] { 0, 7 }, new int[] { 7, 0 })); // 3
    System.out.println(knightConnection(new int[] { 0, 8 }, new int[] { 8, 0 })); // 3
  }
}
