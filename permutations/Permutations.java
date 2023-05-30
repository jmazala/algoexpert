import java.util.*;

class Permutations {
  // ITERATIVELY
  // public static List<List<Integer>> getPermutations(List<Integer> array) {
  // List<List<Integer>> permutations = new ArrayList<>();
  // getPermutations(0, array, permutations);
  // return permutations;
  // }

  // public static void getPermutations(int i, List<Integer> array,
  // List<List<Integer>> permutations) {
  // if (i == array.size() - 1) {
  // permutations.add(new ArrayList<Integer>(array));
  // return;
  // }

  // for (int j = i; j < array.size(); j++) {
  // swap(array, i, j);
  // getPermutations(i + 1, array, permutations);
  // swap(array, i, j);
  // }
  // }

  // public static void swap(List<Integer> array, int i, int j) {
  // int temp = array.get(i);
  // array.set(i, array.get(j));
  // array.set(j, temp);
  // }

  // RECURSIVELY
  public static List<List<Integer>> getPermutations(List<Integer> array) {
    List<List<Integer>> answer = new LinkedList<>();
    if (array.size() == 0) {
      return answer;
    }

    permutations(array, new ArrayList<>(), answer);
    return answer;
  }

  private static void permutations(List<Integer> remaining, List<Integer> taken, List<List<Integer>> answer) {
    if (remaining.isEmpty()) {
      answer.add(taken);
      return;
    }

    for (int i = 0; i < remaining.size(); i++) {
      List<Integer> next = new ArrayList<Integer>(taken);
      next.add(remaining.get(i));
      List<Integer> remainingAfterRemoval = new ArrayList<>(remaining);
      remainingAfterRemoval.remove(i);
      permutations(remainingAfterRemoval, next, answer);
    }
  }

  public static void main(String[] args) {
    System.out.println(getPermutations(new LinkedList<>()));
    List<Integer> list = new LinkedList<>();
    list.add(1);
    list.add(2);
    list.add(3);
    System.out.println(getPermutations(list));
  }
}
