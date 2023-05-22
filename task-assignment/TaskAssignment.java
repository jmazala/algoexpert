// https://www.algoexpert.io/questions/task-assignment

import java.util.*;

class TaskAssignment {

  static class Task {
    int index;
    int duration;

    public Task(int index, int duration) {
      this.index = index;
      this.duration = duration;
    }
  }

  static class SortByDuration implements Comparator<Task> {
    @Override
    public int compare(Task a, Task b) {
      return a.duration - b.duration;
    }
  }

  /*
   * METHOD 1 - SORTING
   * TIME: O(n log n) to sort
   * SPACE: O(n) for sorted array and output
   * 
   * Also example of a comparator
   */
  public static ArrayList<ArrayList<Integer>> taskAssignment(int k, ArrayList<Integer> tasks) {
    List<Task> sortedTasks = new ArrayList<>(tasks.size());
    for (int i = 0; i < tasks.size(); i++) {
      sortedTasks.add(new Task(i, tasks.get(i)));
    }

    Collections.sort(sortedTasks, new SortByDuration());

    ArrayList<ArrayList<Integer>> output = new ArrayList<>(k);
    for (int i = 0; i < (tasks.size() / 2); i++) {
      ArrayList<Integer> result = new ArrayList<>(2);
      Task lightTask = sortedTasks.get(i);
      Task heavyTask = sortedTasks.get(tasks.size() - 1 - i);
      result.add(lightTask.index);
      result.add(heavyTask.index);
      output.add(result);
    }

    return output;
  }

  /*
   * METHOD 2 - TWO HEAPS
   * TIME: Heap insertion is between O(1) avg case and O(log n) worst case
   * which is between O(n) and O(n log n) where n is number of tasks
   * SPACE: O(t) for heaps and O(t) for output = O(t)
   */
  public static ArrayList<ArrayList<Integer>> taskAssignment2(int k, ArrayList<Integer> tasks) {
    PriorityQueue<Task> maxHeap = new PriorityQueue<>((a, b) -> a.duration - b.duration);
    PriorityQueue<Task> minHeap = new PriorityQueue<>((a, b) -> b.duration - a.duration);

    for (int i = 0; i < tasks.size(); i++) {
      Task task = new Task(i, tasks.get(i));
      minHeap.add(task);
      maxHeap.add(task);

      if (minHeap.size() > tasks.size() / 2) {
        minHeap.remove();
        maxHeap.remove();
      }
    }

    ArrayList<ArrayList<Integer>> output = new ArrayList<>(k);
    for (int i = 0; i < k; i++) {
      ArrayList<Integer> result = new ArrayList<>(2);
      Task lightTask = minHeap.remove();
      Task heavyTask = maxHeap.remove();
      result.add(lightTask.index);
      result.add(heavyTask.index);
      output.add(result);
    }

    return output;
  }

  public static void main(String[] args) {
    ArrayList<Integer> tasks = new ArrayList<>(Arrays.asList(new Integer[] { 1, 3, 5, 3, 1, 4 }));
    ArrayList<ArrayList<Integer>> output = TaskAssignment.taskAssignment2(3, tasks);

    for (ArrayList<Integer> assignment : output) {
      System.out.println(assignment.toString());
    }
  }
}
