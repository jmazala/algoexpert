// https://www.algoexpert.io/questions/longest-string-chain

/*
Intuitive solution:
1 - Sort the strings array shortest to longest
2 - Try to form string chains with each string by removing 1 letter and 
  use the hash table to remember the longest string chains from each string
3 - Remember and return longest string chain so far.

TIME: O(n log n) + O(nw^2) where w is length of longest string
O(n log n) to sort strings
O(n) to loop through sorted strings
O(w) to loop through characters and remove them
O(w) for slicing strings
SPACE: O(n^2)
O(n) for answer
O(n^2) for hash (worst case is assuming the entire array is a string train)
*/
function longestStringChain(strings) {
  strings.sort((a, b) => a.length - b.length);

  let answer = [];
  const hash = {};

  for (const string of strings) {
    hash[string] = hash[string] || new Set([string]);
    const copy = new Set(hash[string]);

    for (let i = 0; i < string.length; i++) {
      const oneCharRemoved = string.slice(0, i) + string.slice(i + 1);
      if (!hash[oneCharRemoved]) {
        continue;
      }

      const union = new Set([...hash[oneCharRemoved], ...copy]);
      if (union.size > hash[string].size) {
        hash[string] = union;
      }
    }

    if (hash[string].size > answer.length) {
      answer = Array.from(hash[string]);
    }
  }

  return answer.length > 1 ? answer.reverse() : [];
}

/*
SOLUTION 2 - same as solution 1, but store indices instead.
Space is still O(n^2) though but we're limited to 4 bytes per word in the chain
*/
function longestStringChain(strings) {
  strings.sort((a, b) => a.length - b.length);

  let answer = [];
  const hash = {};

  for (let stringIdx = 0; stringIdx < strings.length; stringIdx++) {
    const string = strings[stringIdx];
    hash[string] = hash[string] || new Set([stringIdx]);
    const copy = new Set(hash[string]);

    for (let charIdx = 0; charIdx < string.length; charIdx++) {
      const oneCharRemoved = string.slice(0, charIdx) + string.slice(charIdx + 1);
      if (!hash[oneCharRemoved]) {
        continue;
      }

      const union = new Set([...hash[oneCharRemoved], ...copy]);
      if (union.size > hash[string].size) {
        hash[string] = union;
      }
    }

    if (hash[string].size > answer.length) {
      answer = Array.from(hash[string]);
    }
  }

  return answer.length > 1 ? answer.reverse().map(i => strings[i]) : [];
}

/*
SOLUTION 3 - Rather than store the indices of the entire string chain, map
each string index to the next index in the string chain

Space would be O(n) for the hash and O(nm) for answer
*/

// Do not edit the line below.
exports.longestStringChain = longestStringChain;

// ['abcdef', 'abcde', 'abde', 'ade', 'ae']
console.log(longestStringChain(['abde', 'abc', 'abd', 'abcde', 'ade', 'ae', '1abde', 'abcdef']));