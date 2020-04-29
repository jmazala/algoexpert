
//https://www.algoexpert.io/questions/BST%20Traversal
import java.util.*;

class Program {

  // left, node, right
  public static List<Integer> inOrderTraverse(BST tree, List<Integer> array) {
    if (tree == null) {
      return null;
    }

    inOrderTraverse(tree.left, array);
    array.add(tree.value);
    inOrderTraverse(tree.right, array);
    return array;
  }

  // node, left, right
  public static List<Integer> preOrderTraverse(BST tree, List<Integer> array) {
    if (tree == null) {
      return null;
    }

    array.add(tree.value);
    preOrderTraverse(tree.left, array);
    preOrderTraverse(tree.right, array);
    return array;
  }

  // left, right, node
  public static List<Integer> postOrderTraverse(BST tree, List<Integer> array) {
    if (tree == null) {
      return null;
    }

    postOrderTraverse(tree.left, array);
    postOrderTraverse(tree.right, array);
    array.add(tree.value);
    return array;
  }

  static class BST {
    public int value;
    public BST left;
    public BST right;

    public BST(int value) {
      this.value = value;
    }
  }

  public static void main(String[] args) {
    BST tree = new BST(10);
    tree.left = new BST(5);
    tree.left.left = new BST(2);
    tree.left.left.left = new BST(1);
    tree.left.right = new BST(5);
    tree.right = new BST(15);
    tree.right.right = new BST(22);

    System.out.println(Program.inOrderTraverse(tree, new LinkedList<>())); // [1, 2, 5, 5, 10, 15, 22]
    System.out.println(Program.preOrderTraverse(tree, new LinkedList<>())); // [10, 5, 2, 1, 5, 15, 22]
    System.out.println(Program.postOrderTraverse(tree, new LinkedList<>())); // [1, 2, 5, 5, 22, 15, 10]
  }
}
