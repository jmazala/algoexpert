// Tip: You can use the Array.isArray function to check whether an item
// is a list or an integer.
function productSum(array, multiplier = 1) {
  let answer = 0;

  for (const element of array) {
    if (Array.isArray(element)) {
      answer += productSum(element, multiplier + 1);
    } else {
      answer += element;
    }
  }

  return multiplier * answer;
}

// Do not edit the line below.
exports.productSum = productSum;
