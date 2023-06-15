// https://www.algoexpert.io/questions/disk-stacking

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class DiskStacking {
  final static int WIDTH = 0;
  final static int DEPTH = 1;
  final static int HEIGHT = 2;

  public static List<Integer[]> diskStacking(List<Integer[]> disks) {
    if (disks.size() == 0) {
      return new ArrayList<>();
    }

    if (disks.size() == 1) {
      return disks;
    }

    /*
     * sort the disks so we can use DP to solve this problem.
     * Because all 3 dimensions (width, depth, height) need to be in order,
     * We can just sort by 1 of them so we don't have to go n^n comparisons
     * 
     * This DP array stores the max height stack with disks.get(i) on bottom.
     * It could be improved by storing just the indices of the disks (per stack)
     * Instead of the entire 3 element Integer []
     * i.e. List<Stack<Integer>> rather than List<Stack<Integer []>>
     */
    Collections.sort(disks, (a, b) -> a[HEIGHT] - b[HEIGHT]);
    List<Stack<Integer[]>> dp = new ArrayList<>(disks.size());
    for (int i = 0; i < disks.size(); i++) {
      dp.add(i, new Stack<Integer[]>());
    }

    dp.get(0).push(disks.get(0));

    // Keep track of max height so we can return that stack as the answer
    int maxHeight = disks.get(0)[HEIGHT];
    List<Integer[]> answer = dp.get(0);

    /*
     * DP stores the max stack height with each disk at index i on bottom.
     * Initially, the DP array will have stacks with just disks.get(i) at the bottom
     * That means we can initialize the first one as just disks.get(0).
     * In this loop, try to put every other disk on top of the
     * best height stacks in the DP array.
     * 
     */
    for (int i = 1; i < disks.size(); i++) {
      Integer[] currentDisk = disks.get(i);
      int currentStackHeight = currentDisk[HEIGHT];
      dp.get(i).push(currentDisk);

      int depth = currentDisk[DEPTH];
      int height = currentDisk[HEIGHT];
      int width = currentDisk[WIDTH];

      for (int j = 0; j < i; j++) {
        Integer[] bottomDisk = disks.get(j);
        /*
         * We are trying to insert otherDisk on top of currentDisk.
         * If that succeeds, we must be able to put the whole stack stored in the DP
         * array on top of
         * currentDisk.
         * 
         * There are probably better (space) ways to do this other than cloning the
         * stack,
         * but this is much easier to debug.
         */
        if (bottomDisk[HEIGHT] < height && bottomDisk[DEPTH] < depth && bottomDisk[WIDTH] < width) {
          Stack<Integer[]> newStackWithBottomDisk = (Stack<Integer[]>) dp.get(j).clone();
          newStackWithBottomDisk.push(currentDisk);

          int tempStackHeight = getStackHeight(newStackWithBottomDisk);
          if (tempStackHeight > currentStackHeight) {
            dp.set(i, newStackWithBottomDisk);
            currentStackHeight = tempStackHeight;
          }
        }
      }

      if (currentStackHeight > maxHeight) {
        answer = dp.get(i);
        maxHeight = currentStackHeight;
      }
    }

    return answer;
  }

  private static int getStackHeight(Stack<Integer[]> stack) {
    int height = 0;
    for (Integer[] disk : new ArrayList<>(stack)) {
      height += disk[HEIGHT];
    }

    return height;
  }

  public static void main(String[] args) {
    List<Integer[]> output = diskStacking(Arrays.asList(new Integer[] { 2, 1, 2 }, new Integer[] { 3, 2, 3 },
        new Integer[] { 2, 2, 8 }, new Integer[] { 2, 3, 4 }, new Integer[] { 1, 3, 1 }, new Integer[] { 4, 4, 5 }));

    for (Integer[] disk : output) {
      System.out.println(Arrays.toString(disk));
    }
  }

}
