// https://www.algoexpert.io/questions/one-edit

class OneEdit {

  /*
   * Can do this recursively, with a stack, or with pointers
   * Implemented recursively below (oneEdit) and pointers (oneEdit2).
   * 
   * Pointers is better. Recursively is a little too literal and involves string
   * operations
   * 
   * They are both O(n) but recursively uses StringBuilders and takes up more
   * space
   */

  public static boolean oneEdit(String stringOne, String stringTwo) {
    if (stringTwo.length() < stringOne.length()) {
      String temp = new String(stringOne);
      stringOne = stringTwo;
      stringTwo = temp;
    }

    return helper(stringOne, stringTwo, 0, 0);
  }

  private static boolean helper(String stringOne, String stringTwo, int numEdits, int i) {
    int lengthDifference = Math.abs(stringOne.length() - stringTwo.length());

    if (numEdits == 0 && lengthDifference > 1 || numEdits == 1 && lengthDifference > 0) {
      return false;
    }

    for (; i < stringOne.length(); i++) {
      if (stringOne.charAt(i) == stringTwo.charAt(i)) {
        continue;
      }

      if (numEdits == 1) {
        return false;
      }

      StringBuilder builder = new StringBuilder(stringOne);

      // REPLACE
      if (lengthDifference == 0) {
        builder.deleteCharAt(i);
        builder.insert(i, stringTwo.charAt(i));
        return helper(builder.toString(), stringTwo, 1, i);
      }

      // ADD
      if (stringOne.length() < stringTwo.length()) {
        builder.insert(i, stringTwo.charAt(i));
        return helper(builder.toString(), stringTwo, 1, i);
      }

      // REMOVE
      builder.deleteCharAt(i);
      return helper(builder.toString(), stringTwo, 1, i);
    }

    return true;
  }

  // JUST USE POINTERS!
  public boolean oneEdit2(String stringOne, String stringTwo) {
    int lengthOne = stringOne.length();
    int lengthTwo = stringTwo.length();

    if (Math.abs(lengthOne - lengthTwo) > 1) {
      return false;
    }

    boolean edited = false;

    int iOne = 0;
    int iTwo = 0;

    while (iOne < lengthOne && iTwo < lengthTwo) {
      if (stringOne.charAt(iOne) == stringTwo.charAt(iTwo)) {
        iOne++;
        iTwo++;
        continue;
      }

      if (edited) {
        return false;
      }

      edited = true;

      // "REPLACE"
      if (lengthOne == lengthTwo) {
        iOne++;
        iTwo++;
        continue;
      }

      // "REMOVE"
      if (lengthOne > lengthTwo) {
        iOne++;
        continue;
      }

      // "ADD"
      iTwo++;
    }

    return true;
  }

  public static void main(String[] args) {
    System.out.println(OneEdit.oneEdit("helle", "hollo")); // false
    System.out.println(OneEdit.oneEdit("hello", "hellloo")); // false
    System.out.println(OneEdit.oneEdit("hello", "heo")); // false
    System.out.println(OneEdit.oneEdit("hello", "hemmo")); // false
    System.out.println(OneEdit.oneEdit("hello", "helloo")); // true
    System.out.println(OneEdit.oneEdit("hello", "helo")); // true
    System.out.println(OneEdit.oneEdit("hello", "hollo")); // true
  }
}
