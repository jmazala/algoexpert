// https://www.algoexpert.io/questions/shift-linked-list

import utils.algoexpert.LinkedList;

public class ShiftLinkedList {
  public static LinkedList shiftLinkedList(LinkedList head, int k) {
    if (head == null || head.next == null) {
      return head;
    }

    /*
     * Can simplify this problem by converting a left (negative) shift
     * into a positive shift.
     */
    int length = head.length();
    LinkedList lastNode = head.lastNode();

    k %= length;

    if (k == 0) {
      return head;
    }

    if (k < 0) {
      k = length + k;
    }

    LinkedList kthLastNode = head;
    LinkedList beforeKthLastNode = head;

    /*
     * Find kth to last element, this is new head,
     * (aka value to return).
     */
    for (int i = 0; i < length - k; i++) {
      kthLastNode = kthLastNode.next;
      if (i == 0) {
        continue;
      }

      beforeKthLastNode = beforeKthLastNode.next;
    }

    /*
     * remove link from k-1 to k and add the
     * wrap around for the shift
     */
    beforeKthLastNode.next = null;
    lastNode.next = head;
    return kthLastNode;
  }

  public static void main(String[] args) {
    LinkedList head = new LinkedList(0);
    head.next = new LinkedList(1);
    head.next.next = new LinkedList(2);
    head.next.next.next = new LinkedList(3);
    head.next.next.next.next = new LinkedList(4);
    head.next.next.next.next.next = new LinkedList(5);
    System.out.println(shiftLinkedList(head, 2).toString()); // 4 -> 5 -> 0 -> 1 -> 2 -> 3
  }
}
