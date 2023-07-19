// https://www.algoexpert.io/questions/palindrome-partitioning-min-cuts

/*
NAIVE SOLUTION
TIME: O(n^3)
  O(n^2) to first make palindromicities
  O(n^2) * O(n) = O(n^3) to populate palindromicities
SPACE: O(n^2)
  O(n^2) for palindromicities + O(n) for minCuts
*/
function palindromePartitioningMinCutsNaive(string) {
  const palindromicities = [];
  for (let i = 0; i < string.length; i++) {
    const row = [];
    for (let j = 0; j < string.length; j++) {
      row.push(i === j);
    }

    palindromicities.push(row);
  }

  for (let i = 0; i < string.length; i++) {
    for (let j = i + 1; j < string.length; j++) {
      palindromicities[i][j] = isPalindrome(i, j, string);
    }
  }

  // ETC (minCuts, see below)
}

// slice from i to j + 1
function isPalindrome(i, j, string) {
  while (i < j) {
    if (string[i] !== string[j]) {
      return false;
    }

    i++;
    j--;
  }

  return true;
}

/*
TIME: O(n^2)
O(n^2) to build palindromicities array
O(n) for all palindromes of length 2
O(n) for all of 3... etc
= O(n^2) for all palindromes of length 2 - n (THIS IS BETTER THAN NAIVE)
O(n^2) for minCuts calculations
SPACE: O(n^2)
  O(n^2) for palindromicities
  O(n) for minCuts
*/
function palindromePartitioningMinCuts(string) {
  /*
  Build a matrix where matrix[i][j] = if a substring
  from i to j is a palindrome.  All substrings of
  length 1 are palindromes.  Also, half the matrix
  will naturally be false as there is no substring
  if j > i
  */
  const palindromicities = [];
  for (let i = 0; i < string.length; i++) {
    const row = [];
    for (let j = 0; j < string.length; j++) {
      row.push(i === j);
    }

    palindromicities.push(row);
  }

  // Next do all palindromes of length 2.  Simple
  for (let i = 0; i < string.length - 1; i++) {
    const j = i + 1;
    palindromicities[i][j] = string[i] === string[j];
  }

  /*
  Populate the matrix starting will all palindromes of length 3 up to length of string.
  This can be accomplished by checking the palindrome status (of length -1) that's inside i and j
  as well as the 2 additional chars string[i] and string[j]
  */
  for (let length = 3; length <= string.length; length++) {
    for (let i = 0; i <= string.length - length; i++) {
      const j = i + length - 1;
      palindromicities[i][j] = string[i] === string[j] && palindromicities[i + 1][j - 1];
    }
  }

  // Build an array of size string.length where a[i] = how many cuts you'd need from index 0 to i
  const cuts = new Array(string.length).fill(Infinity);

  for (let i = 0; i < string.length; i++) {
    /*
    2 easy cases.  Entire string up to i is a palindrome
    or entire string up to i-1 is a palindrome.
    0 or 1 cuts respectively
    */
    if (palindromicities[0][i]) {
      cuts[i] = 0;
      continue;
    }

    if (cuts[i - 1] === 0) {
      cuts[i] = 1;
      continue;
    }

    /*
    Start at 1 additional cut, but work through again
    to see if there is a better case.  Start j at index 1
    and work all the way up to your current index.
    Look for a palindrome between j and i, and add an additional cut to it.
    Check if that ends up being favorable.
    */
    cuts[i] = cuts[i - 1] + 1;
    for (let j = 1; j < i; j++) {
      if (palindromicities[j][i]) {
        cuts[i] = Math.min(cuts[j - 1] + 1, cuts[i]);
      }
    }
  }

  return cuts[cuts.length - 1];
}

// Do not edit the line below.
exports.palindromePartitioningMinCuts = palindromePartitioningMinCuts;

console.log(palindromePartitioningMinCuts('noonabbad')); // 2