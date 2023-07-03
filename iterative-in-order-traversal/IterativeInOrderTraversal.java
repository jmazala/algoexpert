import java.util.function.Function;

public class IterativeInOrderTraversal {

  /*
   * TIME: O(n) because we visit each node in the tree
   * SPACE: O(1) for 3 pointers
   * 
   * At first I wanted to use a stack here, but its kind of impossible
   * because you ned up visiting a node twice or looping infinitely
   * without the use of extra space for a Visited HashSet.
   * This is because you'd try a parent, look for their left child, then process
   * left child. On your way back up, you'd see that same left child you
   * previously
   * processed and try to process it again.
   * 
   * Using 3 pointers, you have enough decision making ability based on where
   * you are in the tree and what direction you're travelling in (up or down).
   * Since it's an in order traverse, the previous node tells you if you have
   * processed
   * the left or the right subtree
   * This version is more readable. Next version is shorter.
   */
  public static void iterativeInOrderTraversal(
      BinaryTree tree, Function<BinaryTree, Void> callback) {
    BinaryTree current = tree;
    BinaryTree previous = null;

    while (current != null) {
      BinaryTree next;

      // Travelling downwards
      if (previous == current.parent) {
        // Favor left child first
        if (current.left != null) {
          next = current.left;
        } else {
          callback.apply(current);
          next = current.right != null ? current.right : current.parent;
        }
      } else if (previous == current.left) {
        // travelling upwards, completed left subtree
        callback.apply(current);
        next = current.right != null ? current.right : current.parent;
      } else {
        // travelling upwards, completed right subtree
        next = current.parent;
      }

      previous = current;
      current = next;
    }
  }

  /*
   * Same runtime and space cmoplexity, I just combined 2 logic branches
   */
  public static void iterativeInOrderTraversal2(
      BinaryTree tree, Function<BinaryTree, Void> callback) {
    BinaryTree current = tree;
    BinaryTree previous = null;

    while (current != null) {
      BinaryTree next;

      if ((previous == current.parent && current.left == null) || previous == current.left) {
        callback.apply(current);
        next = current.right != null ? current.right : current.parent;
      } else if (previous == current.parent && current.left != null) {
        next = current.left;
      } else {
        next = current.parent;
      }

      previous = current;
      current = next;
    }
  }

  static class BinaryTree {
    public int value;
    public BinaryTree left;
    public BinaryTree right;
    public BinaryTree parent;

    public BinaryTree(int value) {
      this.value = value;
    }

    public BinaryTree(int value, BinaryTree parent) {
      this.value = value;
      this.parent = parent;
    }
  }

  public static void main(String[] args) {

  }
}
