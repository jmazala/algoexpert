// https://www.algoexpert.io/questions/repair-bst

import java.util.*;

class RepairBST {
  // This is an input class. Do not edit.
  static class BST {
    public int value;
    public BST left = null;
    public BST right = null;

    public BST(int value) {
      this.value = value;
    }
  }

  private BST node1 = null;
  private BST node2 = null;
  private BST previousNode = null;

  public BST repairBst(BST tree) {
    this.inOrderTraverse(tree);
    int temp = node1.value;
    node1.value = node2.value;
    node2.value = temp;
    return tree;
  }

  private void inOrderTraverse(BST tree) {
    if (tree == null) {
      return;
    }

    inOrderTraverse(tree.left);

    if (this.previousNode != null && this.previousNode.value > tree.value) {
      if (this.node1 == null) {
        this.node1 = this.previousNode;
      }

      this.node2 = tree;
    }

    this.previousNode = tree;
    inOrderTraverse(tree.right);
  }

  public static void main(String[] args) {
    RepairBST repair = new RepairBST();
    // BST two = new BST(2);
    // BST one = new BST(1);
    // two.right = one;

    // repair.repairBst(two);
    // System.out.println(two.value); // 1

    // one = new BST(1);
    // two = new BST(2);
    // BST three = new BST(3);
    // two.left = three;
    // two.right = one;
    // repair.repairBst(two);
    // System.out.println(two.value); // 2
    // System.out.println(two.left.value); // 1
    // System.out.println(two.right.value); // 3

    BST a = new BST(2);
    BST b = new BST(3);
    BST c = new BST(7);
    BST d = new BST(12);
    BST e = new BST(10);
    BST f = new BST(8);
    BST g = new BST(14);
    BST h = new BST(20);
    BST i = new BST(22);

    e.left = c;
    e.right = h;
    e.left.left = b;
    e.left.right = d;
    e.left.left.left = a;
    e.right.left = f;
    e.right.left.right = g;
    e.right.right = i;
    repair.repairBst(e);
    System.out.println(d.value); // 8
    System.out.println(f.value); // 12

  }
}
