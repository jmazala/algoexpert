// https://www.algoexpert.io/questions/common-characters

/*
METHOD 1 - turn each string to a set of chars,
use a util or slick function to find the intersection of them all

PROS - readable, same time complextiy
CONS - lots of comparisons.  Extra memory for each set
*/
function commonCharacters(strings) {
  const sets = strings.map(function (string) {
    return new Set(string.split(''));
  });

  /*
    this is really finding the intersection of sets
    I found this code snippet at https://stackoverflow.com/a/55053125
  */
  // return Array.from(sets.reduce((a, b) => new Set([...a].filter(x => b.has(x)))));
  return Array.from(sets.reduce(function (a, b) {
    return new Set(Array.from(a).filter(x => b.has(x)));
  }));
}

/*
METHOD 2 -
Reduce number of comparisons by starting with the smallest string.
Turn that into a set of chars.
Compare with each string and remove until all comparisons are done,
or the set is empty

TIME:
O(n) for finding smallest string where n is # of strings
O(m * n -1) for set comparisons where m is length of shortest string
= O(m * n)
SPACE:
O(i) for smallestIndex
O(m) for smallestSet
O(l) for currentStringSet where l is length of string, but this is temporary heap storage
O(m) for returned array
= O(m)
*/

function commonCharacters(strings) {
  /*
  We could sort by length of string, but that's n log n.
  Just linear scan instead
  */
  let smallestIndex = 0;
  for (let i = 1; i < strings.length; i++) {
    const string = strings[i];
    if (string.length < strings[smallestIndex].length) {
      smallestIndex = i;
    }
  }

  // turn string into set of chars.  thanks JS
  const smallestSet = new Set(strings[smallestIndex]);
  for (let i = 0; i < strings.length; i++) {
    if (i === smallestIndex) {
      continue;
    }

    const currentStringSet = new Set(strings[i]);
    for (const checkChar of smallestSet.values()) {
      if (!currentStringSet.has(checkChar)) {
        smallestSet.delete(checkChar);
      }
    }

    if (smallestSet.size === 0) {
      break;
    }
  }
  return Array.from(smallestSet);
}

// Do not edit the line below.
exports.commonCharacters = commonCharacters;

console.log(commonCharacters(['abc', 'ade', 'afg']));