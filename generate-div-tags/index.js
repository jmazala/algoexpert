// https://www.algoexpert.io/questions/generate-div-tags

/*
Using recursive algorithm counting opening and closing tags used
Time & Space complexity is catalan number.  This is combinatorial
*/
function generateDivTags(numberOfTags) {
  if (numberOfTags === 0) {
    return [];
  }

  const output = [];
  helper(numberOfTags, numberOfTags, '');
  return output;

  function helper(openingNeeded, closingNeeded, prefix) {
    if (!openingNeeded && !closingNeeded) {
      output.push(prefix);
      return;
    }

    /*
    If we require any opening tags, it's always valid to add an opening tag
    Doesn't matter the prefix.
    This works because we always add an opening tag first
    */
    if (openingNeeded > 0) {
      helper(openingNeeded - 1, closingNeeded, prefix + '<div>');
    }

    /*
    If we need closing tags,
    It must be valid to add a closing tag to our prefix
    This means that somewhere in the string there is an opening tag that needs to be closed
    */
    if (openingNeeded < closingNeeded) {
      helper(openingNeeded, closingNeeded - 1, prefix + '</div>');
    }
  }
}

// Do not edit the line below.
exports.generateDivTags = generateDivTags;

console.log(generateDivTags(0)); // []
console.log(generateDivTags(1)); // ['<div></div>']
console.log(generateDivTags(2)); // ['<div><div></div></div>', '<div></div><div></div>']
console.log(generateDivTags(3)); // ['<div><div><div></div></div></div>', '<div></div><div><div></div></div>', '<div><div><div><div></div></div></div></div>', '<div></div><div><div><div></div></div></div>']
