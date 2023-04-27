
// https://www.algoexpert.io/questions/class-photos
import java.util.*;

class ClassPhotos {
  /*
   * Back row is going to determined by the tallest overall student
   * Both arrays sorted ensures that we can find a match or not.
   */

  public static boolean classPhotos(
      ArrayList<Integer> redShirtHeights, ArrayList<Integer> blueShirtHeights) {
    Collections.sort(redShirtHeights, Collections.reverseOrder());
    Collections.sort(blueShirtHeights, Collections.reverseOrder());

    if (redShirtHeights.get(0) == blueShirtHeights.get(0)) {
      return false;
    }

    if (redShirtHeights.get(0) > blueShirtHeights.get(0)) {
      for (int i = 1; i < redShirtHeights.size(); i++) {
        if (redShirtHeights.get(i) <= blueShirtHeights.get(i)) {
          return false;
        }
      }
    } else {
      for (int i = 1; i < redShirtHeights.size(); i++) {
        if (redShirtHeights.get(i) >= blueShirtHeights.get(i)) {
          return false;
        }
      }
    }

    return true;
  }
}
