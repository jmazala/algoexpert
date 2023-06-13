// https://www.algoexpert.io/questions/validate-three-nodes

class ValidateThreeNodes {
  // This is an input class. Do not edit.
  static class BST {
    public int value;
    public BST left = null;
    public BST right = null;

    public BST(int value) {
      this.value = value;
    }
  }

  public boolean validateThreeNodes(BST nodeOne, BST nodeTwo, BST nodeThree) {
    return (isAncestor(nodeOne, nodeTwo, nodeThree) && isDescendant(nodeThree, nodeTwo, nodeOne)) ||
        (isAncestor(nodeThree, nodeTwo, nodeOne) && isDescendant(nodeOne, nodeTwo, nodeThree));
  }

  /*
   * is nodeOne a descendant of nodeTwo?
   * NOTE: this serves the problem itself by having nodeThree
   * as an argument. That's a case where we can stop searching
   */
  private boolean isDescendant(BST nodeOne, BST nodeTwo, BST nodeThree) {
    if (nodeTwo == null) {
      return false;
    }

    if (nodeTwo == nodeOne) {
      return true;
    }

    if (nodeTwo == nodeThree) {
      return false;
    }

    if (nodeOne.value < nodeTwo.value) {
      return isDescendant(nodeOne, nodeTwo.left, nodeThree);
    }

    return isDescendant(nodeOne, nodeTwo.right, nodeThree);
  }

  private boolean isAncestor(BST nodeOne, BST nodeTwo, BST nodeThree) {
    return isDescendant(nodeTwo, nodeOne, nodeThree);
  }
}
