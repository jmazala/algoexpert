// https://www.algoexpert.io/questions/breadth-first-search

import java.util.*;

public class BreadthFirstSearch {
  // Do not edit the class below except
  // for the breadthFirstSearch method.
  // Feel free to add new properties
  // and methods to the class.
  static class Node {
    String name;
    List<Node> children = new ArrayList<Node>();

    public Node(String name) {
      this.name = name;
    }

    public List<String> breadthFirstSearch(List<String> array) {
      Queue<Node> queue = new LinkedList<>();
      queue.add(this);

      while (!queue.isEmpty()) {
        int queueSize = queue.size();

        for (int i = 0; i < queueSize; i++) {
          Node node = queue.remove();
          array.add(node.name);

          for (Node next : node.children) {
            queue.add(next);
          }
        }
      }

      return array;
    }

    public Node addChild(String name) {
      Node child = new Node(name);
      children.add(child);
      return this;
    }
  }
}
