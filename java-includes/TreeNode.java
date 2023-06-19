import java.util.Arrays;

public class TreeNode {
  int val;
  TreeNode left;
  TreeNode right;

  public TreeNode(int val) {
    this.val = val;
  }

  /**
   * takes a array of ints in preorder and returns a binary tree.
   * 
   * @param preorder array of numbers in preorder
   * @return TreeNode
   */
  public static TreeNode bstFromPreorder(int[] preorder) {
    if (preorder == null || preorder.length == 0) {
      return null;
    }

    TreeNode root = new TreeNode(preorder[0]);
    if (preorder.length == 1) {
      return root;
    }

    int rightTreeIndex = 1;

    while (rightTreeIndex < preorder.length && preorder[rightTreeIndex] < root.val) {
      rightTreeIndex++;
    }

    root.left = bstFromPreorder(Arrays.copyOfRange(preorder, 1, rightTreeIndex));
    root.right = bstFromPreorder(Arrays.copyOfRange(preorder, rightTreeIndex, preorder.length));
    return root;
  }
}