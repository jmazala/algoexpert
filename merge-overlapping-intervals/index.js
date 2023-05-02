const START = 0;
const END = 1;

function mergeOverlappingIntervals(array) {
  // sort the array by starting points
  const sorted = array.sort((a, b) => a[0] - b[0]);

  let i = 1;
  while (i < sorted.length) {
    if (doesOverlap(sorted[i - 1], sorted[i])) {
      mergeHelper(sorted, i);
    } else {
      i++;
    }
  }

  return sorted;
}

function mergeHelper(array, i) {
  array[i - 1][END] = Math.max(array[i - 1][END], array[i][END]);
  array.splice(i, 1);
}

function doesOverlap(interval1, interval2) {
  //interval 1 is first
  if (interval1[START] > interval2[END]) {
    return false;
  }

  //interval 2 is first
  if (interval2[START] > interval1[END]) {
    return false;
  }

  // overlap
  return true;
}

// Do not edit the line below.
exports.mergeOverlappingIntervals = mergeOverlappingIntervals;

const params = [
  [[1, 2], [3, 5], [6, 8], [4, 7], [9, 10]],
  [[1, 22], [-20, 30]]
];

for (const arg of params) {
  console.log(mergeOverlappingIntervals(arg).map(x => '[' + x.join(',') + ']'));
}