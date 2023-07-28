# https://www.algoexpert.io/questions/min-number-of-coins-for-change

from typing import List


# TIME: O(nc) where c is # of coinrs
# O(n) to make dp array
# O(n) for value loop
#   O(c) for inner loop
# SPACE: O(n) for DP array
def minNumberOfCoinsForChange(n: int, denoms: List[int]) -> int:
    dp = [float("inf")] * (n + 1)
    dp[0] = 0

    for value in range(1, n + 1):
        # You could sort coins here to break early.
        # In certain cases, this O(c log c) operation would have a better runtime
        # For instance, a ton of denoms that are bigger than n would be looped through every time
        for coin in denoms:
            dp[value] = min(
                dp[value], dp[value - coin] + 1 if (value - coin) >= 0 else float("inf")
            )

    return dp[n] if dp[n] != float("inf") else -1


print(minNumberOfCoinsForChange(3, [2]))  # -1
print(minNumberOfCoinsForChange(14, [1, 5, 10]))  # 5
print(minNumberOfCoinsForChange(7, [1, 5, 10]))  # 3
print(minNumberOfCoinsForChange(99, [1, 5, 10, 25]))  # 9
