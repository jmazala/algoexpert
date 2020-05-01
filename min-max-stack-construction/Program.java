import java.util.*;

// Feel free to add new properties and methods to the class.
class Program {
  static class MinMaxStack {
    List<Integer> values = new ArrayList<>();
    List<Map<String, Integer>> minMaxStack = new ArrayList<>();

    public int peek() {
      return values.get(values.size() - 1);
    }

    public int pop() {
      minMaxStack.remove(minMaxStack.size() - 1);
      return values.remove(values.size() - 1);

    }

    public void push(Integer number) {
      Map<String, Integer> newEntry = new HashMap<>();
      newEntry.put("min", number);
      newEntry.put("max", number);

      if (!minMaxStack.isEmpty()) {
        Map<String, Integer> lastEntry = new HashMap<>(minMaxStack.get(minMaxStack.size() - 1));
        newEntry.replace("min", Math.min(number, lastEntry.get("min")));
        newEntry.replace("max", Math.max(number, lastEntry.get("max")));
      }

      minMaxStack.add(newEntry);
      values.add(number);
    }

    public int getMin() {
      return minMaxStack.get(minMaxStack.size() - 1).get("min");

    }

    public int getMax() {
      return minMaxStack.get(minMaxStack.size() - 1).get("max");
    }
  }
}