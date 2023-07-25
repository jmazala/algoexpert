# https://www.algoexpert.io/questions/max-profit-with-k-transactions

from typing import List


def maxProfitWithKTransactions(prices: List[int], k: int) -> int:
    if len(prices) < 2:
        return 0

    # Build a 2D array with # of transactions for rows, # of days for cols

    dp = [[0 for _ in prices] for _ in range(k + 1)]

    for t in range(1, k + 1):
        maxSoFar = float("-inf")

        for d in range(1, len(prices)):
            # take into account if we buy a stock on day d-1
            maxSoFar = max(maxSoFar, dp[t - 1][d - 1] - prices[d - 1])

            # decide to sell at this day, or keep previous max profit
            maxProfitFromDayBefore = dp[t][d - 1]
            maxProfitIfWeSellOnDay = maxSoFar + prices[d]
            dp[t][d] = max(maxProfitFromDayBefore, maxProfitIfWeSellOnDay)

    return dp[k][len(prices) - 1]


print(maxProfitWithKTransactions([5, 11, 3, 50, 60, 90], 2))  # 93
