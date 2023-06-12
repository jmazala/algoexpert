// https://www.algoexpert.io/questions/subarray-sort

function subarraySort(array) {
  let unsortedMin = Infinity;
  let unsortedMax = -Infinity;

  // Find the max and min values that are out of place in the "sorted" array
  for (let i = 0; i < array.length; i++) {
    if (isUnsorted(i)) {
      unsortedMin = Math.min(unsortedMin, array[i]);
      unsortedMax = Math.max(unsortedMax, array[i]);
    }
  }

  if (unsortedMin === Infinity) {
    return [-1, 1];
  }

  // Find where to place the smallest and largest values
  let left = 0;
  while (unsortedMin >= array[left]) {
    left++;
  }

  let right = array.length - 1;
  while (unsortedMax <= array[right]) {
    right--;
  }

  return [left, right];

  function isUnsorted(i) {
    if (i === 0) {
      return array[i] > array[i + 1];
    }

    if (i === (array.length - 1)) {
      return array[i] < array[i - 1];
    }

    return array[i] < array[i - 1] || array[i] > array[i + 1];
  }
}
// Do not edit the line below.
exports.subarraySort = subarraySort;

console.log(subarraySort([2, 3, 4, 1])); //[0, 3]
console.log(subarraySort([1, 2, 4, 7, 10, 11, 7, 12, 6, 7, 16, 18, 19])); //[3, 9]
console.log(subarraySort([1, 2, 3, 4, 5, 6, 7, 16, 18, 19])); //[-1, -1]
console.log(subarraySort([2, 1])); //[0, 1]
console.log(subarraySort([1, 2, 4, 7, 10, 11, 7, 12, 7, 7, 16, 18, 19])); //[4,9]