import java.util.*;

class Program {
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
    List<Integer> answer = new ArrayList<>();

    for (Map.Entry<Integer, Integer> entry : degrees.entrySet()) {
      if (entry.getValue() == 0) {
        queue.add(entry.getKey());
      }
    }

    while (!queue.isEmpty()) {
      int job = queue.remove();
      answer.add(job);
      for (int next : prereqs.get(job)) {
        degrees.put(next, degrees.get(next) - 1);
        if (degrees.get(next) == 0) {
          queue.add(next);
        }
      }
    }

    return answer.size() == jobs.size() ? answer : new ArrayList<>();
  }

  public static void main(String[] args) {
    List<Integer> jobs = new ArrayList<>();
    jobs.add(1);
    jobs.add(2);
    jobs.add(3);
    jobs.add(4);
    List<Integer[]> deps = new ArrayList<>();
    deps.add(new Integer[] { 1, 2 });
    deps.add(new Integer[] { 1, 3 });
    deps.add(new Integer[] { 3, 2 });
    deps.add(new Integer[] { 4, 2 });
    deps.add(new Integer[] { 4, 3 });
    System.out.println(Program.topologicalSort(jobs, deps));
  }
}
