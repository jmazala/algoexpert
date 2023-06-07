// https://www.algoexpert.io/questions/longest-substring-without-duplication

/*
METHOD 1 - Brute force.  Use a set
TIME:  O(n^2)
SPACE:  O(n)
*/
function longestSubstringWithoutDuplication(string) {
  if (string.length < 2) {
    return string;
  }

  let answer = '';

  for (let i = 0; i < string.length; i++) {
    const seen = new Set();

    let end = i;
    while (end < string.length && !seen.has(string[end])) {
      seen.add(string[end]);
      end++;
    }

    const substring = string.substring(i, end);
    if (substring.length > answer.length) {
      answer = substring;
    }
  }

  return answer;
}


/*
Method 2 - use a hash so you can jump indices
TIME: O(n) for start loop
SPACE:  O(min(n, a)) depending how many chars we need to store in the hash.
could be the entire string if the whole thing doesnt have duplicates
*/
function longestSubstringWithoutDuplication2(string) {
  if (string.length < 2) {
    return string;
  }

  let answer = '';
  let start = 0;
  let end = start;
  while (start < string.length) {
    const hash = {};

    while (end < string.length && !(string[end] in hash)) {
      hash[string[end]] = end;
      end++;
    }

    const substring = string.substring(start, end);
    if (substring.length > answer.length) {
      answer = substring;
    }

    start = hash[string[end]] + 1;
    end = start;
  }

  return answer;
}

// Do not edit the line below.
exports.longestSubstringWithoutDuplication = longestSubstringWithoutDuplication;

console.log(longestSubstringWithoutDuplication2("abc")); // abc
console.log(longestSubstringWithoutDuplication2("clementisacap")); // mentisac
