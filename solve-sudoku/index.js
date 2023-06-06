// https://www.algoexpert.io/questions/solve-sudoku

function solveSudoku(board) {
  const rowFreeValues = [];
  const colFreeValues = [];
  const squareFreeValues = [];

  for (let i = 0; i < 9; i++) {
    rowFreeValues.push(new Set([1, 2, 3, 4, 5, 6, 7, 8, 9]));
    colFreeValues.push(new Set([1, 2, 3, 4, 5, 6, 7, 8, 9]));
    squareFreeValues.push(new Set([1, 2, 3, 4, 5, 6, 7, 8, 9]));
  }

  for (let row = 0; row < 9; row++) {
    for (let col = 0; col < 9; col++) {
      if (board[row][col] === 0) {
        continue;
      }

      const val = board[row][col];
      rowFreeValues[row].delete(val);
      colFreeValues[col].delete(val);
      squareFreeValues[getSquareIndex(row, col)].delete(val);
    }
  }

  let foundSolution = false;

  for (let row = 0; row < 9 && !foundSolution; row++) {
    for (let col = 0; col < 9 && !foundSolution; col++) {
      if (board[row][col] === 0) {
        fillBoard(row, col);
        break;
      }
    }
  }

  return board;

  function fillBoard(row, col) {
    const squareIndex = getSquareIndex(row, col);
    const intersection = new Set([...rowFreeValues[row]].filter(val => colFreeValues[col].has(val) && squareFreeValues[squareIndex].has(val)));

    for (const available of intersection) {
      board[row][col] = available;
      rowFreeValues[row].delete(available);
      colFreeValues[col].delete(available);
      squareFreeValues[squareIndex].delete(available);

      /*
      NEED TO UPDATE THIS TO SKIP FILLED IN SQUARES.
      Search for next slot to fill
      */

      let nextRow = row;
      let nextCol = col;
      while (board[nextRow][nextCol] !== 0) {
        nextCol++;
        if (nextCol === 9) {
          nextRow++;

          if (nextRow === 9) {
            foundSolution = true;
            return;
          }

          nextCol = 0;
        }
      }

      fillBoard(nextRow, nextCol);
      if (foundSolution) {
        return;
      }

      board[row][col] = 0;
      rowFreeValues[row].add(available);
      colFreeValues[col].add(available);
      squareFreeValues[squareIndex].add(available);
    }
  }
}

function getSquareIndex(row, col) {
  if (row > 5) {
    if (col > 5) {
      return 8;
    }

    if (col > 2) {
      return 7;
    }

    return 6;
  }

  if (row > 2) {
    if (col > 5) {
      return 5;
    }

    if (col > 2) {
      return 4;
    }

    return 3;
  }

  if (col > 5) {
    return 2;
  }

  if (col > 2) {
    return 1;
  }

  return 0;
}

// Do not edit the line below.
exports.solveSudoku = solveSudoku;

const board = [
  [7, 8, 0, 4, 0, 0, 1, 2, 0],
  [6, 0, 0, 0, 7, 5, 0, 0, 9],
  [0, 0, 0, 6, 0, 1, 0, 7, 8],
  [0, 0, 7, 0, 4, 0, 2, 6, 0],
  [0, 0, 1, 0, 5, 0, 9, 3, 0],
  [9, 0, 4, 0, 6, 0, 0, 0, 5],
  [0, 7, 0, 3, 0, 0, 0, 1, 2],
  [1, 2, 0, 0, 0, 7, 4, 0, 0],
  [0, 4, 9, 2, 0, 6, 0, 0, 7]
];

solveSudoku(board);
for (const row of board) {
  console.log(row);
}
