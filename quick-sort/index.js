// https://www.algoexpert.io/questions/quick-sort

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
    const pivot = array[low];
    let i = low + 1;
    let j = high;

    while (i <= j) {
      if (array[i] > pivot && array[j] < pivot) {
        swap(array, i, j);
        i++;
        j--;
        continue;
      }

      if (array[i] <= pivot) {
        i++;
      }

      if (array[j] >= pivot) {
        j--;
      }
    }

    swap(array, low, j);
    return j;
  }
}

function swap(array, i, j) {
  const temp = array[i];
  array[i] = array[j];
  array[j] = temp;
}

// Do not edit the line below.
exports.quickSort = quickSort;
