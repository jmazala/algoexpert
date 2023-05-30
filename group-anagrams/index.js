// https://www.algoexpert.io/questions/group-anagrams

function groupAnagrams(words) {
  const hash = {};
  for (word of words) {
    const sortedWord = Array.from(word).sort().join('');
    hash[sortedWord] = hash[sortedWord] || [];
    hash[sortedWord].push(word);
  }

  return Object.values(hash);
}

// Do not edit the line below.
exports.groupAnagrams = groupAnagrams;
