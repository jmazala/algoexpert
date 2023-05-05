import java.util.*;

class Semordnilap {
  public static ArrayList<ArrayList<String>> semordnilap(String[] words) {
    Set<String> wordSet = new HashSet<>(Arrays.asList(words));
    ArrayList<ArrayList<String>> result = new ArrayList<>();

    for (Iterator<String> i = wordSet.iterator(); i.hasNext();) {
      String word = i.next();
      i.remove();
      String reverseWord = Semordnilap.reverseString(word);
      if (wordSet.contains(reverseWord)) {
        result.add(new ArrayList<>(Arrays.asList(new String[] { word, reverseWord })));
      }
    }

    return result;
  }

  private static String reverseString(String string) {
    StringBuilder builder = new StringBuilder(string);
    builder.reverse();
    return builder.toString();
  }

  public static void main(String[] args) {
    System.out.println(Semordnilap.semordnilap(new String[] {})); // []
    System.out.println(Semordnilap.semordnilap(new String[] { "aaa" })); // []
    System.out.println(Semordnilap.semordnilap(new String[] { "dog", "god" })); // [['dog', 'god']]
  }
}
