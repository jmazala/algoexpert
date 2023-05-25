// https://www.algoexpert.io/questions/merging-linked-lists

import java.util.*;

class Program {
  // This is an input class. Do not edit.
  public static class LinkedList {
    public int value;
    public LinkedList next;

    public LinkedList(int value) {
      this.value = value;
      this.next = null;
    }
  }

  /*
   * METHOD 1 - use a set (thanks java)
   * n = length LL1, m = length LL2
   * TIME: O(n + m)
   * SPACE: O(n)
   */
  public LinkedList mergingLinkedLists(LinkedList linkedListOne, LinkedList linkedListTwo) {
    Set<LinkedList> set = new HashSet<LinkedList>();
    while (linkedListOne != null) {
      set.add(linkedListOne);
      linkedListOne = linkedListOne.next;
    }

    while (linkedListTwo != null) {
      if (set.contains(linkedListTwo)) {
        return linkedListTwo;
      }

      linkedListTwo = linkedListTwo.next;
    }

    return null;
  }

  /*
   * METHOD 2 - use pointers
   * Grab the length of both lists
   * Since they eventually merge, we can catch up the pointer of the longer list
   * to the appropriate spot
   * TIME: O(n + m)
   * SPACE: O(1)
   */
  public LinkedList mergingLinkedLists2(LinkedList linkedListOne, LinkedList linkedListTwo) {
    int lengthOne = 0;
    int lengthTwo = 0;

    LinkedList temp = linkedListOne;
    while (temp != null) {
      lengthOne++;
      temp = temp.next;
    }

    temp = linkedListTwo;
    while (temp != null) {
      lengthTwo++;
      temp = temp.next;
    }

    /*
     * L1: 5 -> 6 -> 7 -> 8 -> 9
     * L2: 1 -> 2 -> 3 -> 4 -> 6 -> 7 -> 8 -> 9
     * 
     * L1 length is 5
     * L2 length is 8
     * 
     * If we move up pointer 2 by 3
     * 
     * L1 (5) -> 6
     * L2 (4) -> 6
     */

    LinkedList p1 = linkedListOne;
    LinkedList p2 = linkedListTwo;

    while (lengthOne > lengthTwo) {
      p1 = p1.next;
      lengthOne--;
    }

    while (lengthTwo > lengthOne) {
      p2 = p2.next;
      lengthTwo--;
    }

    while (p1 != null && p2 != null) {
      if (p1 == p2) {
        return p1;
      }

      p1 = p1.next;
      p2 = p2.next;
    }

    return null;
  }
}
