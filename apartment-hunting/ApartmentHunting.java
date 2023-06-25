// https://www.algoexpert.io/questions/apartment-hunting

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Program {
  public static int apartmentHunting(List<Map<String, Boolean>> blocks, String[] reqs) {
    int[][] maxDistanceLeft = new int[reqs.length][blocks.size()];
    int[][] maxDistanceRight = new int[reqs.length][blocks.size()];

    for (int reqIndex = 0; reqIndex < reqs.length; reqIndex++) {
      String req = reqs[reqIndex];
      maxDistanceLeft[reqIndex][0] = blocks.get(0).get(req) ? 0 : Integer.MAX_VALUE;
      maxDistanceRight[reqIndex][blocks.size() - 1] = blocks.get(blocks.size() - 1).get(req) ? 0
          : Integer.MAX_VALUE;
    }

    for (int blockIndex = 1; blockIndex < blocks.size(); blockIndex++) {
      Map<String, Boolean> block = blocks.get(blockIndex);

      for (int reqIndex = 0; reqIndex < reqs.length; reqIndex++) {
        String req = reqs[reqIndex];

        if (block.get(req)) {
          maxDistanceLeft[reqIndex][blockIndex] = 0;
        } else {
          if (maxDistanceLeft[reqIndex][blockIndex - 1] == Integer.MAX_VALUE) {
            maxDistanceLeft[reqIndex][blockIndex] = Integer.MAX_VALUE;
          } else {
            maxDistanceLeft[reqIndex][blockIndex] = 1 + maxDistanceLeft[reqIndex][blockIndex - 1];
          }
        }
      }
    }

    for (int blockIndex = blocks.size() - 2; blockIndex >= 0; blockIndex--) {
      Map<String, Boolean> block = blocks.get(blockIndex);

      for (int reqIndex = 0; reqIndex < reqs.length; reqIndex++) {
        String req = reqs[reqIndex];

        if (block.get(req)) {
          maxDistanceRight[reqIndex][blockIndex] = 0;
        } else {
          if (maxDistanceRight[reqIndex][blockIndex + 1] == Integer.MAX_VALUE) {
            maxDistanceRight[reqIndex][blockIndex] = Integer.MAX_VALUE;
          } else {
            maxDistanceRight[reqIndex][blockIndex] = 1 + maxDistanceRight[reqIndex][blockIndex + 1];
          }
        }
      }
    }

    int bestIndex = 0;
    int bestValue = Integer.MAX_VALUE;

    for (int blockIndex = 0; blockIndex < blocks.size(); blockIndex++) {
      int maxDistanceForBlock = 0;

      for (int reqIndex = 0; reqIndex < reqs.length; reqIndex++) {
        int leftDistance = maxDistanceLeft[reqIndex][blockIndex];
        int rightDistance = maxDistanceRight[reqIndex][blockIndex];

        if (leftDistance == 0 || rightDistance == 0) {
          continue;
        }

        int distanceForReq = 0;

        if (leftDistance == Integer.MAX_VALUE) {
          distanceForReq = rightDistance;
        } else if (rightDistance == Integer.MAX_VALUE) {
          distanceForReq = leftDistance;
        } else {
          distanceForReq = Math.max(leftDistance, rightDistance);
        }

        maxDistanceForBlock = Math.max(maxDistanceForBlock, distanceForReq);
      }

      if (maxDistanceForBlock < bestValue) {
        bestValue = maxDistanceForBlock;
        bestIndex = blockIndex;
      }
    }

    return bestIndex;
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
