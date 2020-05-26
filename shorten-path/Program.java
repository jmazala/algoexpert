import java.util.*;
import java.util.stream.Collectors;

class Program {
  public static String shortenPath(String path) {
    String[] tokens = path.split("/");
    List<String> filteredTokens = Arrays.asList(tokens).stream().filter((a) -> a.length() > 0 && !a.equals("."))
        .collect(Collectors.toList());

    Stack<String> stack = new Stack<>();
    boolean isAbsolutePath = path.charAt(0) == '/';

    if (isAbsolutePath) {
      stack.push("");
    }

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
    System.out.println(Program.shortenPath("/foo/../test/../test/../foo//bar/./baz"));
  }
}
