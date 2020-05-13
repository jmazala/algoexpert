// Feel free to add new properties and methods to the class.
class Program {
  static class DoublyLinkedList {
    public Node head;
    public Node tail;

    public void setHead(Node node) {
      if (head != null) {
        insertBefore(head, node);
        return;
      }

      head = node;
      tail = node;
    }

    public void setTail(Node node) {
      if (tail != null) {
        insertAfter(tail, node);
        return;
      }

      setHead(node);
    }

    public void insertBefore(Node node, Node nodeToInsert) {
      if (nodeToInsert == head && nodeToInsert == tail) {
        return;
      }

      remove(nodeToInsert);
      nodeToInsert.prev = node.prev;
      nodeToInsert.next = node;

      if (node.prev == null) {
        head = nodeToInsert;
      } else {
        node.prev.next = nodeToInsert;
      }

      node.prev = nodeToInsert;
    }

    public void insertAfter(Node node, Node nodeToInsert) {
      if (nodeToInsert == head && nodeToInsert == tail) {
        return;
      }

      remove(nodeToInsert);

      nodeToInsert.prev = node;
      nodeToInsert.next = node.next;

      if (node.next == null) {
        tail = nodeToInsert;
      } else {
        node.next.prev = nodeToInsert;
      }

      node.next = nodeToInsert;
    }

    public void insertAtPosition(int position, Node nodeToInsert) {
      if (position == 1) {
        setHead(nodeToInsert);
        return;
      }

      Node temp = head;
      while (temp != null && position > 1) {
        temp = temp.next;
        position--;
      }

      if (temp != null) {
        insertBefore(temp, nodeToInsert);
        return;
      }

      setTail(nodeToInsert);
    }

    public void removeNodesWithValue(int value) {
      Node current = head;
      while (current != null) {
        if (current.value != value) {
          current = current.next;
          continue;
        }

        Node temp = current;
        current = current.next;
        remove(temp);
      }
    }

    public void remove(Node node) {
      if (node == head) {
        head = head.next;
      }

      if (node == tail) {
        tail = tail.prev;
      }

      removeNodeBindings(node);
    }

    public boolean containsNodeWithValue(int value) {
      Node temp = head;
      while (temp != null) {
        if (temp.value == value) {
          return true;
        }

        temp = temp.next;
      }

      return false;
    }

    public void removeNodeBindings(Node node) {
      if (node.prev != null) {
        node.prev.next = node.next;
      }

      if (node.next != null) {
        node.next.prev = node.prev;
      }

      node.prev = null;
      node.next = null;
    }
  }

  // Do not edit the class below.
  static class Node {
    public int value;
    public Node prev;
    public Node next;

    public Node(int value) {
      this.value = value;
    }
  }
}
