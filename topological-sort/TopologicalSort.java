// https://www.algoexpert.io/questions/topological-sort

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

class TopologicalSort {
  public static List<Integer> topologicalSort(List<Integer> jobs, List<Integer[]> deps) {
    Map<Integer, Integer> degrees = new HashMap<>();
    Map<Integer, List<Integer>> prereqs = new HashMap<>();

    for (int job : jobs) {
      degrees.put(job, 0);
      prereqs.put(job, new ArrayList<>());
    }

    for (Integer[] dep : deps) {
      int prereq = dep[0];
      int job = dep[1];
      degrees.put(job, degrees.get(job) + 1);
      prereqs.get(prereq).add(job);
    }

    Queue<Integer> queue = new LinkedList<>();
    List<Integer> completed = new ArrayList<>();

    // Add all the jobs that can be started immediately to the queue
    for (Map.Entry<Integer, Integer> entry : degrees.entrySet()) {
      if (entry.getValue() == 0) {
        queue.add(entry.getKey());
      }
    }

    while (!queue.isEmpty()) {
      int job = queue.remove();
      completed.add(job);
      for (int next : prereqs.get(job)) {
        degrees.put(next, degrees.get(next) - 1);
        if (degrees.get(next) == 0) {
          queue.add(next);
        }
      }
    }

    // Check if we've completed all jobs
    return completed.size() == jobs.size() ? completed : new ArrayList<>();
  }

  public static void main(String[] args) {
    List<Integer> jobs = new ArrayList<>(Arrays.asList(new Integer[] { 1, 2, 3, 4 }));
    List<Integer[]> deps = new ArrayList<>(
        Arrays.asList(new Integer[][] { { 1, 2 }, { 1, 3 }, { 3, 2 }, { 4, 2 }, { 4, 3 } }));
    System.out.println(topologicalSort(jobs, deps));
  }
}
