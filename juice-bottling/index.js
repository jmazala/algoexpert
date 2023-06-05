// https://www.algoexpert.io/questions/juice-bottling

/*
prices[i] = price for i units of juice

Use a dp array to keep track of most revenue per # of units
Keep incrementing and store the makeup of most revenue

TIME: O(n) * O(n) = O(n^2)
SPACE: O(n)

See Java for recursive solution
*/
function juiceBottling(prices) {
  const n = prices.length;
  if (n === 0) {
    return 0;
  }

  const totalUnits = n - 1;

  const dp = new Array(n);
  for (let i = 0; i < n; i++) {
    dp[i] = { revenue: 0, makeup: new Array(0) };
  }

  for (let currentUnits = 0; currentUnits < dp.length; currentUnits++) {
    const currentRevenue = dp[currentUnits].revenue;
    const currentMakeup = dp[currentUnits].makeup;

    for (let additionalUnits = 1; additionalUnits < prices.length && (currentUnits + additionalUnits) <= totalUnits; additionalUnits++) {
      const numUnits = currentUnits + additionalUnits;
      const additionalRevenue = prices[additionalUnits];
      const totalRevenue = currentRevenue + additionalRevenue;

      if (totalRevenue > dp[numUnits].revenue) {
        currentMakeup.push(additionalUnits);
        dp[numUnits] = { revenue: totalRevenue, makeup: [...currentMakeup] };
        currentMakeup.pop();
      }
    }
  }


  return dp[totalUnits].makeup;
}

// Do not edit the line below.
exports.juiceBottling = juiceBottling;

// console.log(juiceBottling([0, 1])); // 1
console.log(juiceBottling([0, 1, 3, 2])); // 4
