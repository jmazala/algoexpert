//https://www.algoexpert.io/questions/Max%20Subset%20Sum%20No%20Adjacent
function maxSubsetSumNoAdjacent(array) {
	if (!array.length) {
		return 0;
	}
	
	if (array.length === 1) {
		return array[0];
  }
  
  if (array.length === 2) {
    return Math.max(...array);
  }
  
	const dp = Array(array.length);
	dp[0] = array[0]; //max at position 0 is array[0];
  dp[1] = Math.max(array[1], array[0]); //max at position 1 is array[0] or array[1]
	
	for (let i = 2; i < array.length; i++) {
		dp[i] = Math.max(dp[i-1], dp[i-2] + array[i]);
	}
	
	return dp[dp.length - 1];
}

// Do not edit the line below.
exports.maxSubsetSumNoAdjacent = maxSubsetSumNoAdjacent;

console.log(maxSubsetSumNoAdjacent([4, 3, 5, 200, 5, 3])); // 207
console.log(maxSubsetSumNoAdjacent([30, 25, 50, 55, 100, 120])); // 205