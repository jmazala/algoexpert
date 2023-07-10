// https://www.algoexpert.io/questions/first-non-repeating-character

/*
Must be O(n) because we need to check every char in the string
Store in a hash keeping track of the count and first index
TIME: O(n) to traverse entire string
SPACE: O(1) because problem statement says "lowercase English-alphabet letters" in s.  Thus only 26 hashtable keys
*/
function firstNonRepeatingCharacter(string) {
  const hash = {};

  for (let i = 0; i < string.length; i++) {
    const c = string[i];
    hash[c] = hash[c] + 1 || 1;
  }

  for (let i = 0; i < string.length; i++) {
    const c = string[i];
    if (hash[c] === 1) {
      return i;
    }
  }

  return -1;
}

// Do not edit the line below.
exports.firstNonRepeatingCharacter = firstNonRepeatingCharacter;

console.log(firstNonRepeatingCharacter('abcdcaf')); // 1