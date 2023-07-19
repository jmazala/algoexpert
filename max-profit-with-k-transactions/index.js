// https://www.algoexpert.io/questions/max-profit-with-k-transactions

function maxProfitWithKTransactions(prices, k) {
  if (prices.length < 2) {
    return 0;
  }

  /*
  Build 2D array with # of transactions for rows
  and # of days for columns
  */
  const dp = []
  for (let i = 0; i <= k; i++) {
    const row = [];

    for (let j = 0; j < prices.length; j++) {
      row.push(0);
    }

    dp.push(row);
  }

  for (let t = 1; t <= k; t++) {
    let maxMoneySoFar = -Infinity;

    for (let d = 1; d < prices.length; d++) {
      // take into account if we buy a stock on day d-1
      maxMoneySoFar = Math.max(maxMoneySoFar, dp[t - 1][d - 1] - prices[d - 1]);

      // decixe to sell at this day, or keep previous max profit
      const maxProfitFromDayBefore = dp[t][d - 1];
      const maxProfitIfWeSellOnDay = maxMoneySoFar + prices[d];
      dp[t][d] = Math.max(maxProfitFromDayBefore, maxProfitIfWeSellOnDay);
    }
  }

  return dp[k][prices.length - 1];
}

// Do not edit the line below.
exports.maxProfitWithKTransactions = maxProfitWithKTransactions;
