// https://www.algoexpert.io/questions/remove-kth-node-from-end

public class RemoveKthNodeFromEnd {
  public static void removeKthNodeFromEnd(LinkedList head, int k) {
    // 2 pointers. move 1 head k elements
    // iterate both to find kth element from end
    // update pointers on that node
    LinkedList first = head;
    LinkedList second = head;

    // remove 3rd from end (3)
    // 1 -> 2 -> 3 -> 4 -> 5
    // first = 1, second = 4
    // first = 2, second = 5
    // first = 3, second = null

    for (int i = 0; i < k; i++) {
      second = second.next;
    }

    // case where list head is kth from the end
    if (second == null) {
      head.value = head.next.value;
      head.next = head.next.next;
      return;
    }

    /*
     * we use second.next
     * because we need our first pointer to be just
     * before the element to remove.
     * This is because we're in a singly linked list
     * and we don't have a prev pointer
     */
    while (second.next != null) {
      first = first.next;
      second = second.next;
    }

    first.next = first.next.next;
  }

  static class LinkedList {
    int value;
    LinkedList next = null;

    public LinkedList(int value) {
      this.value = value;
    }

    public String toString() {
      StringBuilder builder = new StringBuilder();
      LinkedList node = this;
      while (node != null) {
        builder.append(String.valueOf(node.value));
        builder.append(" -> ");
        node = node.next;
      }

      return builder.substring(0, builder.length() - 3);
    }
  }

  public static void main(String[] args) {
    LinkedList ll = new LinkedList(1);
    ll.next = new LinkedList(2);
    ll.next.next = new LinkedList(3);
    ll.next.next.next = new LinkedList(4);
    ll.next.next.next.next = new LinkedList(5);

    removeKthNodeFromEnd(ll, 3);
    System.out.println(ll.toString()); // 1 -> 2 -> 4 -> 5

    removeKthNodeFromEnd(ll, 4);
    System.out.println(ll.toString()); // 2 -> 4 -> 5

    removeKthNodeFromEnd(ll, 1);
    System.out.println(ll.toString()); // 2 -> 4
  }
}
