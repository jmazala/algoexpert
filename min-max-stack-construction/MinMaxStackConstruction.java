// https://www.algoexpert.io/questions/min-max-stack-construction

import java.util.*;

// Feel free to add new properties and methods to the class.
class MinMaxStackConstruction {
  static class MinMaxItem {
    int min;
    int max;
    int value;

    public MinMaxItem(int min, int max, int value) {
      this.min = min;
      this.max = max;
      this.value = value;
    }
  }

  /*
   * This is kind of a dumbass problem.
   * Can accomplish this with 1 - 3 stacks
   * Each stack can store 1 or all of {value, min, max}
   */
  static class MinMaxStack {
    List<MinMaxItem> minMaxValues = new ArrayList<>();

    public int peek() {
      return this.minMaxValues.get(this.minMaxValues.size() - 1).value;
    }

    public int pop() {
      return this.minMaxValues.remove(this.minMaxValues.size() - 1).value;
    }

    public void push(Integer number) {
      if (this.minMaxValues.isEmpty()) {
        this.minMaxValues.add(new MinMaxItem(number, number, number));
      } else {
        MinMaxItem lastMinMax = this.minMaxValues.get(this.minMaxValues.size() - 1);
        this.minMaxValues
            .add(new MinMaxItem(Math.min(lastMinMax.min, number), Math.max(lastMinMax.max, number), number));
      }
    }

    public int getMin() {
      return this.minMaxValues.get(this.minMaxValues.size() - 1).min;
    }

    public int getMax() {
      return this.minMaxValues.get(this.minMaxValues.size() - 1).max;
    }
  }
}