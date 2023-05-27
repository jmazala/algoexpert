// https://www.algoexpert.io/questions/reversePolishNotation

import java.util.*;

class ReversePolishNotation {
  public static int reversePolishNotation(String[] tokens) {
    Stack<String> stack = new Stack<>();

    for (int i = 0; i < tokens.length; i++) {
      String token = tokens[i];

      if (isOperand(token)) {
        String y = stack.pop();
        String x = stack.pop();
        int result = operate(x, y, token);
        stack.push(String.valueOf(result));
      } else {
        stack.push(token);
      }
    }

    return Integer.parseInt(stack.pop());
  }

  private static boolean isOperand(String token) {
    return token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/");
  }

  private static int operate(String x, String y, String operand) {
    int iX = Integer.parseInt(x);
    int iY = Integer.parseInt(y);
    if (operand.equals("+")) {
      return iX + iY;
    }

    if (operand.equals("-")) {
      return iX - iY;
    }

    if (operand.equals("*")) {
      return iX * iY;
    }

    return iX / iY;
  }

  public static void main(String[] args) {
    System.out.println(ReversePolishNotation.reversePolishNotation(new String[] { "2", "4", "+" })); // 6
    System.out.println(ReversePolishNotation.reversePolishNotation(new String[] { "18", "4", "-", "7", "/" })); // 2
    System.out
        .println(ReversePolishNotation.reversePolishNotation(new String[] { "50", "3", "17", "+", "2", "-", "/" })); // 2
  }
}
