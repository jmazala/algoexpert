// https://www.algoexpert.io/questions/square-of-zeroes

/*
NAIVE SOLUTION
TIME: O(m*n) * O(max(m, n))
  O(m * n) for loops
  inside that loop, most expensive case is
  traversing to the right and down the entire array
  which is O(max(m, n))
SPACE: O(1)
*/
function squareOfZeroes(matrix) {
  const n = matrix.length;
  if (n < 2) {
    return false;
  }

  // Can't start a square from last row or last col
  for (let i = 0; i < n - 1; i++) {
    for (let j = 0; j < n - 1; j++) {
      if (matrix[i][j] === 1) {
        continue;
      }

      let length = 1;
      while (i + length <= n && j + length <= n && matrix[i][j + length] === 0 && matrix[i + length][j] === 0) {
        if (check(matrix, i, j, length)) {
          return true;
        }

        length++;
      }
    }
  }

  return false;
}

/*
Entering this function means top row and left column
are populated with zeros.
*/
function check(matrix, i, j, length) {
  // check right column
  for (let curI = i + 1; curI <= i + length; curI++) {
    if (matrix[curI][j + length] === 1) {
      return false;
    }
  }

  // check bottom row
  for (let curJ = j + 1; curJ <= j + length; curJ++) {
    if (matrix[i + length][curJ] === 1) {
      return false;
    }
  }

  return true;
}

// END NAIVE SOLUTION

/*
  DP SOLUTION
  From bottom right to top left, store number of zeros below 
  and to the right of that element i,j in a matrix
  Then, consider each 0 in the array as the top left
  hand corner of a square, and check accordingly for all 4 sides
  of a square

  TIME: O(n^3)
    O(n^2) to make zero map +
    O(n^3) because:
      O(n^2) to traverse array *
        O(n) for length loop *
          O(1) to check for a square
  SPACE:
    O(n^2) for zeroMap
*/
function squareOfZeroes(matrix) {
  const n = matrix.length;

  if (n < 2) {
    return false;
  }

  const zeroMap = makeZeroMap(matrix);

  for (let i = 0; i < n; i++) {
    for (let j = 0; j < n; j++) {
      if (matrix[i][j] === 1) {
        continue;
      }

      for (let length = 2; i + length <= n && j + length <= n; length++) {
        if (checkWithZeroMap(zeroMap, i, j, length)) {
          return true;
        }
      }
    }
  }

  return false;
}

function checkWithZeroMap(zeroMap, i, j, length) {
  const top = i;
  const bottom = i + length - 1;
  const left = j;
  const right = j + length - 1;

  const hasTopRow = zeroMap[top][left].right >= length;
  if (!hasTopRow) {
    return false;
  }

  const hasLeftCol = zeroMap[top][left].below >= length;
  if (!hasLeftCol) {
    return false;
  }

  const hasBottomRow = zeroMap[bottom][left].right >= length;
  if (!hasBottomRow) {
    return false;
  }

  const hasRightCol = zeroMap[top][right].below >= length;
  if (!hasRightCol) {
    return false;
  }

  return true;
}

function makeZeroMap(matrix) {
  const zeroMap = [];
  const n = matrix.length;

  for (let i = n - 1; i >= 0; i--) {
    const row = [];

    for (let j = n - 1; j >= 0; j--) {
      const item = { below: 0, right: 0 };
      row.unshift(item);

      if (matrix[i][j] === 1) {
        continue;
      }

      item.below++;
      item.right++;

      if (i !== n - 1) {
        item.below += zeroMap[0][j].below;
      }

      if (j !== n - 1) {
        item.right += row[1].right;
      }
    }

    zeroMap.unshift(row);
  }

  return zeroMap;
}

// Do not edit the line below.
exports.squareOfZeroes = squareOfZeroes;

console.log(squareOfZeroes([
  [1, 1, 1, 0, 1, 0],
  [0, 0, 0, 0, 0, 1],
  [0, 1, 1, 1, 0, 1],
  [0, 0, 0, 1, 0, 1],
  [0, 1, 1, 1, 0, 1],
  [0, 0, 0, 0, 0, 1]
])); // true

console.log(squareOfZeroes([
  [1, 1, 0, 1],
  [1, 0, 0, 1],
  [0, 0, 0, 1],
  [1, 1, 1, 1]
])); // true

console.log(squareOfZeroes([
  [0, 0, 0, 1],
  [0, 1, 1, 0],
  [0, 1, 0, 0],
  [0, 1, 0, 1]
])); // false