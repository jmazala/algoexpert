// https://www.algoexpert.io/questions/zero-sum-subarray
/*
Brute force.  O(n^2)
*/
function zeroSumSubarray(nums) {
  for (let i = 0; i < nums.length; i++) {
    if (nums[i] === 0) {
      return true;
    }

    let sum = nums[i];
    for (let j = i + 1; j < nums.length; j++) {
      sum += nums[j];
      if (sum === 0) {
        return true;
      }
    }
  }

  return false;
}

/*
O(n) time, O(n) space

Use a set to keep track of the running sum.
If a running sum is already in the set, that means that
there is a subarray with sum 0.
*/

function zeroSumArray(nums) {
  const sumSet = new Set();
  let runningSum = 0;
  for (let i = 0; i < nums.length; i++) {
    if (nums[i] === 0) {
      return true;
    }

    runningSum += nums[i];
    if (sumSet.has(runningSum) || runningSum === 0) {
      return true;
    }

    sumSet.add(runningSum);
  }

  return false;
}

// Do not edit the line below.
exports.zeroSumSubarray = zeroSumSubarray;

console.log(zeroSumSubarray([])); // false
console.log(zeroSumSubarray([1])); // false
console.log(zeroSumSubarray([0])); // true
console.log(zeroSumSubarray([1, 0])); // true
console.log(zeroSumSubarray([1, -1])); // true
console.log(zeroSumSubarray([2, -1, -1])); // true
console.log(zeroSumSubarray([6, -1, -1, 10, -4, -10])); // true
console.log(zeroSumSubarray([-5, -5, 2, 3, -2])); // true
