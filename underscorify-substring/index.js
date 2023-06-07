// https://www.algoexpert.io/questions/underscorify-substring

/*
TIME: O(n*m) for first loop
SPACE:  O(n) for output string, O(n) for match (assuming entire string is a match)
*/
function underscorifySubstring(string, substring) {
  const matches = [];

  for (let i = 0; i < string.length; i++) {
    if (string.substring(i, i + substring.length) === substring) {
      matches.push([i, i + substring.length]);
    }
  }

  mergeIntervals(matches);
  let output = '';

  for (let i = 0; i < string.length; i++) {
    if (matches.length > 0 && i === matches[0][0]) {
      output += '_';
    }

    output += string[i];

    if (matches.length > 0 && i === (matches[0][1] - 1)) {
      output += '_';
      matches.shift();
    }
  }

  return output;
}

function mergeIntervals(array) {
  let i = 0;
  while (i < array.length - 1) {
    if (array[i + 1][0] <= array[i][1]) {
      array[i][1] = array[i + 1][1];
      array.splice(i + 1, 1);
    } else {
      i++;
    }
  }
}

// Do not edit the line below.
exports.underscorifySubstring = underscorifySubstring;

console.log(underscorifySubstring('testthis is a testtest to see if testestest it works', 'test'));