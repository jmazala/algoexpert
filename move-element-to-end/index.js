// https://www.algoexpert.io/questions/move-element-to-end

function moveElementToEnd(array, toMove) {
  let beginning = 0;
  let end = array.length - 1;

  while (beginning < end) {
    while (beginning < end && array[beginning] !== toMove) {
      beginning++;
    }

    while (end > beginning && array[end] === toMove) {
      end--;
    }

    const temp = array[beginning];
    array[beginning] = array[end];
    array[end] = temp;
  }

  return array;
}

// Do not edit the line below.
exports.moveElementToEnd = moveElementToEnd;

console.log(moveElementToEnd([2, 1, 2, 2, 2, 3, 4, 2], 2));