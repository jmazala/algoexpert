// https://www.algoexpert.io/questions/best-digits

import java.util.*;

class BestDigits {
  static class HeapItem {
    int index;
    char value;

    public HeapItem(int index, char value) {
      this.index = index;
      this.value = value;
    }
  }

  public static String bestDigits(String number, int numDigits) {
    Stack<Character> stack = new Stack<>();

    for (int i = 0; i < number.length(); i++) {
      char c = number.charAt(i);

      while (!stack.isEmpty() && numDigits > 0 && c > stack.peek()) {
        stack.pop();
        numDigits--;
      }

      stack.push(c);
    }

    while (numDigits > 0) {
      stack.pop();
      numDigits--;
    }

    StringBuilder builder = new StringBuilder();
    while (!stack.isEmpty()) {
      builder.insert(0, stack.pop());
    }

    return builder.toString();
  }

  public static void main(String[] args) {
    System.out.println(BestDigits.bestDigits("462839", 2)); // "6839"
    System.out.println(BestDigits.bestDigits("129847563", 4)); // "98763"
  }
}
