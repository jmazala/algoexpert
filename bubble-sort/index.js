function bubbleSort(array) {
  let swaps = true;
  let iterations = 0;

  while (swaps) {
    swaps = false;

    for (let i = 0; i < array.length - 1 - iterations; i++) {
      if (array[i + 1] < array[i]) {
        const temp = array[i];
        array[i] = array[i + 1];
        array[i + 1] = temp;
        swaps = true;
      }
    }
    
    iterations++;
  }

  return array;
}

// Do not edit the line below.
exports.bubbleSort = bubbleSort;