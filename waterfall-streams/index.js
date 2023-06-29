// https://www.algoexpert.io/questions/waterfall-streams

const BLOCK = 1;
const DOWN = 0;
const LEFT = 1;
const RIGHT = 2;

function waterfallStreams(array, source) {
  const m = array.length;
  const n = array[0].length;
  const output = new Array(n).fill(0);
  const queue = [[0, source, 100, DOWN]];

  const fCanMoveDown = (i, j) => i < m - 1 && array[i + 1][j] !== BLOCK;
  const fCanMoveLeft = (i, j, direction) => j !== 0 && direction !== RIGHT && array[i][j - 1] !== BLOCK;
  const fCanMoveRight = (i, j, direction) => j < n - 1 && direction !== LEFT && array[i][j + 1] !== BLOCK;
  const isBucket = (i) => i === m - 1;

  while (queue.length) {
    const [curI, curJ, curPercent, curDirection] = queue.shift();

    if (isBucket(curI)) {
      output[curJ] += curPercent;
      continue;
    }

    if (fCanMoveDown(curI, curJ)) {
      queue.push([curI + 1, curJ, curPercent, DOWN]);
      continue;
    }

    const canMoveLeft = fCanMoveLeft(curI, curJ, curDirection);
    const canMoveRight = fCanMoveRight(curI, curJ, curDirection);

    if (canMoveLeft && canMoveRight) {
      queue.push([curI, curJ - 1, curPercent / 2, LEFT]);
      queue.push([curI, curJ + 1, curPercent / 2, RIGHT]);
    } else if (canMoveLeft) {
      const nextPercent = curDirection === LEFT ? curPercent : curPercent / 2;
      queue.push([curI, curJ - 1, nextPercent, LEFT]);
    } else if (canMoveRight) {
      const nextPercent = curDirection === RIGHT ? curPercent : curPercent / 2;
      queue.push([curI, curJ + 1, nextPercent, RIGHT]);
    }
  }

  return output;
}

// Do not edit the line below.
exports.waterfallStreams = waterfallStreams;
console.log(waterfallStreams([
  [0, 0, 0, 0, 0, 0, 0],
  [1, 0, 0, 0, 0, 0, 0],
  [0, 0, 1, 1, 1, 0, 0],
  [0, 0, 0, 0, 0, 0, 0],
  [1, 1, 1, 0, 0, 1, 0],
  [0, 0, 0, 0, 0, 0, 1],
  [0, 0, 0, 0, 0, 0, 0]
], 3)); // [0, 0, 0, 25, 25, 0, 0]

console.log(waterfallStreams([
  [0, 0, 0, 0, 0, 0, 0],
  [1, 0, 1, 0, 1, 0, 0],
  [0, 0, 1, 1, 1, 0, 0],
  [0, 0, 0, 0, 0, 0, 0],
  [1, 1, 1, 0, 0, 1, 0],
  [0, 0, 0, 0, 0, 0, 1],
  [0, 0, 0, 0, 0, 0, 0]
], 3)); // [0, 0, 0, 0, 0, 0, 0]

console.log(waterfallStreams([
  [0, 0, 0, 0, 0, 0, 0],
  [1, 0, 1, 0, 0, 0, 0],
  [0, 0, 1, 1, 1, 0, 0],
  [0, 0, 0, 0, 0, 0, 0],
  [1, 1, 1, 0, 0, 1, 0],
  [0, 0, 0, 0, 0, 0, 1],
  [0, 0, 0, 0, 0, 0, 0]
], 3)); // [0, 0, 0, 0, 25, 0, 0]
