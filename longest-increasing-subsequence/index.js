// https://www.algoexpert.io/questions/longest-increasing-subsequence

/*
Brute force:  Try all combinations
This is quite literally brutal LOL
TIME: O(2^n)
SPACE: O(n) * O(n^n) = O(n^n)
  O(n) for recursion stack
  O(n) for all combinations of 1
  O(n^2) for all combinations of 2
  ... = O(n) + ... = O(n^n)
*/
function longestIncreasingSubsequence(array) {
  let answer = [];
  helper(0, []);
  return answer;

  function helper(index, prefix) {
    if (prefix.length > answer.length) {
      answer = prefix.slice();
    }

    if (index >= array.length) {
      return;
    }

    // if we don't have enough slots left return
    if (array.length - index - 1 + prefix.length < answer.length) {
      return;
    }

    while (index < array.length) {
      if (!prefix.length || array[index] > prefix[prefix.length - 1]) {
        const copy = prefix.slice();
        copy.push(array[index]);
        helper(index + 1, copy);
      }

      index++;
    }
  }
}

/*
DP:  Store longest subsequence ending with each number in the array
at slot i.
For each new number, work backwards to see if we can append that number
to the subsequences calculated beforehand.  Use an additional variable
to keep track of the longest one so far.

TIME: O(n^2)
  O(n) for outer loop
  O(n - 1) for inner loop
SPACE: O(n^2)
  O(1) for element 1 of dp array
  O(2) for element 2 of dp array
  + ... O(n) for element n of dp array
  = 1 + 2 + ... n = O(n^2)

This could be optimized to O(n) space by keeping track of maxLengthIndex in the for loop,
and storing the last element of each subsequence in the dp array
*/
function longestIncreasingSubsequence(array) {
  const longestSubsequences = new Array(array.length);
  let answer = [];

  for (let i = 0; i < array.length; i++) {
    const val = array[i];
    let longest = [val];

    for (let j = i - 1; j >= 0; j--) {
      const subsequence = longestSubsequences[j];

      if (val > subsequence[subsequence.length - 1]) {
        if (subsequence.length + 1 > longest.length) {
          const copy = subsequence.slice();
          copy.push(val);
          longest = copy;
        }
      }
    }

    longestSubsequences[i] = longest;

    if (longestSubsequences[i].length > answer.length) {
      answer = longestSubsequences[i];
    }
  }

  return answer;
}

/*
Further optimize on solution 2 (see solution 2 for more details)
TIME: O(n^2)
  O(n) for outer loop
  O(n-1) for inner loop = O(n)
  O(n) to build subsequence
SPACE: O(n)
  O(n) for subsequenceLengths
  O(n) for secondToLastIndices
  O(1) for maxLengthIndex
  O(n) for output array
*/
function longestIncreasingSubsequence(array) {
  /*
  Length of longest subsequence ending with (CONTAINING) array[i]
  Can default to 1 because all arrays of element 1 are subsequences
  */
  const subsequenceLengths = new Array(array.length).fill(1);

  /*
  Index of the second-to-last number of each subsequence
  ending at index i (contains array[i]). The index itself represents the last
  number). Will be null for any subsequence of length 1
  */
  const secondToLastIndices = new Array(array.length);
  let maxLengthIndex = 0;

  for (let i = 1; i < array.length; i++) {
    const val = array[i];

    for (let j = 0; j < i; j++) {
      const prevVal = array[j];

      if (prevVal < val) {
        if (subsequenceLengths[j] + 1 >= subsequenceLengths[i]) {
          subsequenceLengths[i] = subsequenceLengths[j] + 1;
          secondToLastIndices[i] = j;
        }
      }
    }

    if (subsequenceLengths[i] >= subsequenceLengths[maxLengthIndex]) {
      maxLengthIndex = i;
    }
  }

  return buildSubsequence(maxLengthIndex, array, secondToLastIndices);
}

function buildSubsequence(currentIndex, array, indices) {
  const subsequence = [];
  while (currentIndex >= 0) {
    subsequence.unshift(array[currentIndex]);
    currentIndex = indices[currentIndex];
  }

  return subsequence;
}

/*
Optimized DP solution (really hard)

TIME: O(n log n)
SPACE: O(n)

Iterate through the array one time and apply a binary search 
to try and build a new subsequence.
*/
function longestIncreasingSubsequence(array) {
  // Point to previous values in the subsequence
  const sequences = new Array(array.length);
  /*
  At each index i, store the index of the smallest number in a
  increasing subsequence of length i
  */
  const indices = new Array(array.length + 1);
  let longestSubsequenceLength = 0;

  for (let i = 0; i < array.length; i++) {
    // Binary search for the smallest number ending with a number less than array[i] that ends a subsequence
    const newLength = binarySearch(1, longestSubsequenceLength, array[i]);
    sequences[i] = indices[newLength - 1];
    indices[newLength] = i;
    longestSubsequenceLength = Math.max(longestSubsequenceLength, newLength);
  }

  return buildSubsequence(indices[longestSubsequenceLength], array, sequences);

  function binarySearch(low, high, num) {
    if (low > high) {
      return low;
    }

    const mid = Math.floor((low + high) / 2);
    if (array[indices[mid]] < num) {
      low = mid + 1;
    } else {
      high = mid - 1;
    }

    return binarySearch(low, high, num);
  }
}


// Do not edit the line below.
exports.longestIncreasingSubsequence = longestIncreasingSubsequence;

console.log(longestIncreasingSubsequence([5, 7, -24, 12, 10, 2, 3, 12, 5, 6, 35])); // [-24, 2, 3, 5, 6, 35]