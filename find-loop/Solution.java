import java.util.*;

class Program {
  // O(n) time O(1) space
  public static LinkedList findLoop(LinkedList head) {
    //if we start them both at head our while loop won't work
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

  // O(n) time O(n) space
  // public static LinkedList findLoop(LinkedList head) {
  // Set<LinkedList> set = new HashSet<>();
  // while (true) {
  // if (set.contains(head)) {
  // return head;
  // }

  // set.add(head);
  // head = head.next;
  // }
  // }

  static class LinkedList {
    int value;
    LinkedList next = null;

    public LinkedList(int value) {
      this.value = value;
    }
  }
}
