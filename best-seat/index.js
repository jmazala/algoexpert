// https://www.algoexpert.io/questions/best-seat

const FILLED = 1;

/*
In a sentence:
"I would find the biggest block of empty seats and sit in the middle"

O(n) - Iterate through the row once keeping track of biggest block of empty seats.

Use a couple placeholders to keep track of the beginning / end of the block

At the end, find the middle of that block
*/

function bestSeat(seats) {
  let right = 0;
  let numEmptySoFar = 0;
  let maxEmpty = -1;

  for (let i = 0; i < seats.length; i++) {
    if (seats[i] === FILLED) {
      numEmptySoFar = 0;
      continue;
    }

    numEmptySoFar++;
    if (numEmptySoFar > maxEmpty) {
      maxEmpty = numEmptySoFar;
      right = i;
    }
  }

  if (maxEmpty === -1) {
    return -1;
  }

  left = right - maxEmpty + 1;

  if (maxEmpty % 2 === 0) {
    return Math.floor((left + right) / 2);
  }

  return (left + right) / 2;
}

// Do not edit the line below.
exports.bestSeat = bestSeat;

console.log(bestSeat([1, 0, 1, 0, 0, 0, 1])); //4
console.log(bestSeat([1, 0])); //1
console.log(bestSeat([1, 1])); //-1
console.log(bestSeat([])); //-1
console.log(bestSeat([0, 1])); // 0
console.log(bestSeat([0, 0, 1])); // 1
console.log(bestSeat([0, 0, 0, 1])); // 1
console.log(bestSeat([0, 0, 0, 1, 0, 0, 0, 0, 0])); // 6