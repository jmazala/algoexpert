// https://www.algoexpert.io/questions/apartment-hunting

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class ApartmentHunting {

  /*
   * This is definitely a DP problem, and the complexity really just comes from
   * converting around
   * through data types (i.e. string for reqs, which are keys in the blocks hash).
   * The other complexity is from the score. Each block's score represents how far
   * the furthest requirement is from it. i.e. if you need a gym and a store,
   * if the gym is 1 block away and the store is 5 blocks away, the score is 5
   * because you must go to the store. That would still be worse than a block with
   * a store 3 blocks away and a store 3 blocks away. Even though you have to walk
   * 6 to go to both, your max is 5.
   * 
   * TIME: O(br) for loops through blocks and reqs (1 left to right, 1 right to
   * left, 1 for score)
   * SPACE: O(br) for distances matrix
   */
  public static int apartmentHunting(List<Map<String, Boolean>> blocks, String[] reqs) {
    // left to right
    int[][] distances = new int[reqs.length][blocks.size()];

    for (int reqIndex = 0; reqIndex < reqs.length; reqIndex++) {
      int distance = Integer.MAX_VALUE;
      String req = reqs[reqIndex];

      for (int blockIndex = 0; blockIndex < blocks.size(); blockIndex++) {
        if (blocks.get(blockIndex).get(req)) {
          distance = 0;
        } else {
          distance = distance == Integer.MAX_VALUE ? Integer.MAX_VALUE : distance + 1;
        }

        distances[reqIndex][blockIndex] = distance;
      }
    }

    // right to left
    for (int reqIndex = 0; reqIndex < reqs.length; reqIndex++) {
      int distance = Integer.MAX_VALUE;
      String req = reqs[reqIndex];

      for (int blockIndex = blocks.size() - 1; blockIndex >= 0; blockIndex--) {
        if (blocks.get(blockIndex).get(req)) {
          distance = 0;
        } else {
          distance = distance == Integer.MAX_VALUE ? Integer.MAX_VALUE : distance + 1;
        }

        distances[reqIndex][blockIndex] = Math.min(distances[reqIndex][blockIndex], distance);
      }
    }

    int bestScore = Integer.MAX_VALUE;
    int bestBlock = -1;

    for (int blockIndex = 0; blockIndex < blocks.size(); blockIndex++) {
      int blockScore = 0;
      for (int reqIndex = 0; reqIndex < reqs.length; reqIndex++) {
        blockScore = Math.max(blockScore, distances[reqIndex][blockIndex]);
      }

      if (blockScore < bestScore) {
        bestScore = blockScore;
        bestBlock = blockIndex;
      }
    }

    return bestBlock;
  }

  public static void main(String[] args) {
    List<Map<String, Boolean>> blocks = new ArrayList<>();
    Boolean[][] values = new Boolean[][] { { false, true, false }, { true, false, false }, { true, true, false },
        { false, true, false }, { false, true, true } };

    for (Boolean[] value : values) {
      Map<String, Boolean> hash = new HashMap<>();
      hash.put("gym", value[0]);
      hash.put("school", value[1]);
      hash.put("store", value[2]);
      blocks.add(hash);
    }
    String[] reqs = new String[] { "gym", "school", "store" };

    System.out.println(apartmentHunting(blocks, reqs)); // 3
  }
}
