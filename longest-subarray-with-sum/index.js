// https://www.algoexpert.io/questions/longest-subarray-with-sum

// #slidingwindow

/*
TIME: O(n)
SPACE: O(1)
*/
function longestSubarrayWithSum(array, targetSum) {
  let start = 0;
  let end = 0;
  let answer = [];
  let currentSum = 0;

  while (end < array.length) {
    currentSum += array[end];

    // shrink the sliding window if too large
    while (start < end && currentSum > targetSum) {
      currentSum -= array[start];
      start++;
    }

    // check for equality
    if (currentSum === targetSum) {
      if (!answer.length || (answer[1] - answer[0]) < (end - start)) {
        answer = [start, end];
      }
    }

    // extend window.  No need to look for 0's
    end++;
  }

  return answer;
}

/*
Solution 2 is same runtime as solution 1, but a little more verbose.
Solution 1 is more readable
*/
function longestSubarrayWithSum2(array, targetSum) {
  let start = 0;
  let end = 0;
  let currentSum = 0;
  let answer = [];

  while (end < array.length) {
    if (start > end) {
      end++;
      continue;
    }

    currentSum += array[end];

    if (currentSum < targetSum) {
      end++;
      continue;
    }

    if (currentSum > targetSum) {
      currentSum -= array[start];
      if (start !== end) {
        currentSum -= array[end];
      }

      start++;
      continue;
    }

    if (currentSum === targetSum) {
      while (end < array.length - 1 && array[end + 1] === 0) {
        end++;
      }

      if (!answer.length || (answer[1] - answer[0]) < (end - start)) {
        answer = [start, end];
      }

      currentSum -= array[start];
      if (start !== end) {
        currentSum -= array[end];
      }

      start++;
      continue;
    }
  }

  return answer;
}

// Do not edit the line below.
exports.longestSubarrayWithSum = longestSubarrayWithSum;

console.log(longestSubarrayWithSum([0, 0, 0, 0, 0, 1, 0, 0, 0, 0], 1)); //[0, 9]
console.log(longestSubarrayWithSum([1, 2, 3, 4, 3, 3, 1, 2, 1, 2], 10)); //[4, 8]
console.log(longestSubarrayWithSum([25, 25, 25, 25, 100, 0, 0, 0, 0, 0, 0, 0], 100)); // [4, 11]