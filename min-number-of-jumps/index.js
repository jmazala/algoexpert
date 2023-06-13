// https://www.algoexpert.io/questions/min-number-of-jumps

/*
TIME: O(n)
SPACE: O(1)
*/
function minNumberOfJumps(array) {
  if (array.length === 1) {
    return 0;
  }

  let jumps = 1;
  let furthest = array[0];
  let steps = array[0];

  for (let i = 1; i < array.length - 1; i++) {
    furthest = Math.max(furthest, i + array[i]);
    steps--;

    // When we run of steps we have to take a jump
    if (steps === 0) {
      jumps++;
      steps = furthest - i;
    }
  }

  return jumps;
}
/*
TIME:  O(n^2) worst case depending on the values at array[i]
SPACE:  O(n)
*/
function minNumberOfJumps2(array) {
  const dp = Array(array.length).fill(Infinity);
  dp[0] = 0;
  for (let i = 0; i < array.length; i++) {
    for (let j = 1; j <= array[i] && i + j < array.length; j++) {
      const nextI = i + j;
      dp[nextI] = Math.min(dp[nextI], dp[i] + 1);
    }
  }

  return dp[array.length - 1];
}

// Do not edit the line below.
exports.minNumberOfJumps = minNumberOfJumps;


console.log(minNumberOfJumps([1])); // 0
console.log(minNumberOfJumps([1, 1])); // 1
console.log(minNumberOfJumps([2, 1])); // 1
console.log(minNumberOfJumps([2, 1, 1])); // 1
console.log(minNumberOfJumps([1, 1, 1])); // 2
console.log(minNumberOfJumps([3, 1, 3, 1, 1, 1])); // 2
console.log(minNumberOfJumps([3, 1, 2, 1, 1, 1])); // 3
console.log(minNumberOfJumps([3, 4, 2, 1, 2, 3, 7, 1, 1, 1, 3])); // 4