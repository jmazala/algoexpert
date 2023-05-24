// https://www.algoexpert.io/questions/sum-of-linked-lists

public class SumOfLinkedLists {
  // This is an input class. Do not edit.
  public static class LinkedList {
    public int value;
    public LinkedList next;

    public LinkedList(int value) {
      this.value = value;
      this.next = null;
    }
  }

  public LinkedList sumOfLinkedLists(LinkedList linkedListOne, LinkedList linkedListTwo) {
    boolean carry = false;
    LinkedList head = new LinkedList(0);
    LinkedList node = head;

    while (true) {
      int sum = carry ? 1 : 0;
      carry = false;

      if (linkedListOne != null) {
        sum += linkedListOne.value;
        linkedListOne = linkedListOne.next;
      }

      if (linkedListTwo != null) {
        sum += linkedListTwo.value;
        linkedListTwo = linkedListTwo.next;
      }

      if (sum > 9) {
        carry = true;
        sum -= 10;
      }

      node.value = sum;

      if (carry || linkedListOne != null || linkedListTwo != null) {
        node.next = new LinkedList(0);
        node = node.next;
      } else {
        break;
      }
    }

    return head;
  }
}
