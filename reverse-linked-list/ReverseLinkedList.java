// https://www.algoexpert.io/questions/reverse-linked-list

import utils.algoexpert.LinkedList;

public class ReverseLinkedList {
  public static LinkedList reverseLinkedList(LinkedList head) {
    if (head == null || head.next == null) {
      return head;
    }

    LinkedList prev = null;
    LinkedList current = head;

    while (current != null) {
      LinkedList next = current.next;
      current.next = prev;
      prev = current;
      current = next;
    }

    return prev;
  }
}
