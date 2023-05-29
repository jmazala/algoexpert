// https://www.algoexpert.io/questions/number-of-ways-to-make-change

function numberOfWaysToMakeChange(n, denoms) {
  // no coin values, no ways to do it
  if (denoms.length === 0) {
    return 0;
  }

  // 1 coin value, must be a multiple
  if (denoms.length === 1) {
    return (n & denoms[0] === 0) ? 1 : 0;
  }

  // why even bother with too big of coins
  denoms = denoms.filter(coin => coin <= n).sort((a, b) => a - b);

  const dp = new Array(n + 1).fill(0);
  dp[0] = 1; // only 1 way to make 0

  for (const coin of denoms) {
    for (let amount = coin; amount <= n; amount++) {
      if (coin > amount) {
        continue;
      }

      dp[amount] += dp[amount - coin];
    }
  }

  return dp[n];
}

// Do not edit the line below.
exports.numberOfWaysToMakeChange = numberOfWaysToMakeChange;

console.log(numberOfWaysToMakeChange(6, [1, 5, 10])); // 2 (1x1+1x5, 6x1)