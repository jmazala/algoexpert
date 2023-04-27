
// https://www.algoexpert.io/questions/minimum-waiting-time
import java.util.*;

public class MinimumWaitingTime {

  public static int minimumWaitingTime(int[] queries) {
    Arrays.sort(queries);
    int timeWaited = 0;

    for (int i = 0; i < queries.length; i++) {
      /*
       * we have to wait for this query to finish n times
       * where n is the number of queries left
       */
      timeWaited += queries[i] * (queries.length - i - 1);
    }

    return timeWaited;
  }

  public static void main(String[] args) {
    System.out.println(MinimumWaitingTime.minimumWaitingTime(new int[] { 3, 2, 1, 2, 6 })); // 17
    System.out.println(MinimumWaitingTime.minimumWaitingTime(new int[] { 5, 1, 4 })); // 11
  }
}

/*
 * 
 * 
 * playing with [5, 1, 4]
 * 
 * [1, 4, 5]
 * wait 0
 * 1 waits 0 minutes
 * 4 waits 1 minute
 * 5 waits 4 minutes and 1 minute
 * 
 * 0 + 1 + (4 + 1) = 6
 * 
 * playing with [3, 2, 1, 2, 6]
 * 
 * wait 0
 * start 3 = waited 0
 * start 2 = waited 0 + 3 = 3
 * start 1 = waited 3 + 2 = 5
 * start 2 = waited 5 + 1 = 6
 * start 6 = waited 6 + 2 = 8
 * total = 0 + 3 + 5 + 6 + 8 = 22
 * 
 * now sort it
 * [1, 2, 2, 3, 6]
 * wait 0
 * start 1 = wait (0) = 0
 * start 2 = wait (0 + 1) = 1
 * start 2 = wait (0 + 1 + 2) = 3
 * start 3 = wait (0 + 1 + 2 + 2) = 5
 * start 6 = wait (0 + 1 + 2 + 2 + 3) = 8
 * = 0 + 1 + 3 + 5 + 8 = 17
 * 
 * in other words
 */