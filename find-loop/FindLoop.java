// https://www.algoexpert.io/questions/find-loop

import java.util.HashSet;
import java.util.Set;

class FindLoop {
  /*
   * O(n) time O(1) space.
   * The distance between the start of the linked list and the beginning of the
   * loop
   * is the same as the first intersection point and the start of the loop.
   * This is called Floyd's algorithm, and you can see more either in the
   * video explanation of the algoexpert problem, or at
   * https://youtu.be/wjYnzkAhcNk
   */
  public static LinkedList findLoop(LinkedList head) {
    /*
     * if we start them both at head our while loop won't work
     * we could either add a first iteration flag, or just advance them
     * at declaration space
     */
    LinkedList slow = head.next;
    LinkedList fast = head.next.next;

    while (slow != fast) {
      slow = slow.next;
      fast = fast.next.next;
    }

    slow = head;

    while (slow != fast) {
      fast = fast.next;
      slow = slow.next;
    }

    return slow;
  }

  /*
   * O(n) time O(n) space. Use a set to keep track of nodes you've seen
   * OR you could also add a seen flag inside the LinkedList class
   * for this problem. Either way you're using O(n) space.
   */
  public static LinkedList findLoop2(LinkedList head) {
    Set<LinkedList> set = new HashSet<>();
    while (true) {
      if (set.contains(head)) {
        return head;
      }

      set.add(head);
      head = head.next;
    }
  }

  static class LinkedList {
    int value;
    LinkedList next = null;

    public LinkedList(int value) {
      this.value = value;
    }
  }
}
