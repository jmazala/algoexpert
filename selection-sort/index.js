function selectionSort(array) {
  for (let i = 0; i < array.length; i++) {
    let swapIndex = i;
    for (let j = i + 1; j < array.length; j++) {
      if (array[j] < array[swapIndex]) {
        swapIndex = j;
      }
    }

    if (swapIndex !== i) {
      const temp = array[i];
      array[i] = array[swapIndex];
      array[swapIndex] = temp;
    }
  }

  return array;
}

// Do not edit the line below.
exports.selectionSort = selectionSort;
