// https://www.algoexpert.io/questions/next-greater-element

import java.util.*;

/*
 * Brute force.  O(n^2)
 */
class NextGreaterElement {

  public static int[] nextGreaterElement(int[] array) {
    int[] output = new int[array.length];
    Arrays.fill(output, -1);

    for (int i = 0; i < array.length; i++) {
      int currentVal = array[i];
      int nextGreaterElement = -1;
      for (int j = 1; j < array.length; j++) {
        int nextIndex = (i + j) % array.length;
        int nextVal = array[nextIndex];

        if (nextVal > currentVal) {
          nextGreaterElement = nextVal;
          break;
        }
      }

      output[i] = nextGreaterElement;
    }

    return output;
  }

  /*
   * Stack, left to right, store indices
   */
  public static int[] nextGreaterElement2(int[] array) {
    int[] output = new int[array.length];
    Arrays.fill(output, -1);

    Stack<Integer> stack = new Stack<>();
    stack.push(0);

    for (int i = 1; i < 2 * array.length; i++) {
      int idx = i % array.length;

      int curVal = array[idx];

      while (!stack.isEmpty() && curVal > array[stack.peek()]) {
        int poppedIdx = stack.pop();
        output[poppedIdx] = curVal;
      }

      stack.push(idx);
    }

    return output;
  }

  /*
   * Stack, right to left, use it to figure out what next greater element is
   */
  public static int[] nextGreaterElement3(int[] array) {
    int[] output = new int[array.length];
    Arrays.fill(output, -1);

    Stack<Integer> stack = new Stack<>();

    for (int i = 2 * array.length - 1; i >= 0; i--) {
      int idx = i % array.length;

      int curVal = array[idx];

      while (!stack.isEmpty()) {
        /*
         * Remove from stack until we find a greater value or the stack is empty
         */
        if (stack.peek() <= curVal) {
          stack.pop();
        } else {
          output[idx] = stack.peek();
          break;
        }
      }

      stack.push(curVal);
    }

    return output;
  }

  public static void main(String[] args) {
    System.out.println(Arrays.toString(NextGreaterElement.nextGreaterElement(new int[] { 2, 5, -3, -4, 6, 7, 2 }))); // [5,6,6,6,7,-1,5]
    System.out.println(Arrays.toString(NextGreaterElement.nextGreaterElement2(new int[] { 2, 5, -3, -4, 6, 7, 2 }))); // [5,6,6,6,7,-1,5]
    System.out.println(Arrays.toString(NextGreaterElement.nextGreaterElement3(new int[] { 2, 5, -3, -4, 6, 7, 2 }))); // [5,6,6,6,7,-1,5]
    System.out.println(Arrays.toString(NextGreaterElement.nextGreaterElement(new int[] { 7, 6, 5, 4, 3, 2, 1 }))); // [-1,7,7,7,7,7,7]
    System.out.println(Arrays.toString(NextGreaterElement.nextGreaterElement2(new int[] { 7, 6, 5, 4, 3, 2, 1 }))); // [-1,7,7,7,7,7,7]
    System.out.println(Arrays.toString(NextGreaterElement.nextGreaterElement3(new int[] { 7, 6, 5, 4, 3, 2, 1 }))); // [-1,7,7,7,7,7,7]
  }
}
