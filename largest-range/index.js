/**
 * @param {number[]} array
 * @return {number}
 */
var largestRange = function (array) {
  if (!array.length) {
    return [];
  }

  const set = new Set(array);
  let answer = [array[0], array[0]]; //placeholder

  set.forEach(num => {
    if (!set.has(num - 1)) { //starting a new range
      const min = num;
      let max = num;

      while (set.has(max)) {
        max++;
      }

      if ((max - min) > answer[1] - answer[0]) {
        answer = [min, max - 1];
      }
    }
  });

  return answer;
}

console.log(largestRange([1, 11, 3, 0, 15, 5, 2, 4, 10, 7, 12, 6])); // [0, 7]
console.log(largestRange([100, 4, 200, 1, 3, 2])); // [1, 4]
console.log(largestRange([9, 1, 4, 7, 3, -1, 0, 5, 8, -1, 6])); // [3, 9]
console.log(largestRange([2147483646, -2147483647, 0, 2, 2147483644, -2147483645, 2147483645])); // [2147483644, 2147483646]

// Do not edit the line below.
exports.largestRange = largestRange;
