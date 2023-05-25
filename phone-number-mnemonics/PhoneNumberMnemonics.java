// https://www.algoexpert.io/questions/phone-number-mnemonics

import java.util.*;

class PhoneNumberMnemonics {

  private static Map<Character, List<Character>> MAP = new HashMap<Character, List<Character>>();

  static {
    MAP.put('0', Arrays.asList(new Character[] { '0' }));
    MAP.put('1', Arrays.asList(new Character[] { '1' }));
    MAP.put('2', Arrays.asList(new Character[] { 'a', 'b', 'c' }));
    MAP.put('3', Arrays.asList(new Character[] { 'd', 'e', 'f' }));
    MAP.put('4', Arrays.asList(new Character[] { 'g', 'h', 'i' }));
    MAP.put('5', Arrays.asList(new Character[] { 'j', 'k', 'l' }));
    MAP.put('6', Arrays.asList(new Character[] { 'm', 'n', 'o' }));
    MAP.put('7', Arrays.asList(new Character[] { 'p', 'q', 'r', 's' }));
    MAP.put('8', Arrays.asList(new Character[] { 't', 'u', 'v' }));
    MAP.put('9', Arrays.asList(new Character[] { 'w', 'x', 'y', 'z' }));
  }

  /*
   * METHOD 1 - Recursion
   */
  public static ArrayList<String> phoneNumberMnemonics(String phoneNumber) {
    ArrayList<String> result = new ArrayList<>();

    // int i = 0;
    // StringBuilder prefix = new StringBuilder();
    // PhoneNumberMnemonics.fillArray(i, prefix, phoneNumber, result);
    PhoneNumberMnemonics.fillArray2(phoneNumber, result);
    return result;
  }

  private static void fillArray(int i, StringBuilder prefix, String phoneNumber, ArrayList<String> result) {
    if (i == phoneNumber.length()) {
      result.add(prefix.toString());
      return;
    }

    char digit = phoneNumber.charAt(i);
    for (char c : MAP.get(digit)) {
      prefix.append(c);
      fillArray(i + 1, prefix, phoneNumber, result);
      prefix.deleteCharAt(prefix.length() - 1);
    }
  }

  /*
   * Method 2 - Stack
   */
  static class StackItem {
    public StringBuilder builder;
    public int index;

    public StackItem(StringBuilder builder, int index) {
      this.builder = builder;
      this.index = index;
    }
  }

  private static void fillArray2(String phoneNumber, ArrayList<String> result) {
    Stack<StackItem> stack = new Stack<>();

    stack.add(new StackItem(new StringBuilder(), 0));

    while (!stack.isEmpty()) {
      StackItem item = stack.pop();
      int index = item.index;
      StringBuilder builder = item.builder;

      if (index == phoneNumber.length()) {
        result.add(builder.toString());
        continue;
      }

      char digit = phoneNumber.charAt(index);
      for (char c : MAP.get(digit)) {
        StringBuilder copy = new StringBuilder(builder);
        copy.append(c);
        stack.push(new StackItem(copy, index + 1));
      }
    }
  }

  public static void main(String[] args) {
    for (String s : PhoneNumberMnemonics.phoneNumberMnemonics("1905")) {
      System.out.println(s);
    }
  }
}
