//https://www.algoexpert.io/questions/Spiral%20Traverse

const RIGHT = 0;
const DOWN = 1;
const LEFT = 2;
const UP = 3;

function spiralTraverse(array) {
  const answer = [];
  const M = array.length;
  if (!M) {
    return answer;
  }

  const N = array[0].length;
  if (!N) {
    return M;
  }

  let top = 0;
  let bottom = M - 1;
  let left = 0;
  let right = N - 1;

  while (top <= bottom && left <= right) {
    helper(top, bottom, left, right);
    top++;
    bottom--;
    left++;
    right--;
  }

  return answer;

  function helper(top, bottom, left, right) {
    //top left to top right
    for (let j = left; j <= right; j++) {
      answer.push(array[top][j]);
    }

    //excluding top right down to bottom right
    for (let i = top + 1; i <= bottom; i++) {
      answer.push(array[i][right]);
    }

    //excluding bottom right over to bottom left
    for (let j = right - 1; j >= left; j--) {
      if (top === bottom) {
        return;
      }
      
      answer.push(array[bottom][j]);
    }

    //excluding bottom left up to but excluding top left
    for (let i = bottom - 1; i > top; i--) {
      if (left === right) {
        return;
      }
      
      answer.push(array[i][left]);
    }
  }
}

// Do not edit the line below.
exports.spiralTraverse = spiralTraverse;

console.log(spiralTraverse([
  [1, 2, 3, 4],
  [12, 13, 14, 5],
  [11, 16, 15, 6],
  [10, 9, 8, 7]]));

console.log(spiralTraverse([
  [1, 2, 3, 4],
  [10, 11, 12, 5],
  [9, 8, 7, 6]]));

console.log(spiralTraverse([
  [1, 2, 3],
  [12, 13, 4], 
  [11, 14, 5],
  [10, 15, 6],
  [9, 8, 7]]));