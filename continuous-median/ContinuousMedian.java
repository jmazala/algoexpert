// https://www.algoexpert.io/questions/continuous-median

import java.util.PriorityQueue;

// Do not edit the class below except for
// the insert method. Feel free to add new
// properties and methods to the class.
class ContinuousMedian {
  double median = 0;
  PriorityQueue<Integer> maxHeapLowerValues = new PriorityQueue<>((a, b) -> b - a);
  PriorityQueue<Integer> minHeapHigherValues = new PriorityQueue<>();

  public void insert(int number) {
    /*
     * any time you insert a number, pick a heap to place it in by comparing it
     * to max / min values of the heap
     */
    if (this.maxHeapLowerValues.isEmpty() && this.minHeapHigherValues.isEmpty()) {
      this.median = number;
      this.maxHeapLowerValues.add(number);
      return;
    }

    /*
     * If both heaps have equal sizes, determine where to insert the new number.
     * After inserting we'll have an odd number of values, so the median is the
     * middle element)
     */
    if (this.maxHeapLowerValues.size() == this.minHeapHigherValues.size()) {
      if (this.maxHeapLowerValues.peek() > number) {
        this.maxHeapLowerValues.add(number);
        this.median = this.maxHeapLowerValues.peek();
      } else {
        this.minHeapHigherValues.add(number);
        this.median = this.minHeapHigherValues.peek();
      }

      return;
    }

    /*
     * Otherwise we have an odd number of values, so put the newest number
     * in the appropriate heap. Then we'll have to rebalance the heaps
     * so they are equal sizes. Afterwards we'll have an even number of values
     * so the median is the average of both heap.peek
     */
    if (maxHeapLowerValues.peek() > number) {
      maxHeapLowerValues.add(number);
    } else {
      minHeapHigherValues.add(number);
    }

    /*
     * Check both heaps to see if 1 is larger than the other (it can only be by 2)
     */
    if (maxHeapLowerValues.size() > minHeapHigherValues.size()) {
      minHeapHigherValues.add(maxHeapLowerValues.remove());
    } else if (minHeapHigherValues.size() > maxHeapLowerValues.size()) {
      maxHeapLowerValues.add(minHeapHigherValues.remove());
    }

    // median is average of 2 middle elements
    median = (minHeapHigherValues.peek() + maxHeapLowerValues.peek()) / 2.0;
    return;
  }

  public double getMedian() {
    return median;
  }

  public static void main(String[] args) {
    ContinuousMedian cmh = new ContinuousMedian();
    cmh.insert(5);
    cmh.insert(10);
    System.out.println(cmh.getMedian()); // 7.5
    cmh.insert(100);
    System.out.println(cmh.getMedian()); // 10
  }
}
