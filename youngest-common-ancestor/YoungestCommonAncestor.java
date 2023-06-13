// https://www.algoexpert.io/questions/youngest-common-ancestor

public class YoungestCommonAncestor {

  static class AncestralTree {
    public char name;
    public AncestralTree ancestor;

    AncestralTree(char name) {
      this.name = name;
      this.ancestor = null;
    }

    // This method is for testing only.
    void addAsAncestor(AncestralTree[] descendants) {
      for (AncestralTree descendant : descendants) {
        descendant.ancestor = this;
      }
    }
  }

  /*
   * This is similar to the find merge point of two linked lists problem
   * Find the depth of each node, move the deeper node up by
   * (depthDeep - depthShallow) places
   * 
   * Then traverse up the tree until the nodes meet.
   * 
   * TIME: O(h) for depth where h is the height of the tree
   * SPACE: O(1) for depth ints and pointers
   */
  public static AncestralTree getYoungestCommonAncestor(
      AncestralTree topAncestor, AncestralTree descendantOne, AncestralTree descendantTwo) {
    int depthOne = 0;

    AncestralTree current = descendantOne;
    while (current != null) {
      current = current.ancestor;
      depthOne++;
    }

    int depthTwo = 0;
    current = descendantTwo;
    while (current != null) {
      current = current.ancestor;
      depthTwo++;
    }

    while (depthOne > depthTwo) {
      descendantOne = descendantOne.ancestor;
      depthOne--;
    }

    while (depthTwo > depthOne) {
      descendantTwo = descendantTwo.ancestor;
      depthTwo--;
    }

    while (descendantOne != descendantTwo) {
      descendantOne = descendantOne.ancestor;
      descendantTwo = descendantTwo.ancestor;
    }

    return descendantOne;
  }

  public static void main(String[] args) {
    AncestralTree a = new AncestralTree('A');
    AncestralTree b = new AncestralTree('B');
    AncestralTree c = new AncestralTree('C');
    AncestralTree d = new AncestralTree('D');
    AncestralTree e = new AncestralTree('E');
    AncestralTree f = new AncestralTree('F');
    AncestralTree g = new AncestralTree('G');
    AncestralTree h = new AncestralTree('H');
    AncestralTree i = new AncestralTree('I');

    b.ancestor = a;
    System.out.println(getYoungestCommonAncestor(a, b, a).name); // A

    i.ancestor = d;
    h.ancestor = d;
    d.ancestor = b;
    e.ancestor = b;
    c.ancestor = a;
    f.ancestor = c;
    g.ancestor = c;

    System.out.println(getYoungestCommonAncestor(a, e, i).name); // B
    System.out.println(getYoungestCommonAncestor(a, h, f).name); // A
    System.out.println(getYoungestCommonAncestor(a, g, f).name); // C
    System.out.println(getYoungestCommonAncestor(a, h, i).name); // D
  }
}
