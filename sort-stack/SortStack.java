// https://www.algoexpert.io/questions/sort-stack

import java.util.*;

class SortStack {
  /*
   * TIME: O(n^2) as we might need to pop the entire stack for every value
   * inserted
   * in worse case scenario (i.e. original ArrayList is in reverse sorted order)
   * SPACE: O(n) for the recursion frames
   */
  public static ArrayList<Integer> sortStack(ArrayList<Integer> stack) {
    if (stack.isEmpty()) {
      return stack;
    }

    int valToInsert = pop(stack);
    SortStack.sortStack(stack);
    SortStack.insertInOrder(stack, valToInsert);
    return stack;
  }

  private static void insertInOrder(ArrayList<Integer> stack, int value) {
    if (stack.isEmpty() || SortStack.peek(stack) <= value) {
      push(stack, value);
      return;
    }

    int next = SortStack.pop(stack);
    insertInOrder(stack, value);
    SortStack.push(stack, next);
  }

  private static int peek(ArrayList<Integer> stack) {
    if (stack.isEmpty()) {
      throw new EmptyStackException();
    }

    return stack.get(stack.size() - 1);
  }

  private static void push(ArrayList<Integer> stack, int value) {
    stack.add(value);
  }

  private static Integer pop(ArrayList<Integer> stack) {
    if (stack.isEmpty()) {
      throw new EmptyStackException();
    }

    return stack.remove(stack.size() - 1);
  }

  public static void main(String[] args) {
    ArrayList<Integer> stack = new ArrayList<Integer>(Arrays.asList(new Integer[] { -5, 2, -2, 4, 3, 1 }));
    System.out.println(SortStack.sortStack(stack).toString());
  }
}
