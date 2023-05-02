// https://www.algoexpert.io/questions/missingNumbers

/*
 should be 1 - n with 2 numbers missing 
 i.e. nums.length = n - 2

 return a list of length 2 with the missing numbers
 numerically

 METHOD 1 - use a set to keep track of numbers, then search the set
 O(n) space for the set
 O(n) time for looping through nums, and again through the set

 METHOD 2 - pre-fill a set, remove each number when you see it in the array, return 2 remaining elements
 O(n) space for set
 O(n) time to create the set
 O(n) time to loop through nums

 METHOD 3 - keep track of running sum.  store in the array if you found a number
 by transforming the val into an index by subtracting 1
 use 2 extra booleans for the 2 overflow values

 check the booleans, or check the array to find a missing value
 the other missing value can be determined by the running sum

 O(1) space for the extra booleans
 O(n) time to loop through nums twice
 */
function missingNumbers(nums) {
  const n = nums.length + 2;
  const desiredSum = n * (n + 1) / 2;
  let nMinusOneFound = false;
  let nFound = false;
  let runningSum = 0;

  for (let i = 0; i < nums.length; i++) {
    const val = Math.abs(nums[i]);
    runningSum += val;

    if (val === n) {
      nFound = true;
      continue;
    }

    if (val === (n - 1)) {
      nMinusOneFound = true;
      continue;
    }

    const markedIndex = val - 1;
    nums[markedIndex] *= -1;
  }

  if (!nMinusOneFound && !nFound) {
    return [n - 1, n];
  }

  if (!nFound) {
    return [desiredSum - runningSum - n, n];
  }

  if (!nMinusOneFound) {
    return [desiredSum - runningSum - (n - 1), n - 1];
  }

  for (let j = 0; j < nums.length; j++) {
    if (nums[j] < 0) { // marked as negative, we found it
      continue;
    }

    const missingVal = j + 1;
    const otherMissingVal = desiredSum - runningSum - missingVal;
    return [Math.min(missingVal, otherMissingVal), Math.max(missingVal, otherMissingVal)];
  }

  return [] // error;
}

// Do not edit the line below.
exports.missingNumbers = missingNumbers;

/*
TEST CASES
first 2
last 2
either of the last 2 but not the other
*/

console.log(missingNumbers([1, 2, 3])); //[4, 5]
console.log(missingNumbers([3, 4, 5])); //[1, 2]
console.log(missingNumbers([1, 2, 5])); //[3, 4]
console.log(missingNumbers([1, 2, 4])); //[3, 5]

/*
example is [1, 4, 3]
nums.length = 3
n = 3 + 2 = 5
we should have [1, 2, 3, 4, 5]

if we go through the array, subtract 1 to get an index,
and have 1 or 2 extra variables for the overflow that could work

ex [1, 4, 3] (one of the 2 greatest is missing, the bigger one)
i = 0
abs(nums[i]) = 1
abs(nums[i]) - 1 = 0
mark as negative
nums = [-1, 4, 3]

i = 1
abs(nums[i]) = 4
abs(nums[i]) - 1 = 3
3 is >= than nums.length
n-4 = 1
nMinusOneFound = true
nFound = false
nums = [-1, 4, 3]

i = 2
abs(nums[i]) = 3
abs(nums[i]) - 1 = 2
mark as negative
nums is [-1, 4, -3]

lookingForSum = n * (n+1) / 2 = 15
assume we kept track of the sum inside the loop
in this case it totals 1 + 4 + 3 = 8

nFound = false
missingOne = n
missingTwo = lookingForSum - sumOfArray - missingOne = 15 - 8 - 5 = 2

return [min(missingOne, missingTwo), max(missingOne, missingTwo)]

ex [1, 5, 3] (one of the 2 greatest is missing, the smaller one)
i = 0
abs(nums[i]) = 1
abs(nums[i]) - 1 = 0
mark as negative
nums = [-1, 5, 3]

i = 1
abs(nums[i]) = 5
abs(nums[i]) - 1 = 4
4 >= nums.length
5 === n
nFound = true
nums [-1, 5, 3]

i = 2
abs(nums[i]) = 3
abs(nums[i]) - 1 = 2
mark as negative
nums = [-1, 5, -3]

runningSum = 9

return [desiredSum - runningSum - (n - 1), n - 1];
= return [15 - 9 - 4 = 2, 4] (CORRECT)

ex [1, 4, 5] (neither of the 2 greatest is missing)
i = 0
val = 1
index = 0
mark as negative
nums = [-1, 4, 5]
i = 1
val = 4
index = 3
3 >= 3
4 != 5
nMinusOneFound = true

i = 2
val = 5
index = 4
4 >= 3
5 === 5
nFound = true

j = 0
nums[j] = -1 (continue)

j = 1
nums[j] = 4
4 is not negative
missingVal = j + 1 = 2
otherMissingVal = desiredSum - runningSum - missingVal = (15 - 10 - 2 = 3)
return [2, 3];

ex [1, 2, 3] (both of the greatest 2 are missing)
i = 0
val = 1
index = 0
mark as negative
nums = [-1, 2, 3]

i = 1
val = 2
index = 1
mark as negative
nums = [-1, -2, 3]

i = 2
val = 3
index = 2
mark as negative
nums = [-1, -2, -3]

return [4, 5]

ex [3, 4, 5] (least 2 are missing)
i = 0
val = 3
index = 2
mark as negative
nums = [3, 4, -5]

i = 1
val = 4
nMinusOneFound = true

i = 2
val = 5
5 === n
nFound = true

j = 0
nums[j] > 0
missingVal = 0 + 1 = 1
otherMissingVal = 15 - 12 - 1 = 2

return [1, 2]

*/