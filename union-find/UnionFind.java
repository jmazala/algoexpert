import java.util.*;

// Do not edit the class below except for
// the constructor and the createSet, find,
// and union methods. Feel free to add new
// properties and methods to the class.
class UnionFind {
  // Write your code here.
  private HashMap<Integer, Integer> valuesToParent = new HashMap<>();
  private HashMap<Integer, Set<Integer>> parentsToValues = new HashMap<>();

  public void createSet(int value) {
    this.valuesToParent.put(value, value);
    Set<Integer> values = new HashSet<>();
    values.add(value);
    this.parentsToValues.put(value, values);
  }

  public Integer find(int value) {
    return this.valuesToParent.get(value);
  }

  public void union(int valueOne, int valueTwo) {
    if (!this.valuesToParent.containsKey(valueOne) || !this.valuesToParent.containsKey(valueTwo)) {
      return;
    }

    int newParent = this.valuesToParent.get(valueOne);
    int oldParent = this.valuesToParent.get(valueTwo);

    if (oldParent == newParent) {
      return;
    }

    Set<Integer> valuesToMove = this.parentsToValues.get(oldParent);
    for (int valueToMove : valuesToMove) {
      this.valuesToParent.put(valueToMove, newParent);
      this.parentsToValues.get(newParent).add(valueToMove);
    }

    this.parentsToValues.remove(oldParent);
  }

  public static void main(String[] args) {
    UnionFind u = new UnionFind();
    u.createSet(0); // [0]
    u.createSet(2); // [0], [2]
    u.union(0, 2); // {0}:[0, 2]
    u.createSet(3); // [0,2], [3]
    u.createSet(1);// [0,2], [3], [1]
    u.union(1, 3); // {0}: [0,2], {1}: [1,3]
    System.out.println(u.find(0)); // 0
    System.out.println(u.find(1)); // 1
    System.out.println(u.find(2)); // 0
    System.out.println(u.find(3)); // 1
    u.union(3, 0); // {1}: [0,2,1,3]
    System.out.println(u.find(0)); // 1
    System.out.println(u.find(1)); // 1
    System.out.println(u.find(2)); // 1
    System.out.println(u.find(3)); // 1
  }
}
