// https://www.algoexpert.io/questions/validate-bst

public class ValidateBST {
  static class BST {
    public int value;
    public BST left;
    public BST right;

    public BST(int value) {
      this.value = value;
    }
  }

  public static boolean validateBst(BST tree) {
    return helper(tree, Integer.MIN_VALUE, Integer.MAX_VALUE);
  }

  private static boolean helper(BST tree, int lowerBound, int upperBound) {
    if (tree == null) {
      return true;
    }

    if (tree.value < lowerBound || tree.value > upperBound) {
      return false;
    }

    if (!helper(tree.left, lowerBound, tree.value - 1)) {
      return false;
    }

    if (!helper(tree.right, tree.value, upperBound)) {
      return false;
    }

    return true;
  }

  public static void main(String[] args) {
    BST ten = new BST(10);
    System.out.println(validateBst(ten)); // true
    BST eighty = new BST(80);
    ten.left = eighty;
    System.out.println(validateBst(ten)); // false
    ten.left = null;
    BST five = new BST(5);
    ten.right = five;
    System.out.println(validateBst(ten)); // false
    ten.right = null;
    ten.left = five;
    System.out.println(validateBst(ten)); // true
    BST two = new BST(2);
    five.left = two;
    System.out.println(validateBst(ten)); // true
    BST five2 = new BST(5);
    five.right = five2;
    System.out.println(validateBst(ten)); // true
    BST one = new BST(1);
    two.left = one;
    System.out.println(validateBst(ten)); // true
    BST fifteen = new BST(15);
    ten.right = fifteen;
    System.out.println(validateBst(ten)); // true
    BST thirteen = new BST(13);
    fifteen.left = thirteen;
    System.out.println(validateBst(ten)); // true
    BST fourteen = new BST(14);
    thirteen.right = fourteen;
    System.out.println(validateBst(ten)); // true
    BST twentyTwo = new BST(22);
    fifteen.right = twentyTwo;
    System.out.println(validateBst(ten)); // true
    BST seventeen = new BST(17);
    twentyTwo.right = seventeen;
    System.out.println(validateBst(ten)); // false
    twentyTwo.right = null;
    twentyTwo.left = seventeen;
    System.out.println(validateBst(ten)); // true
  }
}
