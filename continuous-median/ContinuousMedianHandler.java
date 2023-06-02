import java.util.*;

// Do not edit the class below except for
// the insert method. Feel free to add new
// properties and methods to the class.
class ContinuousMedianHandler {
  double median = 0;
  PriorityQueue<Integer> lowerMaxHeap = new PriorityQueue<>((a, b) -> b - a);
  PriorityQueue<Integer> upperMinHeap = new PriorityQueue<>();

  public void insert(int number) {
    // any time you insert a number, pick a heap to place it in by comparing it
    // to max / min values of the heap
    if (lowerMaxHeap.isEmpty() && upperMinHeap.isEmpty()) {
      median = number;
      lowerMaxHeap.add(number);
      return;
    }

    // after inserting we have an odd number, so the median is the middle element)
    if (lowerMaxHeap.size() == upperMinHeap.size()) {
      if (lowerMaxHeap.peek() > number) {
        lowerMaxHeap.add(number);
        median = lowerMaxHeap.peek();
      } else {
        upperMinHeap.add(number);
        median = upperMinHeap.peek();
      }

      return;
    }

    // otherwise after inserting we'll have an even number
    if (lowerMaxHeap.peek() > number) {
      lowerMaxHeap.add(number);
    } else {
      upperMinHeap.add(number);
    }

    // must rebalance first
    if (lowerMaxHeap.size() > upperMinHeap.size()) {
      upperMinHeap.add(lowerMaxHeap.remove());
    } else if (upperMinHeap.size() > lowerMaxHeap.size()) {
      lowerMaxHeap.add(upperMinHeap.remove());
    }

    // median is average of 2 middle elements
    median = (upperMinHeap.peek() + lowerMaxHeap.peek()) / 2.0;
    return;
  }

  public double getMedian() {
    return median;
  }

  public static void main(String[] args) {
    ContinuousMedianHandler cmh = new ContinuousMedianHandler();
    cmh.insert(5);
    cmh.insert(10);
    System.out.println(cmh.getMedian()); // 7.5
    cmh.insert(100);
    System.out.println(cmh.getMedian()); // 10
  }
}
