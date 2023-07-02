// https://www.algoexpert.io/questions/line-through-points

/*
TIME: O(n^2) for loops
SPACE: O(n - 1) for hash table
*/
function lineThroughPoints(points) {
  if (points.length < 2) {
    return points.length;
  }

  points.sort((a, b) => a[0] === b[0] ? a[1] - b[1] : a[0] - b[0]);

  let maxPoints = 2;
  let hash;

  for (let i = 0; i < points.length; i++) {
    hash = {};
    const [x1, y1] = points[i];
    let numPoints = 2;

    for (let j = i + 1; j < points.length; j++) {
      const [x2, y2] = points[j];
      const dY = (y2 - y1);
      const dX = (x2 - x1);
      const slope = dY / dX;

      if (hash[slope]) {
        hash[slope]++;
      } else {
        hash[slope] = 2;
      }

      if (hash[slope] > numPoints) {
        numPoints = hash[slope];
      }
    }

    maxPoints = Math.max(maxPoints, numPoints);
  }

  return maxPoints;
}

// Do not edit the line below.
exports.lineThroughPoints = lineThroughPoints;

console.log(lineThroughPoints([])); // 0
console.log(lineThroughPoints([[1, 1]])); // 1
console.log(lineThroughPoints([
  [-1, -1],
  [-3, -1],
  [-4, -1],
  [1, 1],
  [4, 1]
])); // 3
console.log(lineThroughPoints([
  [1, 1],
  [2, 2],
  [3, 3],
  [0, 4],
  [-2, 6],
  [4, 0],
  [2, 1]
])); // 4