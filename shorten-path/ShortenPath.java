// https://www.algoexpert.io/questions/shorten-path

import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

class ShortenPath {
  public static String shortenPath(String path) {
    String[] tokens = path.split("/");
    /*
     * Take away '.', '' (occurs with //)
     */
    List<String> filteredTokens = Arrays.asList(tokens).stream().filter((a) -> a.length() > 0 && !a.equals("."))
        .collect(Collectors.toList());

    Stack<String> stack = new Stack<>();
    boolean isAbsolutePath = path.charAt(0) == '/';

    if (isAbsolutePath) {
      stack.push("");
    }

    /*
     * Case:
     * '..' pop off stack if there is anything to pop off. otherwise append
     * else just add to stack
     */
    for (String token : filteredTokens) {
      if (!token.equals("..")) {
        stack.push(token);
        continue;
      }

      if (stack.isEmpty() || stack.peek().equals("..")) {
        stack.push(token);
        continue;
      }

      if (!stack.peek().equals("")) {
        stack.pop();
      }
    }

    if (stack.size() == 1 && stack.peek().equals("")) {
      return "/";
    }

    StringBuilder builder = new StringBuilder();

    while (!stack.isEmpty()) {
      builder.append(stack.remove(0));
      if (!stack.isEmpty()) {
        builder.append("/");
      }
    }

    return builder.toString();
  }

  public static void main(String[] args) {
    System.out.println(shortenPath("/foo/../test/../test/../foo//bar/./baz"));
  }
}
