// https://www.algoexpert.io/questions/balanced-brackets

import java.util.*;

class BalancedBrackets {
  final static Map<Character, Character> MATCHING = new HashMap<>();
  static {
    MATCHING.put(')', '(');
    MATCHING.put('}', '{');
    MATCHING.put(']', '[');
  }

  public static boolean balancedBrackets(String str) {
    Stack<Character> stack = new Stack<>();
    for (char c : str.toCharArray()) {
      switch (c) {
        case '(':
        case '{':
        case '[':
          stack.push(c);
          break;
        case ')':
        case '}':
        case ']':
          if (stack.isEmpty() || stack.pop() != MATCHING.get(c)) {
            return false;
          }

          break;
      }
    }

    return stack.isEmpty();
  }

  public static void main(String[] args) {
    System.out.println(BalancedBrackets.balancedBrackets("([])(){}(())()()"));
  }
}