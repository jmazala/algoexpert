// https://www.algoexpert.io/questions/move-element-to-end

import java.util.*;

public class MoveElementToEnd {

  public static List<Integer> moveElementToEnd(List<Integer> array, int toMove) {
    int i = 0;
    int j = array.size() - 1;

    while (i < j) {
      if (array.get(j) == toMove) {
        j--;
        continue;
      }

      if (array.get(i) != toMove) {
        i++;
        continue;
      }

      swap(array, i, j);
      i++;
      j--;
    }

    return array;
  }

  private static void swap(List<Integer> array, int i, int j) {
    int temp = array.get(i);
    array.set(i, array.get(j));
    array.set(j, temp);
  }

  public static void main(String[] args) {
    System.out
        .println(moveElementToEnd(new ArrayList<Integer>(Arrays.asList(new Integer[] { 2, 1, 2, 2, 2, 3, 4, 2 })), 2)
            .toString());
  }
}
