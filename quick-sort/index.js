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
    let i = low;
    for (let j = low; j < high; j++) {
      if (array[j] < pivot) {
        swap(array, i, j);
        i++;
      }
    }

    swap(array, i, high);
    return i;
  }
}

function swap(array, i, j) {
  const temp = array[i];
  array[i] = array[j];
  array[j] = temp;
}

// Do not edit the line below.
exports.quickSort = quickSort;
