// https://www.algoexpert.io/questions/first-non-repeating-character

/*
Must be O(n) because we need to check every char in the string
Store in a hash keeping track of the count and first index
*/
function firstNonRepeatingCharacter(string) {
  const hash = {};
  let firstRepeatingCharacter = '';

  for (let i = 0; i < string.length; i++) {
    const c = string[i];
    if (!hash[c]) {
      hash[c] = 0;
    }

    hash[c]++;
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

console.log(firstNonRepeatingCharacter('abcdcaf')); //1