// https://www.algoexpert.io/questions/min-heap-construction

import java.util.*;

// Do not edit the class below except for the buildHeap,
// siftDown, siftUp, peek, remove, and insert methods.
// Feel free to add new properties and methods to the class.
class MinHeapConstruction {

  static class MinHeap {
    List<Integer> heap = new ArrayList<Integer>();

    public MinHeap(List<Integer> array) {
      heap = buildHeap(array);
    }

    /*
     * TIME: O(n)
     * SPACE: O(1)
     */
    public List<Integer> buildHeap(List<Integer> array) {
      /*
       * use sift down method here because it is
       * more efficient than sift up or insert
       */
      int firstParentIdx = getParentIndex(array.size() - 1);

      for (int currentIdx = firstParentIdx; currentIdx >= 0; currentIdx--) {
        siftDown(currentIdx, array.size() - 1, array);
      }

      return array;
    }

    /*
     * TIME: O(log n)
     * SPACE: O(1)
     */
    private static void siftDown(int currentIdx, int endIdx, List<Integer> heap) {
      // compare to both childNodes
      int leftChildIdx = getLeftChildIndex(currentIdx);

      // don't sift down on a node at the bottom of the heap
      while (leftChildIdx <= endIdx) {
        int idxToSwap = leftChildIdx;

        /*
         * if we have a right child node and its value is the smaller child,
         * then we'd want to potentially swap with the right child
         * otherwise either there is no right child, or the right child is the larger
         * one
         */
        int rightChildIdx = getRightChildIndex(currentIdx);

        if (rightChildIdx <= endIdx && heap.get(rightChildIdx) < heap.get(leftChildIdx)) {
          idxToSwap = rightChildIdx;
        }

        if (heap.get(idxToSwap) < heap.get(currentIdx)) {
          swap(currentIdx, idxToSwap, heap);
          currentIdx = idxToSwap;
          leftChildIdx = getLeftChildIndex(currentIdx);
        } else {
          break;
        }
      }
    }

    /*
     * TIME: O(log n)
     * SPACE: O(1)
     */
    public static void siftUp(int currentIdx, List<Integer> heap) {
      int parentIdx = getParentIndex(currentIdx);

      // can't sift up from top of heap
      while (currentIdx > 0 && heap.get(currentIdx) < heap.get(parentIdx)) {
        swap(parentIdx, currentIdx, heap);
        currentIdx = parentIdx;
        parentIdx = getParentIndex(currentIdx);
      }
    }

    /*
     * TIME: O(1)
     * SPACE: O(1)
     */
    public int peek() {
      return this.heap.get(0);
    }

    /*
     * TIME: O(log n)
     * SPACE: O(1)
     */
    public int remove() {
      swapHeadWithLastElement(this.heap);
      int valueToRemove = this.heap.remove(this.heap.size() - 1);
      siftDown(0, this.heap.size() - 1, this.heap);
      return valueToRemove;
    }

    /*
     * TIME: O(log n)
     * SPACE: O(1)
     */
    public void insert(int value) {
      this.heap.add(value);
      int index = this.heap.size() - 1;
      siftUp(index, this.heap);
    }

    private static int getParentIndex(int i) {
      return (i - 1) / 2;
    }

    private static int getRightChildIndex(int i) {
      return 2 + i * 2;
    }

    private static int getLeftChildIndex(int i) {
      return 1 + i * 2;
    }

    private static void swapHeadWithLastElement(List<Integer> heap) {
      swap(0, heap.size() - 1, heap);
    }

    private static void swap(int i, int j, List<Integer> heap) {
      int temp = heap.get(i);
      heap.set(i, heap.get(j));
      heap.set(j, temp);
    }

    public static void main(String[] args) {
      MinHeap heap = new MinHeap(
          new ArrayList<Integer>(Arrays.asList(new Integer[] { 48, 12, 24, 7, 8, -5, 24, 391, 24, 56, 2, 6, 8, 41 })));
      heap.insert(76);
      System.out.println(heap.peek()); // -5
      System.out.println(heap.remove()); // -5
      System.out.println(heap.peek()); // 2
      System.out.println(heap.remove()); // 2
      System.out.println(heap.peek()); // 6
      heap.insert(87);
      System.out.println(heap.heap.toString()); // [6, 7, 8, 24, 8, 24, 24, 391, 76, 56, 12, 41, 48, 87]
    }
  }
}
