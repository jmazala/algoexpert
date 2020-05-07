function maxSumIncreasingSubsequence(array) {
  //make arrays for longest subsequence ending at index i
  //as well as the actual subsequence
  const dp = Array(array.length);
  const subSequences = Array(array.length);
  
  dp[0] = array[0];
  subSequences[0] = [array[0]];
  
  let answerMax = array[0];
  let answerSubsequence = [array[0]];

  for (let i = 1; i < array.length; i++) {
    const currentNum = array[i];
    let max = currentNum;
    subSequences[i] = [currentNum];
    
    for (let j = 0; j < i; j++) {
      if (array[j] < currentNum) {
        if (dp[j] + currentNum > max) {
          max = dp[j] + currentNum;
          subSequences[i] = subSequences[j].concat([currentNum]);
        }

        if (max > answerMax) {
          answerMax = max;
          answerSubsequence = subSequences[i];
        }
      }
    }

    dp[i] = max;
  }

  return [answerMax, answerSubsequence];
}

// Do not edit the line below.
exports.maxSumIncreasingSubsequence = maxSumIncreasingSubsequence;

console.log(JSON.stringify(maxSumIncreasingSubsequence([8, 12, 2, 3, 15, 5, 7])));
console.log(JSON.stringify(maxSumIncreasingSubsequence([10, 70, 20, 30, 50, 11, 30])));
