public class ListNode {
  public Integer value;
  public ListNode next;

  public ListNode(int value) {
    this.value = value;
  }

  public int length() {
    ListNode temp = this;
    int length = 0;
    while (temp != null) {
      length++;
      temp = temp.next;
    }

    return length;
  }

  public ListNode lastNode() {
    ListNode temp = this;
    while (temp.next != null) {
      temp = temp.next;
    }

    return temp;
  }

  /**
   * makes a string human readable from the linked list.
   * 
   * @return String
   */
  public String toString() {
    StringBuilder builder = new StringBuilder();
    ListNode temp = this;

    while (temp != null) {
      builder.append(temp.value);
      temp = temp.next;
      if (temp != null) {
        builder.append(" -> ");
      }
    }

    return builder.toString();
  }
}