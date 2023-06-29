// https://www.algoexpert.io/questions/minimum-area-rectangle

/*
METHOD 1 - Generate diagonal pairs
*/
function minimumAreaRectangle(points) {
  const xToY = {};

  for (const point of points) {
    const [x, y] = point;
    xToY[x] = xToY[x] || new Set();
    xToY[x].add(y);
  }

  let minArea = -1;

  for (let i = 0; i < points.length; i++) {
    const [x1, y1] = points[i];

    for (let j = i + 1; j < points.length; j++) {
      const [x2, y2] = points[j];
      // only form diagonals to check for squares
      if (x2 === x1 || y2 === y1) {
        continue;
      }

      const [x3, y3] = [x1, y2];
      const [x4, y4] = [x2, y1];

      if (xToY[x3].has(y3) && xToY[x4].has(y4)) {
        const area = Math.abs(x1 - x2) * Math.abs(y1 - y2);
        minArea = minArea === -1 ? area : Math.min(minArea, area);
      }
    }
  }

  return minArea === -1 ? 0 : minArea;
}

/*
METHOD 2 - Look for vertical lines, search for other vertical lines
Find a pair of points (say [1,2] and [1,5].  Then search all other sets with point X2 for a set that contains 2 & 5).
You could reduce this search by sorting points and stopping iteration for more rectangles based on your current minArea
*/
function minimumAreaRectangle(points) {
}

// Do not edit the line below.
exports.minimumAreaRectangle = minimumAreaRectangle;


console.log(minimumAreaRectangle([
  [1, 5],
  [5, 1],
  [4, 2],
  [2, 4],
  [2, 2],
  [1, 2],
  [4, 5],
  [2, 5],
  [-1, -2]
])); // 3