import java.util.*;

public class NonConstructibleChange {
  /*
   * OPTION 1 - use a set to keep track of all these combos
   * OPTION 2 - somehow keep track of the lowest you can find
   */
  /*
   * O(n log n) for sorting, O(n) for space for the set
   * 
   * First, sort the coins
   * Keep track of all the amounts you can make with your coins
   * Go coin by coin and add that to the amounts you can make
   * If you've exhausted all coins, that's the lowest one.
   * 
   */
  public static int nonConstructibleChange(int[] coins) {
    Arrays.sort(coins);
    int tryToMatch = 1;
    if (coins.length == 0 || tryToMatch < coins[0]) {
      return 1;
    }

    Set<Integer> canMake = new HashSet<>();
    int coinIndex = 0;

    while (tryToMatch < Integer.MAX_VALUE) {
      // if we still have more coins, add its value to each of the found sums
      if (coinIndex < coins.length) {
        Set<Integer> newSums = new HashSet<>();
        int coinValue = coins[coinIndex];
        for (int alreadyMade : canMake) {
          int currentSum = alreadyMade + coinValue;
          newSums.add(currentSum);
        }

        canMake.add(coinValue);
        canMake.addAll(newSums);
        coinIndex++;
      }

      if (!canMake.contains(tryToMatch)) {
        return tryToMatch;
      }

      tryToMatch++;
    }

    return -1; // ERROR
  }

  public static int nonConstructibleChange2(int[] coins) {
    Arrays.sort(coins);
    int canCreateUpTo = 0;

    for (int coin : coins) {
      // compare coin to what i can create up to
      if (coin > canCreateUpTo + 1) {
        return canCreateUpTo + 1;
      }

      canCreateUpTo += coin;
    }

    return canCreateUpTo + 1;
  }

  public static void main(String[] args) {
    System.out.println(NonConstructibleChange.nonConstructibleChange2(new int[] {})); // 1
    System.out.println(NonConstructibleChange.nonConstructibleChange2(new int[] { 1, 2, 5 })); // 4
    System.out.println(NonConstructibleChange.nonConstructibleChange2(new int[] { 5, 7, 1, 1, 2, 3, 22 })); // 20

    // [1, 1, 2, 3, 5, 7, 22]
  }
}
