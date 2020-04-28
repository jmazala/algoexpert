//https://www.algoexpert.io/questions/Monotonic%20Array

function isMonotonic(array) {
  if (array.length <= 2) {
    return true;
  }

  let start = 0;
  while (array[start] === array[start + 1]) {
    start++;
  }

  // Write your code here.
  const increasing = array[start + 1] > array[start] ? true : false;

  for (let i = start + 1; i < array.length - 1; i++) {
    while (array[i + 1] === array[i]) {
      i++;
    }

    if (increasing && array[i + 1] < array[i]) {
      return false;
    }

    if (!increasing && array[i + 1] > array[i]) {
      return false;
    }
  }

  return true;
}

// Do not edit the line below.
exports.isMonotonic = isMonotonic;

console.log(isMonotonic([-1, -5, -10, -1100, -1100, -1101, -1102, -9001])); //true
console.log(isMonotonic([1, 1, 2, 3, 4, 5, 5, 5, 6, 7, 8, 8, 9, 10, 11])); //true