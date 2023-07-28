// https://www.algoexpert.io/questions/dice-throws

public class DiceThrows {
  public static int diceThrows(int numDice, int numSides, int target) {
    // n throws of 1
    if (numDice == target) {
      return 1;
    }

    if (numDice > target) {
      return 0;
    }

    int[][] dp = new int[numDice + 1][target + 1];
    dp[0][0] = 1;

    /*
     * populate DP matrix with:
     * 1 way to get to every score...
     * 2 ways to get to every score...
     * ...(etc)
     */
    for (int rollNumber = 1; rollNumber <= numDice; rollNumber++) {
      for (int currentTarget = 0; currentTarget <= target; currentTarget++) {
        int numWays = 0;

        for (int rollValue = 1; rollValue <= Math.min(currentTarget, numSides); rollValue++) {
          /*
           * to get score s with n rolls with dice value(s) 1,2, ...v,
           * that would the sum of the # of ways to get to score s - v with n-1 rolls
           */
          numWays += dp[rollNumber - 1][currentTarget - rollValue];
        }

        dp[rollNumber][currentTarget] = numWays;
      }
    }

    return dp[numDice][target];
  }

  public static void main(String[] args) {
    System.out.println(diceThrows(5, 100, 4)); // 0
    System.out.println(diceThrows(12, 9, 12)); // 1
    System.out.println(diceThrows(2, 6, 7)); // 6
    System.out.println(diceThrows(3, 10, 12)); // 55
  }
}
