function zigzagTraverse(array) {
  //only going in 2 directions.  down or up
  //outer bounds are always on the permiter

  //in the middle you always go either up/right or down/left

  //at col 0 if we're going diagonally down we can't go left
  //so we have to go down

  //at row M-1 and going diagonally down, go right

  //at row 0 and going diagonally up, we have to go right

  const M = array.length;
  const N = array[0].length;

  let i = 0;
  let j = 0;
  let diagonalDown = true;
  const output = [];

  while (output.length < M * N) {
    output.push(array[i][j]);

    if (diagonalDown) {
      if (j === 0 || i === M - 1) {
        diagonalDown = false;
        if (i === M - 1) {
          j++;
        } else {
          i++;
        }
      } else {
        i++;
        j--;
      }
    } else {
      //we're going diagonal up
      if (i === 0 || j === N - 1) {
        diagonalDown = true;
        if (j === N - 1) {
          i++;
        } else {
          j++;
        }
      } else {
        i--;
        j++;
      }
    }
  }

  return output;
}

// Do not edit the line below.
exports.zigzagTraverse = zigzagTraverse;

console.log(zigzagTraverse([[1, 3, 4, 10], [2, 5, 9, 11], [6, 8, 12, 15], [7, 13, 14, 16]]));