# https://www.algoexpert.io/questions/dice-throws

from typing import List

# # EVERY PERMUTATION WITH MEMOIZATION
# def diceThrows(numDice: int, numSides: int, target: int) -> int:
#     memo = [[None] * (target + 1) for _ in range(numDice + 1)]
#     return helper(numDice, numSides, target, memo)


# def helper(numDice: int, numSides: int, target: int, memo: List[List[int]]) -> int:
#     if memo[numDice][target] is not None:
#         return memo[numDice][target]

#     if numDice == 0:
#         return 1 if target == 0 else 0

#     ways = 0

#     for nextTarget in range(max(0, target - numSides), target):
#         ways += helper(numDice - 1, numSides, nextTarget, memo)

#     memo[numDice][target] = ways
#     return ways


# DP SOLUTION
# TIME: O(d * t * s ) where d is numDice, t is target, s is numSides
# SPACE: O(t * d)
def diceThrows(numDice: int, numSides: int, target: int) -> int:
    if numDice > target:
        return 0

    if numDice == target:
        return 1

    # rows is # of dice used, cols is target
    # so dp[i][j] = num ways to get to j with i rolls
    dp = [[0] * (target + 1) for _ in range(numDice + 1)]
    dp[0][0] = 1

    for rollNumber in range(1, numDice + 1):
        for currentTarget in range(target + 1):
            ways = 0

            for rollValue in range(1, min(currentTarget, numSides) + 1):
                ways += dp[rollNumber - 1][currentTarget - rollValue]

            dp[rollNumber][currentTarget] = ways

    return dp[numDice][target]


print(diceThrows(5, 100, 4))  # 0
print(diceThrows(12, 9, 12))  # 1
print(diceThrows(2, 6, 7))  # 6
print(diceThrows(3, 10, 12))  # 55
