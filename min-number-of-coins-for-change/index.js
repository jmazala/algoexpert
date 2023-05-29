// https://www.algoexpert.io/questions/min-number-of-coins-for-change 

function minNumberOfCoinsForChange(n, denoms) {
  const dp = Array(n + 1).fill(Infinity);
  dp[0] = 0;

  // why bother with coins too big?
  denoms = denoms.filter(coin => coin <= n);

  denoms.sort((a, b) => a - b);
  for (let i = 1; i <= n; i++) {
    for (let coin of denoms.filter(coin => coin <= i)) {
      const missing = i - coin;

      //we're going to cross the same point a max of len(denoms) times
      dp[i] = Math.min(dp[i], dp[missing] + 1);
    }
  }

  return dp[n] === Infinity ? -1 : dp[n];
}

// Do not edit the line below.
exports.minNumberOfCoinsForChange = minNumberOfCoinsForChange;

console.log(minNumberOfCoinsForChange(7, [1, 5, 10])); //3
console.log(minNumberOfCoinsForChange(135, [39, 45, 130, 40, 4, 1, 60, 75])); //2