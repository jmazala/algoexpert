class MergeLinkedLists {
  // This is an input class. Do not edit.
  public static class LinkedList {
    int value;
    LinkedList next;

    LinkedList(int value) {
      this.value = value;
      this.next = null;
    }
  }

  public static LinkedList mergeLinkedLists(LinkedList headOne, LinkedList headTwo) {
    LinkedList prev = null;
    LinkedList p1 = headOne;
    LinkedList p2 = headTwo;

    while (p1 != null && p2 != null) {
      if (p1.value < p2.value) {
        prev = p1;
        p1 = p1.next;
        continue;
      }

      if (prev != null) {
        prev.next = p2;
      }

      prev = p2;
      p2 = p2.next;
      prev.next = p1;
    }

    if (p1 == null) {
      prev.next = p2;
    }

    return headOne.value < headTwo.value ? headOne : headTwo;
  }
}
