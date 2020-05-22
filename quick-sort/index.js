function quickSort(array) {
  helper(0, array.length - 1);
  return array;

  function helper(low, high) {
    if (low >= high) {
      return;
    }

    const partitionIndex = partition(low, high);
    helper(low, partitionIndex - 1);
    helper(partitionIndex + 1, high);
  }

  function partition(low, high) {
    const pivot = array[high];
    let i = low - 1; //index of smaller element
    for (let j = low; j < high; j++) {
      if (array[j] < pivot) {
        i++;
        const temp = array[i];
        array[i] = array[j];
        array[j] = temp;
      }
    }

    const temp = array[i + 1];
    array[i + 1] = array[high];
    array[high] = temp;
    return i + 1;
  }
}

// Do not edit the line below.
exports.quickSort = quickSort;
