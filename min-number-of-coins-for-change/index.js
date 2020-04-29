function minNumberOfCoinsForChange(n, denoms) {
  //looking for min number or something so populate with largest number
  const dp = Array(n + 1).fill(Infinity);
  dp[0] = 0;

  denoms.sort((a, b) => a - b);
  for (let i = 1; i <= n; i++) {
    for (let coin of denoms) {
      //coins are sorted so this optimizes a bit
      if (coin > i) {
        break;
      }

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