import java.util.*;

class FindKthLargestValueInBST {
  // This is an input class. Do not edit.
  static class BST {
    public int value;
    public BST left = null;
    public BST right = null;

    public BST(int value) {
      this.value = value;
    }
  }

  // O(n) time to traverse tree, O(k) space for the heap / recursion
  public static int findKthLargestValueInBst(BST tree, int k) {
    // insert all into a heap of a given size
    PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>();
    FindKthLargestValueInBST.populateMaxHeap(minHeap, tree, k);
    return minHeap.peek();
  }

  public static void populateMaxHeap(PriorityQueue<Integer> heap, BST tree, int k) {
    if (tree.left != null) {
      FindKthLargestValueInBST.populateMaxHeap(heap, tree.left, k);
    }

    if (heap.size() == k) {
      heap.remove();
    }

    heap.add(tree.value);

    if (tree.right != null) {
      FindKthLargestValueInBST.populateMaxHeap(heap, tree.right, k);
    }
  }

  /*
   * METHOD 2 - we're using a binary search tree.
   * We can traverse the tree in reverse and return the kth visited node
   * still using a heap. This is O(n) time and O(k) space.
   * Slightly better on space/time in not worst case scenarios since we travese
   * backwards
   * As soon as we hit k iterations we are done
   */
  public static int findKthLargestValueInBst2(BST tree, int k) {
    PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>();
    FindKthLargestValueInBST.populateMaxHeap2(tree, minHeap, k);
    return minHeap.peek();
  }

  public static void populateMaxHeap2(BST tree, PriorityQueue<Integer> heap, int k) {
    if (tree == null) {
      return;
    }

    populateMaxHeap2(tree.right, heap, k);

    if (heap.size() == k) {
      return;
    }

    heap.add(tree.value);
    populateMaxHeap2(tree.left, heap, k);
  }

  /*
   * Method 3
   * Traverse the tree backwards and use an object or variables to keep
   * track of some information as you traverse
   */

  private static class HelperInfo {
    public int numNodesVisited;
    public int lastNodeValue;

    public HelperInfo(int numNodesVisited, int lastNodeValue) {
      this.numNodesVisited = numNodesVisited;
      this.lastNodeValue = lastNodeValue;
    }
  }

  public static int findKthLargestValueInBst3(BST tree, int k) {
    HelperInfo h = new HelperInfo(0, -1);
    helper(tree, k, h);
    return h.lastNodeValue;
  }

  private static void helper(BST tree, int k, HelperInfo h) {
    if (tree == null || h.numNodesVisited == k) {
      return;
    }

    FindKthLargestValueInBST.helper(tree.right, k, h);

    if (h.numNodesVisited == k) {
      return;
    }

    h.numNodesVisited++;
    h.lastNodeValue = tree.value;
    FindKthLargestValueInBST.helper(tree.left, k, h);
  }

  public static void main(String[] args) {
    BST one = new BST(1);
    BST three = new BST(3);
    BST two = new BST(2);
    two.left = one;
    two.right = three;

    BST five = new BST(5);
    five.left = two;
    BST five2 = new BST(5);
    five.right = five2;

    BST seventeen = new BST(17);
    BST twentyTwo = new BST(22);

    BST twenty = new BST(20);
    twenty.left = seventeen;
    twenty.right = twentyTwo;

    BST fifteen = new BST(15);
    fifteen.left = five;
    fifteen.right = twenty;

    System.out.println(FindKthLargestValueInBST.findKthLargestValueInBst(fifteen, 3)); // 17
    System.out.println(FindKthLargestValueInBST.findKthLargestValueInBst2(fifteen, 3)); // 17
    System.out.println(FindKthLargestValueInBST.findKthLargestValueInBst3(fifteen, 3)); // 17
  }
}

// [1,2,3,4,5,6,7,8,9,10]