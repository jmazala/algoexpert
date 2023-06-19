// https://www.algoexpert.io/questions/reverse-linked-list

public class ReverseLinkedList {
  public static ListNode reverseLinkedList(ListNode head) {
    if (head == null || head.next == null) {
      return head;
    }

    ListNode prev = null;
    ListNode current = head;

    while (current != null) {
      ListNode next = current.next;
      current.next = prev;
      prev = current;
      current = next;
    }

    return prev;
  }
}
