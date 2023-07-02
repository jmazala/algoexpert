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

Sort the array by x coordinate and iterate through points with 2 pointers.  This way you can find vertical lines faster.
They'll be next to each other in the array.

Maybe we could do something to store all vertical lines in a hash?  Store all vertical lines in a hash?
*/
function minimumAreaRectangle2(points) {
  points.sort((a, b) => a[0] === b[0] ? a[1] - b[1] : a[0] - b[0]);
  const xToY = {};

  for (const point of points) {
    const [x, y] = point;
    xToY[x] = xToY[x] || new Set();
    xToY[x].add(y);
  }

  for (const x of Object.keys(xToY)) {
    if (xToY[x].size < 2) {
      delete xToY[x];
    }
  }

  points = points.filter(i => i[0] in xToY);

  let minArea = Infinity;

  for (let i = 0; i < points.length; i++) {
    const [x1, y1] = points[i];

    for (let j = i + 1; j < points.length; j++) {
      const [x2, y2] = points[j];

      if (x1 !== x2) {
        break;
      }

      // we have a vertical line
      const verticalLineLength = Math.abs(y1 - y2);
      if (verticalLineLength >= minArea) {
        // skip forward til  we have a new value for x1
        while (points[i] && points[i][0] === x1) {
          i++;
        }

        i--;
        break;
      }

      const checked = new Set();
      checked.add(x1);
      for (let k = j + 1; k < points.length; k++) {
        const x3 = points[k][0];
        if (checked.has(x3)) {
          continue;
        }

        checked.add(x3);

        if (xToY[x3].has(y1) && xToY[x3].has(y2)) {
          const area = verticalLineLength * (x3 - x1);
          minArea = Math.min(minArea, area);
          break;
        }
      }
    }
  }

  return minArea === Infinity ? 0 : minArea;
}

// Do not edit the line below.
exports.minimumAreaRectangle = minimumAreaRectangle;

console.log(minimumAreaRectangle2([
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


console.log(minimumAreaRectangle2([
  [0, 0],
  [4, 4],
  [8, 8],
  [0, 8],
  [0, 4],
  [6, 0],
  [6, 4]
])); // 24

console.log(minimumAreaRectangle2([
  [-4, 4],
  [4, 4],
  [4, -2],
  [-4, -2],
  [0, -2],
  [4, 2],
  [0, 2],
  [0, 4],
  [2, 3],
  [0, 3],
  [2, 4]
])); // 2