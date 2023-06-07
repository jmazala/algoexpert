// https://www.algoexpert.io/questions/reveal-minesweeper

const MINE_UNCLICKED = 'M';
const MINE_CLICKED = 'X';
const HIDDEN_NO_MINE = 'H';

function revealMinesweeper(board, row, column) {
  if (board[row][column] === MINE_UNCLICKED) {
    board[row][column] = MINE_CLICKED;
  } else {
    bfsAndReveal(board, row, column);
  }

  return board;
}

function bfsAndReveal(board, row, column) {
  const m = board.length;
  const n = board[0].length;

  let queue = [[row, column]];

  while (queue.length) {
    let queueLength = queue.length;

    for (let i = 0; i < queueLength; i++) {
      const current = queue.shift();
      if (board[current[0]][current[1]] !== HIDDEN_NO_MINE) {
        continue;
      }

      let adjacentMineCount = 0;

      // up
      const up = [current[0] - 1, current[1]];
      const upLeft = [current[0] - 1, current[1] - 1];
      const upRight = [current[0] - 1, current[1] + 1];

      // down
      const down = [current[0] + 1, current[1]];
      const downLeft = [current[0] + 1, current[1] - 1];
      const downRight = [current[0] + 1, current[1] + 1];

      // left
      const left = [current[0], current[1] - 1];

      // right
      const right = [current[0], current[1] + 1];

      const next = [];
      for (const nextCoordinate of [up, down, left, right, upLeft, upRight, downLeft, downRight]) {
        if (nextCoordinate[0] < 0 || nextCoordinate[0] >= m || nextCoordinate[1] < 0 || nextCoordinate[1] >= n) {
          continue;
        }

        if (board[nextCoordinate[0]][nextCoordinate[1]] === MINE_UNCLICKED) {
          adjacentMineCount++;
        } else if (board[nextCoordinate[0]][nextCoordinate[1]] === HIDDEN_NO_MINE) {
          next.push(nextCoordinate);
        }
      }

      board[current[0]][current[1]] = `${adjacentMineCount}`;
      if (!adjacentMineCount) {
        queue = queue.concat(next);
      }
    }
  }
}

// Do not edit the line below.
exports.revealMinesweeper = revealMinesweeper;

const board = [
  ["H", "H", "H", "H", "M"],
  ["H", "1", "M", "H", "1"],
  ["H", "H", "H", "H", "H"],
  ["H", "H", "H", "H", "H"]
];

for (const row of revealMinesweeper(board, 3, 4)) {
  console.log(row);
}

const board2 = [
  ["H", "M"],
  ["H", "H"]
];

for (const row of revealMinesweeper(board2, 1, 1)) {
  console.log(row);
}